package com.lagu.eshop.module.product.service;

import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.dto.ProductForm;
import com.lagu.eshop.module.product.entity.AttributeEntity;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.mapper.AttributeFormMapper;
import com.lagu.eshop.module.product.mapper.FileUploadUtil;
import com.lagu.eshop.module.product.mapper.ProductFormMapper;
import com.lagu.eshop.module.product.mapper.ProductMapper;
import com.lagu.eshop.module.product.repository.AttributeRepository;
import com.lagu.eshop.module.product.repository.CategoryRepository;
import com.lagu.eshop.module.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Product service
 *
 * @author Tomasz Łagowski
 * @version 1.3
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          AttributeRepository attributeRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
    }

    /**
     * Getting random products to the slider
     *
     * @return List of products
     * @since 1.0
     */
    public List<ProductDto> getRandomForSlider() {
        return productRepository.findRandomForHeader(PageRequest.of(0, 3)).stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    /**
     * Search for products
     *
     * @param search Search string
     * @return List of products
     * @since 1.0
     */
    public List<ProductDto> searchProducts(String search) {
        List<ProductEntity> products = productRepository.searchProduct(search);
        return products.stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    /**
     * Getting products by category
     *
     * @param category Category
     * @param page     Page number
     * @param size     Number of products on the page
     * @return List of products
     * @since 1.1
     */
    public ListResponse<ProductDto> getByCategories(long category, int page, int size) {
        List<CategoryEntity> categories = categoryRepository.findAncestry(category);
        Page<ProductEntity> pageProduct = productRepository.findByCategories(categories, PageRequest.of(page, size));
        return new ListResponse<>(
                ProductMapper.map(pageProduct.getContent()),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                size,
                page
        );
    }

    /**
     * Getting products from page number
     *
     * @param page Page number
     * @param size Number of products on the page
     * @return List of products
     * @since 1.1
     */
    public ListResponse<ProductDto> getAllPerPage(int page, int size) {
        Page<ProductEntity> pageProduct = productRepository.findAll(PageRequest.of(page, size));
        return new ListResponse<>(
                ProductMapper.map(pageProduct.getContent()),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                size,
                page
        );
    }

    /**
     * Getting product by UUID
     *
     * @param uuid Product UUID
     * @return Product
     * @since 1.1
     */
    public ProductDto getDtoByUuid(String uuid) {
        return ProductMapper.map(productRepository.getByUuid(uuid), null);
    }

    /**
     * Get all
     *
     * @return List of products
     * @since 1.2
     */
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    /**
     * Create or update product
     *
     * @param product       Product form
     * @param multipartFile Graphic file
     * @return Product entity
     * @throws IOException
     * @since 1.3
     */
    public ProductEntity createOrUpdate(ProductForm product, MultipartFile multipartFile) throws IOException {
        ProductEntity productEntity = (product.isNew()) ? create(product) : update(product);
        if (!multipartFile.isEmpty()) {
            String fileName = "foto.jpg";
            String uploadDir = "/img/" + productEntity.getId() + "/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            productEntity.setPath(uploadDir + fileName);
            productEntity = productRepository.saveAndFlush(productEntity);
        }
        return productEntity;
    }

    /**
     * Create product
     *
     * @param productForm Product form
     * @return Product entity
     * @since 1.3
     */
    public ProductEntity create(ProductForm productForm) {
        Set<AttributeEntity> attributes = new HashSet<>();
        CategoryEntity category = categoryRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wybranej kategorii!!!"));
        ProductEntity product = ProductFormMapper.map(productForm, category, attributes);
        ProductEntity saved = productRepository.saveAndFlush(product);
        if (productForm.getAttributes() != null) {
            if (productForm.getAttributes() != null) {
                attributes = productForm.getAttributes().stream()
                        .map(a -> AttributeFormMapper.map(a, saved))
                        .collect(Collectors.toSet());
                attributeRepository.deleteAllByProduct(product);
                attributeRepository.flush();
            }
        } else if (category.getTemplates() != null) {
            attributes = category.getTemplates().stream()
                    .map(t -> new AttributeEntity()
                            .setCreatedBy(1L)
                            .setProduct(saved)
                            .setName(t.getName())
                    )
                    .collect(Collectors.toSet());
        }
        product.getAttributes().clear();
        if (attributes != null) {
            product.getAttributes().addAll(attributes);
        }
        return productRepository.saveAndFlush(saved);
    }

    /**
     * Update product
     *
     * @param productForm Product form
     * @return Product entity
     * @since 1.3
     */
    public ProductEntity update(ProductForm productForm) {
        CategoryEntity category = categoryRepository.findById(productForm.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wybranej kategorii!!!"));
        final ProductEntity product = productRepository.findByUuid(productForm.getUuid())
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono produktu do aktualizacji!!!"));
        Set<AttributeEntity> attributes = null;
        if (Objects.equals(product.getCategory().getId(), productForm.getCategory())) {
            if (productForm.getAttributes() != null && productForm.getAttributes().size() > 0) {
                attributes = productForm.getAttributes().stream()
                        .map(a -> attributeRepository.findById(a.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono atrybutu do aktualizacji!!!"))
                                .setDescription(a.getDescription())
                        )
                        .collect(Collectors.toSet());
            }
        } else if (category.getTemplates() != null) {
            attributes = category.getTemplates().stream()
                    .map(t -> new AttributeEntity()
                            .setCreatedBy(1L)
                            .setProduct(product)
                            .setName(t.getName())
                    )
                    .collect(Collectors.toSet());
            if (product.getAttributes() != null) {
                product.getAttributes().forEach(a -> attributeRepository.deleteById(a.getId()));
            }
        }
        product.setModel(productForm.getModel())
                .setDescription(productForm.getDescription())
                .setCategory(category)
                .setPrice(productForm.getPrice())
                .setCode(productForm.getCode())
                .setAttributes(attributes);
        return productRepository.saveAndFlush(product);
    }

    /**
     * Delete product
     *
     * @param uuid Product UUID
     * @since 1.3
     */
    public void delete(String uuid) {
        ProductEntity entity = productRepository.getByUuid(uuid);
        productRepository.delete(entity);
    }

    /**
     * @param uuid Product UUID
     * @return Product form
     * @since 1.3
     */
    public ProductForm getFormByUuid(String uuid) {
        return ProductFormMapper.map(productRepository.getByUuid(uuid));
    }

}

package com.lagu.eshop.product.service;

import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.product.dto.ProductDto;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.ProductEntity;
import com.lagu.eshop.product.mapper.ProductMapper;
import com.lagu.eshop.product.repository.CategoryRepository;
import com.lagu.eshop.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Product service
 * @author Tomasz Łagowski
 * @version 1.1
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Getting random products to the slider
     * @since 1.0
     * @return List of products
     */
    public List<ProductDto> getRandomForSlider() {
        return productRepository.findRandomForHeader(PageRequest.of(0, 3)).stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    /**
     * Search for products
     * @since 1.0
     * @param search Search string
     * @return List of products
     */
    public List<ProductDto> searchProducts(String search) {
        List<ProductEntity> products = productRepository.searchProduct(search);
        return products.stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    /**
     * Getting products by category
     * @since 1.1
     * @param category Category
     * @param page Page number
     * @param size Number of products on the page
     * @return List of products
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
     * @since 1.0
     * @param page Page number
     * @param size Number of products on the page
     * @return List of products
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

}

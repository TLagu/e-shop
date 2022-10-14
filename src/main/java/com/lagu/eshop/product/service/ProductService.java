package com.lagu.eshop.product.service;

import com.lagu.eshop.product.dto.ProductDto;
import com.lagu.eshop.product.entity.ProductEntity;
import com.lagu.eshop.product.mapper.ProductMapper;
import com.lagu.eshop.product.repository.AttributeRepository;
import com.lagu.eshop.product.repository.CategoryRepository;
import com.lagu.eshop.product.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getRandomForSlider() {
        return productRepository.findRandomForHeader(PageRequest.of(0, 3)).stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

    public List<ProductDto> searchProducts(String search) {
        List<ProductEntity> products = productRepository.searchProduct(search);
        return products.stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

}

package com.lagu.eshop.product.service;

import com.lagu.eshop.product.dto.CategoryDto;
import com.lagu.eshop.product.dto.CategoryForm;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.TemplateEntity;
import com.lagu.eshop.product.mapper.CategoryFormMapper;
import com.lagu.eshop.product.mapper.CategoryMapper;
import com.lagu.eshop.product.repository.CategoryRepository;
import com.lagu.eshop.product.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Category service
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TemplateRepository templateRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           TemplateRepository templateRepository) {
        this.categoryRepository = categoryRepository;
        this.templateRepository = templateRepository;
    }

    /**
     * Downloading a list of categories and subcategories
     * @since 1.0
     * @return List of category
     */
    public List<CategoryEntity> getMainCategoryWithSubcategories() {
        return categoryRepository.findByParentIsNullOrderByName();
    }

    /**
     * Getting a list of all categories (DTO)
     * @since 1.0
     * @return List of category
     */
    public List<CategoryDto> getAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    /**
     * Getting a category by ID (DTO)
     * @param id Category ID
     * @return Category
     */
    public CategoryDto getDtoById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return CategoryMapper.map(entity.orElse(null), null);
    }

    /**
     * Getting a category by ID (Form)
     * @since 1.0
     * @param id Category ID
     * @return Category
     */
    public CategoryForm getFormById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return (entity.isEmpty()) ? null : CategoryFormMapper.map(entity.get());
    }

    /**
     * Get a list of main categories
     * @since 1.0
     * @return List of categories (DTO)
     */
    public List<CategoryDto> getByParentIsNull() {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    /**
     * Retrieving a list of main categories without specified
     * @since 1.0
     * @param id Category ID
     * @return List of categories (DTO)
     */
    public List<CategoryDto> getByParentIsNullAndIdNot(Long id) {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullAndIdIsNotOrderByName(id);
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

}

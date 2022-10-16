package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.dto.CategoryForm;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.TemplateEntity;
import com.lagu.eshop.module.product.mapper.CategoryMapper;
import com.lagu.eshop.module.product.repository.CategoryRepository;
import com.lagu.eshop.module.product.dto.CategoryDto;
import com.lagu.eshop.module.product.mapper.CategoryFormMapper;
import com.lagu.eshop.module.product.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Category service
 *
 * @author Tomasz Łagowski
 * @version 1.1
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
     *
     * @return List of category
     * @since 1.0
     */
    public List<CategoryEntity> getMainCategoryWithSubcategories() {
        return categoryRepository.findByParentIsNullOrderByName();
    }

    /**
     * Getting a list of all categories (DTO)
     *
     * @return List of category
     * @since 1.0
     */
    public List<CategoryDto> getAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    /**
     * Getting a category by ID (DTO)
     *
     * @param id Category ID
     * @return Category
     */
    public CategoryDto getDtoById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return CategoryMapper.map(entity.orElse(null), null);
    }

    /**
     * Getting a category by ID (Form)
     *
     * @param id Category ID
     * @return Category
     * @since 1.0
     */
    public CategoryForm getFormById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return (entity.isEmpty()) ? null : CategoryFormMapper.map(entity.get());
    }

    /**
     * Get a list of main categories
     *
     * @return List of categories (DTO)
     * @since 1.0
     */
    public List<CategoryDto> getByParentIsNull() {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullOrderByName();
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    /**
     * Retrieving a list of main categories without specified
     *
     * @param id Category ID
     * @return List of categories (DTO)
     * @since 1.0
     */
    public List<CategoryDto> getByParentIsNullAndIdNot(Long id) {
        List<CategoryEntity> categories = categoryRepository.findByParentIsNullAndIdIsNotOrderByName(id);
        return categories.stream().map(c -> CategoryMapper.map(c, null)).collect(Collectors.toList());
    }

    public CategoryEntity createOrUpdate(CategoryForm category) {
        if (category.isNew()) {
            return create(category);
        }
        return update(category.getId(), category);
    }

    /**
     * @param categoryForm Category form
     * @return Category entity
     * @since 1.1
     */
    public CategoryEntity create(CategoryForm categoryForm) {
        Optional<CategoryEntity> parent = categoryRepository.findById(categoryForm.getParent());
        Set<TemplateEntity> templates = new HashSet<>();
        CategoryEntity categoryEntity = CategoryFormMapper.map(categoryForm, parent.orElse(null), templates);
        return categoryRepository.saveAndFlush(categoryEntity);
    }

    /**
     * Update category
     *
     * @param id           Parent category ID
     * @param categoryForm Category form
     * @return Category entity
     * @since 1.1
     */
    public CategoryEntity update(Long id, CategoryForm categoryForm) {
        CategoryEntity parent = categoryRepository.findById(categoryForm.getParent()).orElse(null);
        Set<TemplateEntity> templates = null;
        if (categoryForm.getTemplates() != null) {
            templates = categoryForm.getTemplates().stream()
                    .map(c -> templateRepository.findById(c.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono parametru do aktualizacji!!!"))
                            .setName(c.getName())
                    )
                    .collect(Collectors.toSet());
        }
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii do aktualizacji!!!"))
                .setName(categoryForm.getName())
                .setDescription(categoryForm.getDescription())
                .setParent(parent)
                .setTemplates(templates);
        return categoryRepository.saveAndFlush(category);
    }

    /**
     * Delete category
     *
     * @param id Category ID
     * @since 1.1
     */
    public void delete(Long id) {
        CategoryEntity entity = categoryRepository.getById(id);
        categoryRepository.delete(entity);
    }

}

package com.lagu.eshop.product.repository;

import com.lagu.eshop.product.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Category repository
 * @author Tomasz Łagowski
 * @version 1
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

    /**
     * Getting of categories with subcategories
     * @since 1.0
     * @param categoryId Category ID
     * @return List of categories
     */
    @Query(
            value = "WITH RECURSIVE" +
                    "  starting (id, created_on, created_by, updated_on, updated_by, status, name, description, parent_id) AS (" +
                    "    SELECT t.id, t.created_on, t.created_by, t.updated_on, t.updated_by, t.status, t.name, t.description, t.parent_id" +
                    "    FROM category AS t" +
                    "    WHERE t.id = :categoryId" +
                    "  )," +
                    "  descendants (id, created_on, created_by, updated_on, updated_by, status, name, description, parent_id) AS (" +
                    "    SELECT s.id, s.created_on, s.created_by, s.updated_on, s.updated_by, s.status, s.name, s.description, s.parent_id" +
                    "    FROM starting AS s" +
                    "    UNION ALL" +
                    "    SELECT t.id, t.created_on, t.created_by, t.updated_on, t.updated_by, t.status, t.name, t.description, t.parent_id" +
                    "    FROM category AS t JOIN descendants AS d ON t.parent_id = d.id" +
                    "  )" +
                    "  TABLE descendants",
            nativeQuery = true)
    List<CategoryEntity> findAncestry(@Param("categoryId") Long categoryId);

    /**
     * Get the main categories sorted by name
     * @since 1.0
     * @return List of categories
     */
    List<CategoryEntity> findByParentIsNullOrderByName();

    /**
     * Getting the main categories (except the given one) sorted by name
     * @since 1.0
     * @param id Category ID
     * @return List of categories
     */
    List<CategoryEntity> findByParentIsNullAndIdIsNotOrderByName(Long id);

}

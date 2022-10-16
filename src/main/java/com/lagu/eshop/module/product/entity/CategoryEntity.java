package com.lagu.eshop.module.product.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Product categories
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
@Getter
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status", columnDefinition = "varchar(25) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<CategoryEntity> children;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TemplateEntity> templates;

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public CategoryEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public CategoryEntity setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public CategoryEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public CategoryEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEntity setParent(CategoryEntity parent) {
        this.parent = parent;
        return this;
    }

    public CategoryEntity setChildren(Set<CategoryEntity> children) {
        this.children = children;
        return this;
    }

    public CategoryEntity setTemplates(Set<TemplateEntity> templates) {
        this.templates = templates;
        return this;
    }

}
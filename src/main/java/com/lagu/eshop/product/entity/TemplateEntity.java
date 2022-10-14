package com.lagu.eshop.product.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "category_attribute")
@SQLDelete(sql = "UPDATE category_attribute SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
public class TemplateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_attribute_generator")
    @SequenceGenerator(name = "category_attribute_generator", sequenceName = "category_attribute_id_seq", allocationSize = 1)
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

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public TemplateEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public TemplateEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public TemplateEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public TemplateEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public TemplateEntity setName(String name) {
        this.name = name;
        return this;
    }
}
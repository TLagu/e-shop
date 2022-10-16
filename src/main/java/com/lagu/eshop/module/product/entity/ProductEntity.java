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
 * Product
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
@Getter
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_id_seq", allocationSize = 1)
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

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

    @Column(name = "path")
    private String path;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AttributeEntity> attributes;

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ProductEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public ProductEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public ProductEntity setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public ProductEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public ProductEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ProductEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public ProductEntity setAttributes(Set<AttributeEntity> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ProductEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ProductEntity setPath(String path) {
        this.path = path;
        return this;
    }

    public ProductEntity setCode(String code) {
        this.code = code;
        return this;
    }

}
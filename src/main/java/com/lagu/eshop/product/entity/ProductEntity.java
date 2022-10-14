package com.lagu.eshop.product.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
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

    public Long getId() {
        return id;
    }

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public ProductEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ProductEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ProductEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public ProductEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public ProductEntity setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public ProductEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public ProductEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public Set<AttributeEntity> getAttributes() {
        return attributes;
    }

    public ProductEntity setAttributes(Set<AttributeEntity> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ProductEntity setPath(String path) {
        this.path = path;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProductEntity setCode(String code) {
        this.code = code;
        return this;
    }
}
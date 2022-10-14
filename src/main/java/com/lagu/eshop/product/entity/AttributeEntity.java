package com.lagu.eshop.product.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Additional product attributes
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Entity
@Table(name = "attribute")
@SQLDelete(sql = "UPDATE attribute SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
@Getter
public class AttributeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_generator")
    @SequenceGenerator(name = "attribute_generator", sequenceName = "attribute_id_seq", allocationSize = 1)
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
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public AttributeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AttributeEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public AttributeEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public AttributeEntity setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public AttributeEntity setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public AttributeEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public AttributeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AttributeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public AttributeEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

}
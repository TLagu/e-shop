package com.lagu.eshop.module.product.entity;

import com.lagu.eshop.module.user.entity.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Cart
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Entity
@Table(name = "cart")
@Getter
@EqualsAndHashCode
public class CartEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_generator")
    @SequenceGenerator(name = "card_generator", sequenceName = "card_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total")
    private Double total;

    public CartEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public CartEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public CartEntity setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public CartEntity setTotal(Double total) {
        this.total = total;
        return this;
    }

    /**
     * Add another product
     *
     * @since 1.0
     */
    public void addAmount() {
        this.amount++;
        this.total = this.getProduct().getPrice() * this.amount;
    }

    /**
     * Remove one product
     *
     * @since 1.0
     */
    public void removeAmount() {
        if (this.amount > 1) {
            this.amount--;
            this.total = this.getProduct().getPrice() * this.amount;
        }
    }

}

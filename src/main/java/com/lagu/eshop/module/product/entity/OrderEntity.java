package com.lagu.eshop.module.product.entity;

import com.lagu.eshop.module.user.entity.UserEntity;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Order entity
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Entity
@Table(name = "order_main")
@Getter
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_main_generator")
    @SequenceGenerator(name = "order_main_generator", sequenceName = "order_main_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "uuid")
    private String uuid;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @Column(name = "street")
    private String street;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "post")
    private String post;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderDetailsEntity> orderDetails;

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public OrderEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public OrderEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public OrderEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public OrderEntity setPost(String post) {
        this.post = post;
        return this;
    }

    public OrderEntity setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Set<OrderDetailsEntity> getOrderDetails() {
        return orderDetails;
    }

    public OrderEntity setOrderDetails(Set<OrderDetailsEntity> orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }
}
package com.lagu.eshop.module.user.entity;

import com.lagu.eshop.module.product.entity.Status;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Main user data
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Entity
@Table(name = "app_user")
@SQLDelete(sql = "UPDATE app_user SET status = 'DELETED' WHERE id = ?")
@Where(clause = "status = 'ACTIVE'")
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_generator")
    @SequenceGenerator(name = "app_user_generator", sequenceName = "app_user_id_seq", allocationSize = 1)
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

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "post")
    private String post;

    @Column(name = "street")
    private String street;

    @Column(name = "contact")
    @Enumerated(EnumType.STRING)
    private ContactType contact;

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public UserEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public UserEntity setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public UserEntity setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public UserEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public UserEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public UserEntity setPost(String post) {
        this.post = post;
        return this;
    }

    public UserEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserEntity setContact(ContactType contact) {
        this.contact = contact;
        return this;
    }

}
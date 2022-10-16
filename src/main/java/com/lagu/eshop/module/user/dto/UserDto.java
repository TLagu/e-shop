package com.lagu.eshop.module.user.dto;

import com.lagu.eshop.module.product.entity.Status;
import com.lagu.eshop.module.user.entity.ContactType;
import com.lagu.eshop.module.user.entity.UserRole;
import lombok.Getter;

/**
 * DTO for main user data
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class UserDto {

    private Status status = Status.ACTIVE;
    private String uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role = UserRole.USER;
    private Double longitude;
    private Double latitude;
    private String country;
    private String city;
    private String postCode;
    private String post;
    private String street;
    private ContactType contact;

    public UserDto setStatus(Status status) {
        this.status = status;
        return this;
    }

    public UserDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDto setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public UserDto setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public UserDto setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public UserDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserDto setCity(String city) {
        this.city = city;
        return this;
    }

    public UserDto setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public UserDto setPost(String post) {
        this.post = post;
        return this;
    }

    public UserDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserDto setContact(ContactType contact) {
        this.contact = contact;
        return this;
    }

}
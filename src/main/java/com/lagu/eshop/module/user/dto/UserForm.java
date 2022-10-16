package com.lagu.eshop.module.user.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Form for main user data
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class UserForm {

    private String status;
    private String uuid;
    //    @Size(min = 6, max = 45, message = "Długość powinny być pomiędzy 6 i 45")
    //    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    //            , message = "Niepoprawny format maila")
    private String email;
    //    @Size(min = 3, max = 20, message = "Długość powinny być pomiędzy 3 i 20")
    private String password;
    @Size(min = 3, max = 20, message = "Długość powinny być pomiędzy 3 i 20")
    private String firstName;
    @Size(min = 3, max = 20, message = "Długość powinny być pomiędzy 3 i 20")
    private String lastName;
    private String role;
    private Double longitude;
    private Double latitude;
    @Size(min = 3, max = 100, message = "Długość powinny być pomiędzy 3 i 100")
    private String country;
    @Size(min = 3, max = 100, message = "Długość powinny być pomiędzy 3 i 100")
    private String city;
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Niepoprawny format kodu pocztowego (00-000)")
    private String postCode;
    @Size(min = 3, max = 100, message = "Długość powinny być pomiędzy 3 i 100")
    private String post;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String street;
    private String contact;


    public UserForm setStatus(String status) {
        this.status = status;
        return this;
    }

    public UserForm setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserForm setRole(String role) {
        this.role = role;
        return this;
    }

    public UserForm setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public UserForm setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public UserForm setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserForm setCity(String city) {
        this.city = city;
        return this;
    }

    public UserForm setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public UserForm setPost(String post) {
        this.post = post;
        return this;
    }

    public UserForm setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserForm setContact(String contact) {
        this.contact = contact;
        return this;
    }

    /**
     * Object status verification (new/existing)
     *
     * @return Status whether the object is new or existing
     * @since 1.0
     */
    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}
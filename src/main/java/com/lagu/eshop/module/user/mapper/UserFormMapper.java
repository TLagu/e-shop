package com.lagu.eshop.module.user.mapper;

import com.lagu.eshop.module.product.entity.Status;
import com.lagu.eshop.module.user.dto.UserForm;
import com.lagu.eshop.module.user.entity.ContactType;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.entity.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * User mapper (Form)
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class UserFormMapper {

    /**
     * Form mapping -> Entity
     * @since 1.0
     * @param userForm User form
     * @param userRole User role
     * @param contact Contact type
     * @param encoder Encoded password
     * @return User entity
     */
    public static UserEntity map(UserForm userForm, UserRole userRole, ContactType contact, BCryptPasswordEncoder encoder) {
        UserEntity userEntity = new UserEntity()
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setEmail(userForm.getEmail())
                .setFirstName(userForm.getFirstName())
                .setLastName(userForm.getLastName())
                .setRole(userRole)
                .setLongitude(userForm.getLongitude())
                .setLatitude(userForm.getLatitude())
                .setCountry(userForm.getCountry())
                .setCity(userForm.getCity())
                .setPostCode(userForm.getPostCode())
                .setPost(userForm.getPost())
                .setStreet(userForm.getStreet())
                .setContact(contact);
        if (!userForm.getPassword().isEmpty()) {
            String encodedPassword = encoder.encode(userForm.getPassword());
            userEntity.setPassword(encodedPassword);
        }
        return userEntity;
    }

    /**
     * Entity mapping -> Form
     * @since 1.0
     * @param entity User entity
     * @return User form
     */
    public static UserForm map (UserEntity entity) {
        return new UserForm()
                .setUuid(entity.getUuid())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setRole((entity.getRole() == null) ? UserRole.USER.toString() : entity.getRole().toString())
                .setLongitude(entity.getLongitude())
                .setLatitude(entity.getLatitude())
                .setCountry(entity.getCountry())
                .setCity(entity.getCity())
                .setPostCode(entity.getPostCode())
                .setPost(entity.getPost())
                .setStreet(entity.getStreet())
                .setContact((entity.getContact() == null) ? ContactType.EMAIL.toString() : entity.getContact().toString());
    }

}
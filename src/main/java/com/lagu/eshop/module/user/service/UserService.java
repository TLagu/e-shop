package com.lagu.eshop.module.user.service;

import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.module.user.dto.UserDto;
import com.lagu.eshop.module.user.dto.UserForm;
import com.lagu.eshop.module.user.entity.ContactType;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.entity.UserRole;
import com.lagu.eshop.module.user.mapper.UserFormMapper;
import com.lagu.eshop.module.user.mapper.UserMapper;
import com.lagu.eshop.module.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User service
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Getting user all users from specific page
     *
     * @param page Page number
     * @param size The number of users on page
     * @return List of users
     * @since 1.0
     */
    public ListResponse<UserDto> getAllPerPage(int page, int size) {
        Page<UserEntity> pageProduct = userRepository.findAll(PageRequest.of(page, size));
        return new ListResponse<>(
                UserMapper.map(pageProduct.getContent()),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                size,
                page
        );
    }

    /**
     * Getting user by UUID
     *
     * @param uuid UUID string
     * @return User (DTO)
     * @since 1.0
     */
    public UserDto getDtoByUuid(String uuid) {
        return UserMapper.map(userRepository.getByUuid(uuid));
    }

    /**
     * Getting user by UUID
     *
     * @param uuid UUID string
     * @return User (Form)
     * @since 1.0
     */
    public UserForm getFormByUuid(String uuid) {
        return UserFormMapper.map(userRepository.getByUuid(uuid));
    }

    /**
     * Create or update user
     *
     * @param user    User form
     * @param encoder Encoded password
     * @return User (DTO)
     * @since 1.0
     */
    public UserDto createOrUpdate(UserForm user, BCryptPasswordEncoder encoder) {
        return (user.isNew()) ? create(user, encoder) : update(user, encoder);
    }

    /**
     * Delete user
     *
     * @param uuid UUID string
     * @since 1.0
     */
    public void delete(String uuid) {
        UserEntity entity = userRepository.getByUuid(uuid);
        userRepository.delete(entity);
    }

    /**
     * Create user
     *
     * @param userForm User form
     * @param encoder  Encoded password
     * @return User (DTO)
     * @since 1.0
     */
    private UserDto create(UserForm userForm, BCryptPasswordEncoder encoder) {
        UserRole userRole = UserRole.valueOf(userForm.getRole());
        ContactType contact = ContactType.valueOf(userForm.getContact());
        UserEntity userEntity = UserFormMapper.map(userForm, userRole, contact, encoder);
        UserEntity userUpdate = userRepository.saveAndFlush(userEntity);
        return UserMapper.map(userUpdate);
    }

    /**
     * Update user
     *
     * @param userForm User form
     * @param encoder  Encoded password
     * @return User (DTO)
     * @since 1.0
     */
    private UserDto update(UserForm userForm, BCryptPasswordEncoder encoder) {
        UserRole userRole = ControllerTools.setEnumValue(UserRole.values(), UserRole.USER, userForm.getRole());
        ContactType contact = ControllerTools.setEnumValue(ContactType.values(), ContactType.EMAIL, userForm.getContact());
        UserEntity userEntity = userRepository.getByUuid(userForm.getUuid())
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
        UserEntity userUpdated = userRepository.saveAndFlush(userEntity);
        return UserMapper.map(userUpdated);
    }

    /**
     * Getting user by email
     *
     * @param email Email string
     * @return User (DTO)
     * @since 1.0
     */
    public UserDto getDtoByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserMapper.map(userEntity);
    }

    /**
     * Getting user by email
     *
     * @param email Email string
     * @return User (form)
     * @since 1.0
     */
    public UserForm getFormByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserFormMapper.map(userEntity);
    }

    /**
     * Save user
     *
     * @param userForm User form
     * @param contact  Contact
     * @param encoder  Encoded password
     * @return User entity
     * @since 1.0
     */
    public UserEntity save(UserForm userForm, ContactType contact, BCryptPasswordEncoder encoder) {
        UserEntity userEntity = UserFormMapper.map(userForm, UserRole.USER, contact, encoder);
        return userRepository.saveAndFlush(userEntity);
    }

}
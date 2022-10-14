package com.lagu.eshop.module.user.mapper;

import com.lagu.eshop.module.user.dto.UserDto;
import com.lagu.eshop.module.user.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User mapper (DTO)
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class UserMapper {

    /**
     * Entity mapping to the DTO
     * @since 1.0
     * @param entity User entity
     * @return User DTO
     */
    public static UserDto map(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserDto()
                .setUuid(entity.getUuid())
                .setStatus(entity.getStatus())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setRole(entity.getRole())
                .setLongitude(entity.getLongitude())
                .setLatitude(entity.getLatitude())
                .setCountry(entity.getCountry())
                .setCity(entity.getCity())
                .setPostCode(entity.getPostCode())
                .setPost(entity.getPost())
                .setStreet(entity.getStreet())
                .setContact(entity.getContact());
    }

    /**
     * List of entities mapping to the list of DTOs
     * @since 1.0
     * @param entities List of user entities
     * @return List of user DTOs
     */
    public static List<UserDto> map(List<UserEntity> entities) {
        return entities.stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }

}

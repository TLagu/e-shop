package com.lagu.eshop.module.user.repository;

import com.lagu.eshop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * User repository
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * Getting user by email
     *
     * @param email e-mail string
     * @return User entity
     * @since 1.0
     */
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);

    /**
     * Getting user by UUID
     *
     * @param uuid UUID string
     * @return User entity
     * @since 1.0
     */
    UserEntity getByUuid(String uuid);

}


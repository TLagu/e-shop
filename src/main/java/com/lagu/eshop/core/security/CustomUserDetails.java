package com.lagu.eshop.core.security;

import com.lagu.eshop.module.product.entity.Status;
import com.lagu.eshop.module.user.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * User detail customization
 * @author Tomasz Łagowski
 * @version 1.0
 */
 public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userEntity.getStatus() == Status.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.getStatus() == Status.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.getStatus() == Status.ACTIVE;
    }

    /**
     * Getting User name (firstname + lastname)
     * @since 1.0
     * @return Full name
     */
    public String getFullName() {
        return userEntity.getFirstName() + " " + userEntity.getLastName();
    }

}
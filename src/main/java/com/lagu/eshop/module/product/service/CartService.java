package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.dto.CartForm;
import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.mapper.CartFormMapper;
import com.lagu.eshop.module.product.repository.CartRepository;
import com.lagu.eshop.module.product.repository.ProductRepository;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Cart service
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Add product to cart
     * @since 1.0
     * @param uuid Product UUID
     * @param email User's email
     */
    public void add(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            CartEntity item = cartRepository.findByUserAndProduct(user, product);
            if (item == null) {
                CartEntity tmpItem = new CartEntity()
                        .setUser(user)
                        .setProduct(product)
                        .setAmount(1)
                        .setTotal(product.getPrice());
                cartRepository.save(tmpItem);
            }
        }
    }

    /**
     * Remove product from cart
     * @since 1.0
     * @param uuid Product UUID
     * @param email User's email
     */
    public void remove(String uuid, String email) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user != null && product != null) {
            CartEntity item = cartRepository.findByUserAndProduct(user, product);
            if (item != null) {
                cartRepository.delete(item);
            }
        }
    }

    /**
     * Increase the amount of products
     * @since 1.0
     * @param uuid Product UUID
     * @param email User's email
     */
    public void removeAmount(String uuid, String email) {
        ServiceTools.changeAmount(uuid, email, CartEntity::removeAmount, userRepository, productRepository, cartRepository);
    }

    /**
     * Decrease the amount of products
     * @since 1.0
     * @param uuid Product UUID
     * @param email User's email
     */
    public void addAmount(String uuid, String email) {
        ServiceTools.changeAmount(uuid, email, CartEntity::addAmount, userRepository, productRepository, cartRepository);
    }

    /**
     * Get product UUID by user
     * @since 1.0
     * @param email User's email
     * @return List of UUIDs
     */
    public Set<String> getProductUuidListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        Set<String> items = new HashSet<>();
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
            if (byUser != null) {
                items = byUser.stream().map(c -> c.getProduct().getUuid()).collect(Collectors.toSet());
            }
        }
        return items;
    }

    /**
     * Get product list by user
     * @since 1.0
     * @param email User's email
     * @return List of carts
     */
    public List<CartForm> getProductListByUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        List<CartForm> items = new ArrayList<>();
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
            if (byUser != null) {
                items = byUser.stream()
                        .map(CartFormMapper::map)
                        .collect(Collectors.toList());
            }
        }
        return items;
    }

}

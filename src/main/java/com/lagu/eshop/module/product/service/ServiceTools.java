package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.repository.CartRepository;
import com.lagu.eshop.module.product.repository.ProductRepository;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.repository.UserRepository;

import java.util.function.Consumer;

/**
 * Service tool
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class ServiceTools {

    /**
     * @param uuid              Product UUID
     * @param email             User email
     * @param consumer          Consumer
     * @param userRepository    User repository
     * @param productRepository Product repository
     * @param cartRepository    Cart repository
     * @since 1.0
     */
    public static void changeAmount(
            String uuid,
            String email,
            Consumer<CartEntity> consumer,
            UserRepository userRepository,
            ProductRepository productRepository,
            CartRepository cartRepository
    ) {
        UserEntity user = userRepository.findByEmail(email);
        ProductEntity product = productRepository.getByUuid(uuid);
        if (user == null || product == null) {
            return;
        }
        CartEntity item = cartRepository.findByUserAndProduct(user, product);
        if (item == null) {
            return;
        }
        consumer.accept(item);
        cartRepository.save(item);
    }

}

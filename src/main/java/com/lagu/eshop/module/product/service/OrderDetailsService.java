package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.dto.OrderDetailsDto;
import com.lagu.eshop.module.product.entity.OrderEntity;
import com.lagu.eshop.module.product.mapper.OrderDetailsMapper;
import com.lagu.eshop.module.product.repository.OrderDetailsRepository;
import com.lagu.eshop.module.product.repository.OrderRepository;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Order details service
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get order list
     *
     * @param authentication Authentication
     * @param uuid           Order UUIDs
     * @return List of order details
     * @since 1.0
     */
    public List<OrderDetailsDto> getOrders(Authentication authentication, String uuid) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        OrderEntity order = orderRepository.getByUserAndUuid(user, uuid);
        return orderDetailsRepository.findByOrderOrderByProduct(order).stream()
                .map(OrderDetailsMapper::map)
                .collect(Collectors.toList());
    }

}

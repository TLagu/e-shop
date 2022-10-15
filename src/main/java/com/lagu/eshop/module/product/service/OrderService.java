package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.dto.OrderDto;
import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.product.entity.OrderDetailsEntity;
import com.lagu.eshop.module.product.entity.OrderEntity;
import com.lagu.eshop.module.product.mapper.OrderMapper;
import com.lagu.eshop.module.product.repository.CartRepository;
import com.lagu.eshop.module.product.repository.OrderDetailsRepository;
import com.lagu.eshop.module.product.repository.OrderRepository;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Order service
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderService(
            OrderRepository orderRepository,
            CartRepository cartRepository,
            UserRepository userRepository,
            OrderDetailsRepository orderDetailsRepository
    ) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    /**
     * Get initial order
     * @param authentication Authentication
     * @return Order DTO
     */
    public OrderDto getInitialOrder(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        OrderDto order = new OrderDto().setUser(user).setTotal(0.0);
        if (user != null) {
            List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
            if (byUser != null) {
                Double sum = byUser.stream().map(CartEntity::getTotal).reduce(0.0, Double::sum);
                order.setTotal(sum);
            }
        }
        return order;
    }

    /**
     * Save order
     * @since 1.0
     * @param orderDto Order DTO
     * @param authentication Authentication
     * @return TRUE/FALSE
     */
    public boolean saveOrder(OrderDto orderDto, Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        List<CartEntity> byUser = cartRepository.findByUserOrderByProduct(user);
        if (user != null && byUser != null && byUser.size() > 0) {
            Double sum = byUser.stream().map(CartEntity::getTotal).reduce(0.0, Double::sum);
            OrderEntity order = new OrderEntity()
                    .setUuid(UUID.randomUUID().toString())
                    .setUser(user)
                    .setStreet(orderDto.getStreet())
                    .setPostCode(orderDto.getPostCode())
                    .setPost(orderDto.getPost())
                    .setTotal(sum);
            OrderEntity savedOrder = orderRepository.save(order);
            List<OrderDetailsEntity> orderDetails = byUser.stream()
                    .map(c -> new OrderDetailsEntity()
                            .setOrder(savedOrder)
                            .setProduct(c.getProduct())
                            .setPrice(c.getProduct().getPrice())
                            .setAmount(c.getAmount())
                            .setTotal(c.getTotal()))
                    .collect(Collectors.toList());
            orderDetailsRepository.saveAll(orderDetails);
            return true;
        }
        return false;
    }

    /**
     * Get user orders
     * @since 1.0
     * @param authentication Authentication
     * @return List of user DTOs
     */
    public List<OrderDto> getUserOrders(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        List<OrderEntity> orders = orderRepository.findByUserOrderByCreatedOn(user);
        return orders.stream()
                .map(e -> OrderMapper.map(e, null))
                .collect(Collectors.toList());
    }

    /**
     * Get order by UUID
     * @since 1.0
     * @param authentication Authentication
     * @param uuid Order UUID
     * @return Order DTO
     */
    public OrderDto getOrderByUuid(Authentication authentication, String uuid) {
        UserEntity user = userRepository.findByEmail(authentication.getName());
        OrderEntity order = orderRepository.getByUserAndUuid(user, uuid);
        return OrderMapper.map(order, null);
    }

}

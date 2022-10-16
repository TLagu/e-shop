package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.dto.CartDto;
import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.entity.Status;
import com.lagu.eshop.module.product.repository.CartRepository;
import com.lagu.eshop.module.product.repository.ProductRepository;
import com.lagu.eshop.module.user.entity.ContactType;
import com.lagu.eshop.module.user.entity.UserEntity;
import com.lagu.eshop.module.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private CartService cartService;

    private
    UserEntity user = new UserEntity()
            .setId(1L)
            .setUuid(UUID.randomUUID().toString())
            .setFirstName("First name")
            .setLastName("Last name")
            .setEmail("user1@wp.pl")
            .setPassword("Password")
            .setCountry("Polska")
            .setCity("Szczecin")
            .setPostCode("00-000")
            .setPost("Post")
            .setStreet("Street")
            .setContact(ContactType.EMAIL)
            .setLongitude(14.5528)
            .setLatitude(53.4285)
            .setStatus(Status.ACTIVE);
    ProductEntity product = new ProductEntity()
            .setId(1L)
            .setUuid(UUID.randomUUID().toString())
            .setCreatedOn(LocalDateTime.now())
            .setCreatedBy(1L)
            .setUpdatedOn(null)
            .setUpdatedBy(null)
            .setCategory(null)
            .setCode("Code")
            .setModel("Model")
            .setDescription("Description")
            .setPrice(100.0)
            .setStatus(Status.ACTIVE);
    CartEntity cart = new CartEntity()
            .setUser(user)
            .setProduct(product)
            .setAmount(1)
            .setTotal(product.getPrice());

    @Test
    void shouldAddToCart() {
        //given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(productRepository.getByUuid(product.getUuid())).thenReturn(product);
        when(cartRepository.findByUserAndProduct(user, product)).thenReturn(null);
        when(cartRepository.save(cart)).thenReturn(cart);
        //when
        CartDto expected = cartService.add(product.getUuid(), user.getEmail());
        //then
        assertThat(expected).isNotNull().isInstanceOf(CartDto.class);
        //verify
        verify(cartRepository).save(cart);
    }

    @Test
    void ShouldRemoveFromCart() {
        //given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(productRepository.getByUuid(product.getUuid())).thenReturn(product);
        when(cartRepository.findByUserAndProduct(user, product)).thenReturn(cart);
        //when
        CartRepository cr = mock(CartRepository.class);
        doNothing().when(cr).delete(isA(CartEntity.class));
        cr.delete(cart);
        cartService.remove(product.getUuid(), user.getEmail());
        //then
        //verify
        verify(cr).delete(cart);
    }

    @Test
    void shouldGetProductUuidListByUserEmail() {
        //given
        List<CartEntity> byUser = List.of(cart);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(cartRepository.findByUserOrderByProduct(user)).thenReturn(byUser);
        //when
        Set<String> expected = cartService.getProductUuidListByUser(user.getEmail());
        //then
        assertThat(expected).isNotNull().isEqualTo(Set.of(product.getUuid()));
    }

}
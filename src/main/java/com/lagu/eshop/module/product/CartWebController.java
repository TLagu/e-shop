package com.lagu.eshop.module.product;

import com.lagu.eshop.core.pagination.MenuNavigator;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.module.product.dto.CartForm;
import com.lagu.eshop.module.product.dto.OrderDto;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.service.CartService;
import com.lagu.eshop.module.product.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Cart web controller
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Controller
public class CartWebController {

    private final CartService service;
    private final OrderService orderService;
    private final HttpSession httpSession;

    public CartWebController(CartService service, OrderService orderService, HttpSession httpSession) {
        this.service = service;
        this.orderService = orderService;
        this.httpSession = httpSession;
    }

    /**
     * Add product to cart
     * @since 1.0
     * @param uuid Cart UUIDs
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/cart/add/{uuid}"})
    public ModelAndView add(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        return ControllerTools.addOrRemove(uuid, authentication, service::add, httpSession);
    }

    /**
     * Remove product from cart
     * @since 1.0
     * @param uuid Cart UUIDs
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/cart/remove/{uuid}"})
    public ModelAndView remove(
            @PathVariable String uuid,
            Authentication authentication
    ) {
        return ControllerTools.addOrRemove(uuid, authentication, service::remove, httpSession);
    }

    /**
     * Cart list
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/cart"})
    public String list(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        return showList(model, request, authentication);
    }

    /**
     * Remove cart item
     * @since 1.0
     * @param uuid Cart UUIDs
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/cart/remove-item/{uuid}"})
    public String removeItem(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        service.remove(uuid, authentication.getName());
        return showList(model, request, authentication);
    }

    /**
     * Decrease the amount of products
     * @since 1.0
     * @param uuid Cart UUIDs
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/cart/remove-amount/{uuid}"})
    public String removeAmount(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        service.removeAmount(uuid, authentication.getName());
        return showList(model, request, authentication);
    }

    /**
     * Increase the amount of products
     * @param uuid Cart UUIDs
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/cart/add-amount/{uuid}"})
    public String addAmount(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        service.addAmount(uuid, authentication.getName());
        return showList(model, request, authentication);
    }

    /**
     * Order fulfillment
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @param order Order
     * @return page
     */
    @PostMapping({"/cart"})
    public String makeOrder(
            Model model,
            HttpServletRequest request,
            Authentication authentication,
            OrderDto order
    ) {
        if (orderService.saveOrder(order, authentication)) {
            model.addAttribute("comment", "Zamówienie zrealizowane.");
        } else {
            model.addAttribute("comment", "Błąd realizacji zamówienia");
        }
        return showList(model, request, authentication);
    }

    /**
     * Set product as added
     * @since 1.0
     * @param products List of products
     * @param authentication Authentication
     * @return List of products
     */
    public List<ProductDto> setProductAsAdded(List<ProductDto> products, Authentication authentication) {
        return ControllerTools.setProductAsAdded(products, authentication, service::getProductUuidListByUser, ProductDto::setCart);
    }

    /**
     * Show list of carts
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    private String showList(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        String uri = request.getRequestURI();
        List<CartForm> carts = service.getProductListByUser(authentication.getName());
        OrderDto order = orderService.getInitialOrder(authentication);
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, true));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, true));
        model.addAttribute("carts", carts);
        model.addAttribute("order", order);
        return "shop/cart";
    }

}

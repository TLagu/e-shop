package com.lagu.eshop.module.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.module.product.dto.OrderDto;
import com.lagu.eshop.module.product.service.OrderService;
import com.lagu.eshop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Order Web COntroller
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Controller
public class OrderWebController {

    private final OrderService service;
    private final HttpSession httpSession;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    String uri = "/order";
    boolean isLogged = true;

    public OrderWebController(
            OrderService service,
            HttpSession httpSession,
            UserService userService,
            ObjectMapper objectMapper
    ) {
        this.service = service;
        this.httpSession = httpSession;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    /**
     * Order page
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/order"})
    public String list(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        uri = request.getRequestURI();
        List<OrderDto> orders = service.getUserOrders(authentication);
        setCommonModelSettings(model, authentication);
        model.addAttribute("orders", orders);
        return "shop/orders";
    }

    /**
     * Order detail
     * @since 1.0
     * @param uuid Order UUID
     * @param request HTTP Servlet Request
     * @param model Model attributes
     * @param authentication Authentication
     * @return page
     */
    @GetMapping({"/order/details/{uuid}"})
    public String details(
            @PathVariable String uuid,
            HttpServletRequest request,
            Model model,
            Authentication authentication
    ) {
        uri = request.getRequestURI();
        OrderDto order = service.getOrderByUuid(authentication, uuid);
        setCommonModelSettings(model, authentication);
        model.addAttribute("order", order);
        return "shop/order";
    }

    /**
     * Common model settings
     * @since 1.0
     * @param model Model attributes
     * @param authentication Authentication
     */
    private void setCommonModelSettings(Model model, Authentication authentication) {
        ControllerTools.setCommonModelSettings(model, authentication, httpSession, userService,
                objectMapper, isLogged, uri);
    }

}

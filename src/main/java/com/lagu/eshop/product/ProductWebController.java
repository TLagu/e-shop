package com.lagu.eshop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.product.dto.ProductDto;
import com.lagu.eshop.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductWebController {

    private final ProductService service;
    private final ObjectMapper objectMapper;

    String uri = "/shop";
    boolean isLogged = false;

    public ProductWebController(
            ProductService service,
            ObjectMapper objectMapper
    ) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping({"/", "/home"})
    public String slider(
            Model model,
            HttpServletRequest request
    ) {
        this.uri = request.getRequestURI();
        List<ProductDto> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliders", randomForSlider);
        ControllerTools.setCommonModelSettings(model, objectMapper, isLogged, uri);
        return "shop/index";
    }
}

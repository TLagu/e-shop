package com.lagu.eshop.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductWebController {

    @GetMapping({"/", "/home"})
    public String slider() {
        return "shop/index";
    }
}

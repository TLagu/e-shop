package com.lagu.eshop.module.product;

import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.core.pagination.MenuNavigator;
import com.lagu.eshop.core.pagination.PageWrapper;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Admin product controller
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Controller
public class AdminProductWebController {

    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "25";

    private final ProductService service;

    public AdminProductWebController(ProductService service) {
        this.service = service;
    }

    /**
     * Admin/product list page
     * @since 1.0
     * @param page Page number
     * @param size Number of products on the page
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping({"/admin", "/admin/product"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            Model model,
            HttpServletRequest request
    ) {
        setCommonAttributes(request, model);
        ListResponse<ProductDto> allPerPage = service.getAllPerPage(page, size);
        PageWrapper pageWrapper = new PageWrapper(allPerPage.getMetadata(), request.getRequestURI(), new HashMap<>());
        List<ProductDto> products = allPerPage.getContent();
        model.addAttribute("products", products);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        return "shop/admin-product-list";
    }

    /**
     * Common attributes
     * @since 1.0
     * @param request Security HTTP servlet request
     * @param model Model attributes
     */
    private void setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
    }

}
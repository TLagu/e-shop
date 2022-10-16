package com.lagu.eshop.module.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.core.pagination.PageWrapper;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.dto.PageSetup;
import com.lagu.eshop.module.product.service.CategoryService;
import com.lagu.eshop.module.product.service.ProductService;
import com.lagu.eshop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product web controller
 *
 * @author Tomasz Łagowski
 * @version 1.4
 */
@Controller
public class ProductWebController {
    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "6";

    private final ProductService service;
    private final HttpSession httpSession;
    private final CartWebController cartWebController;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    String uri = "/shop";
    boolean isLogged = false;

    public ProductWebController(
            ProductService service,
            HttpSession httpSession,
            CartWebController cartWebController,
            CategoryService categoryService,
            UserService userService,
            ObjectMapper objectMapper
    ) {
        this.service = service;
        this.httpSession = httpSession;
        this.cartWebController = cartWebController;
        this.categoryService = categoryService;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    /**
     * Root and home page
     *
     * @param model          Model attributes
     * @param request        HTTP Servlet Request
     * @param authentication Security information
     * @return page
     * @since 1.0
     */
    @GetMapping({"/", "/home"})
    public String slider(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        setPageSetup(request, authentication);
        List<ProductDto> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliders", randomForSlider);
        setCommonModelSettings(model, authentication);
        return "shop/index";
    }

    /**
     * Contact page
     *
     * @param model          Model attributes
     * @param request        HTTP Servlet Request
     * @param authentication Security information
     * @return page
     * @since 1.2
     */
    @GetMapping({"/contact"})
    public String contact(
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        setPageSetup(request, authentication);
        setCommonModelSettings(model, authentication);
        return "shop/contact";
    }

    /**
     * Shop page
     *
     * @param page           Page number
     * @param size           Number of products on the page
     * @param category       Category
     * @param model          Model attributes
     * @param request        Http Servlet Request
     * @param authentication Security information
     * @return page
     * @since 1.1
     */
    @GetMapping(value = {"/shop"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam(value = "category", required = false) Long category,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        setPageSetup(request, authentication);

        httpSession.setAttribute("page", String.valueOf(page));
        httpSession.setAttribute("size", String.valueOf(size));

        ListResponse<ProductDto> allPerPage;
        Map<String, String> params = new HashMap<>();

        if (category != null) {
            httpSession.setAttribute("category", category);
            allPerPage = service.getByCategories(category, page, size);
            params.put("category", String.valueOf(category));
        } else {
            allPerPage = service.getAllPerPage(page, size);
        }

        PageWrapper pageWrapper = new PageWrapper(allPerPage.getMetadata(), uri, params);
        List<ProductDto> products = allPerPage.getContent();
        if (isLogged) {
            products = cartWebController.setProductAsAdded(products, authentication);
        }
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("products", products);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        model.addAttribute("pageSetup", new PageSetup(uri, isLogged));
        model.addAttribute("categories", categories);
        setCommonModelSettings(model, authentication);
        return "shop/product";
    }

    /**
     * Product details
     *
     * @param uuid           Project UUID
     * @param model          Model attributes
     * @param request        HTTP Servlet Request
     * @param authentication Security information
     * @return page
     * @since 1.2
     */
    @GetMapping(value = {"/shop/details/{uuid}"})
    public String details(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request,
            Authentication authentication
    ) {
        setPageSetup(request, authentication);
        ProductDto product = service.getDtoByUuid(uuid);
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("productDetails", product);
        model.addAttribute("categories", categories);
        setCommonModelSettings(model, authentication);
        return "shop/product-details";
    }

    /**
     * Search product by string
     *
     * @param search         Search string
     * @param model          Model attributes
     * @param request        HTTP Servlet Request
     * @param authentication Security information
     * @return page
     * @since 1.2
     */
    @PostMapping(value = {"/shop"})
    public String search(String search, Model model, HttpServletRequest request,
                         Authentication authentication
    ) {
        setPageSetup(request, authentication);
        List<ProductDto> products = new ArrayList<>();
        if (search.length() > 2) {
            products = service.searchProducts(search);
        }
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("products", products);
        model.addAttribute("pageSetup", new PageSetup(uri, isLogged));
        model.addAttribute("categories", categories);
        setCommonModelSettings(model, authentication);
        return "shop/product";
    }

    /**
     * Common page setup
     *
     * @param request        HTTP Servlet Request
     * @param authentication Security information
     * @since 1.1
     */
    private void setPageSetup(HttpServletRequest request, Authentication authentication) {
        this.uri = request.getRequestURI();
        this.isLogged = ControllerTools.isLogged(authentication);
    }

    /**
     * Common model settings
     *
     * @param model          Model attributes
     * @param authentication Security information
     * @since 1.1
     */
    private void setCommonModelSettings(Model model, Authentication authentication) {
        ControllerTools.setCommonModelSettings(model, authentication, httpSession, userService,
                objectMapper, isLogged, uri);
    }

}

package com.lagu.eshop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.core.pagination.PageWrapper;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.product.dto.PageSetup;
import com.lagu.eshop.product.dto.ProductDto;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.service.CategoryService;
import com.lagu.eshop.product.service.ProductService;
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
import java.util.stream.Collectors;

/**
 * Product web controller
 * @author Tomasz Łagowski
 * @version 1.2
 */
@Controller
public class ProductWebController {
    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "6";

    private final ProductService service;
    private final HttpSession httpSession;
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    String uri = "/shop";
    boolean isLogged = false;

    public ProductWebController(
            ProductService service,
            HttpSession httpSession,
            CategoryService categoryService,
            ObjectMapper objectMapper
    ) {
        this.service = service;
        this.httpSession = httpSession;
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    /**
     * Root and home page
     * @since 1.0
     * @param model
     * @param request
     * @return
     */
    @GetMapping({"/", "/home"})
    public String slider(
            Model model,
            HttpServletRequest request
    ) {
        setPageSetup(request);
        List<ProductDto> randomForSlider = service.getRandomForSlider();
        model.addAttribute("sliders", randomForSlider);
        setCommonModelSettings(model);
        return "shop/index";
    }

    /**
     * Shop page
     * @since 1.1
     * @param page Page number
     * @param size Number of products on the page
     * @param category Category
     * @param model Model attributes
     * @param request Http Servlet Request
     * @return page
     */
    @GetMapping(value = {"/shop"})
    public String list(
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam(value = "category", required = false) Long category,
            Model model,
            HttpServletRequest request
    ) {
        setPageSetup(request);

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

        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("products", products);
        model.addAttribute("pages", pageWrapper.getPageWrapper());
        model.addAttribute("pageSetup", new PageSetup(uri, isLogged));
        model.addAttribute("categories", categories);
        setCommonModelSettings(model);
        return "shop/product";
    }

    /**
     * Product details
     * @since 1.2
     * @param uuid Project UUID
     * @param model Model attributes
     * @param request Http Servlet Request
     * @return page
     */
    @GetMapping(value = {"/shop/details/{uuid}"})
    public String details(
            @PathVariable String uuid,
            Model model,
            HttpServletRequest request
    ) {
        setPageSetup(request);
        ProductDto product = service.getDtoByUuid(uuid);
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("productDetails", product);
        model.addAttribute("categories", categories);
        setCommonModelSettings(model);
        return "shop/product-details";
    }

    /**
     * Search product by string
     * @since 1.2
     * @param search Search string
     * @param model Model attributes
     * @param request Http Servlet Request
     * @return page
     */
    @PostMapping(value = {"/shop"})
    public String search(String search, Model model, HttpServletRequest request) {
        setPageSetup(request);
        List<ProductDto> products = new ArrayList<>();
        if (search.length() > 2) {
            products = service.searchProducts(search);
        }
        List<CategoryEntity> categories = categoryService.getMainCategoryWithSubcategories();
        model.addAttribute("products", products);
        model.addAttribute("pageSetup", new PageSetup(uri, isLogged));
        model.addAttribute("categories", categories);
        setCommonModelSettings(model);
        return "shop/product";
    }

    /**
     * Common page setup
     * @since 1.1
     * @param request Http Servlet Request
     */
    private void setPageSetup(HttpServletRequest request) {
        this.uri = request.getRequestURI();
    }

    /**
     * Common model settings
     * @since 1.1
     * @param model Model attributes
     */
    private void setCommonModelSettings(Model model) {
        ControllerTools.setCommonModelSettings(model, isLogged, uri);
    }

}

package com.lagu.eshop.module.product;

import com.lagu.eshop.core.pagination.ListResponse;
import com.lagu.eshop.core.pagination.MenuNavigator;
import com.lagu.eshop.core.pagination.PageWrapper;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.dto.ProductForm;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.service.CategoryService;
import com.lagu.eshop.module.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Admin product controller
 * @author Tomasz Łagowski
 * @version 1.1
 */
@Controller
public class AdminProductWebController {

    public final static String DEFAULT_PAGE = "0";
    public final static String DEFAULT_SIZE = "25";

    private final ProductService service;
    private final CategoryService categoryService;

    public AdminProductWebController(ProductService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
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
     * Show product details
     * @since 1.1
     * @param uuid Product UUID
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping("/admin/product/{uuid}")
    public String details(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("product", service.getDtoByUuid(uuid));
        return "shop/admin-product-details";
    }

    /**
     * Product form
     * @since 1.1
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping("/admin/product-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", categoryService.getAll());
        return "shop/admin-product-form";
    }

    /**
     * Edit product
     * @since 1.1
     * @param uuid Product UUID
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping("/admin/product/{uuid}/edit")
    public String edit(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("productForm", service.getFormByUuid(uuid));
        model.addAttribute("categories", categoryService.getAll());
        return "shop/admin-product-form";
    }

    /**
     * Create or update product
     * @since 1.1
     * @param productForm Product form
     * @param result Binding result
     * @param model Model attributes
     * @param request HTTP servlet request
     * @param multipartFile Multipart file
     * @return page
     * @throws IOException
     */
    @PostMapping("/admin/product")
    public String createOrUpdate(@Valid @ModelAttribute("productForm") ProductForm productForm,
                                 BindingResult result, Model model, HttpServletRequest request,
                                 @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {
        if (result.hasErrors()) {
            setCommonAttributes(request, model);
            model.addAttribute("categories", categoryService.getAll());
            return "shop/admin-product-form";
        }
        ProductEntity productEntity = service.createOrUpdate(productForm, multipartFile);
        return "redirect:/admin/product/" + productEntity.getUuid() + "/edit";
    }

    /**
     * Delete by get method
     * @since 1.1
     * @param uuid Product UUID
     * @return page
     */
    @GetMapping("/admin/product/{uuid}/delete")
    public String deleteByGet(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return "redirect:/admin/product";
    }

    /**
     * delete by post method
     * @since 1.1
     * @param uuid Product UUID
     * @return page
     */
    @DeleteMapping("/shop/product/{uuid}/delete")
    public String deleteByPost(@PathVariable("uuid") String uuid) {
        service.delete(uuid);
        return "redirect:/admin/product";
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
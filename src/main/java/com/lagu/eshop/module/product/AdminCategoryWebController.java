package com.lagu.eshop.module.product;

import com.lagu.eshop.core.pagination.MenuNavigator;
import com.lagu.eshop.module.product.dto.CategoryForm;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.service.CategoryService;
import com.lagu.eshop.module.product.service.TemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Category web controller (admin)
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Controller
public class AdminCategoryWebController {

    private final CategoryService service;
    private final TemplateService templateService;

    public AdminCategoryWebController(CategoryService service, TemplateService templateService) {
        this.service = service;
        this.templateService = templateService;
    }

    /**
     * List of categories
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping({"/admin/category"})
    public String list(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categories", service.getAll());
        return "shop/admin-category-list";
    }

    /**
     * Category details
     * @since 1.0
     * @param cid Category id
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping(value = "/admin/category/{id}")
    public String details(@PathVariable("id") Long cid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("category", service.getDtoById(cid));
        return "shop/admin-category-details";
    }

    /**
     * Category form
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping(value = "/admin/category-form")
    public String form(Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("categories", service.getByParentIsNull());
        return "shop/admin-category-form";
    }

    /**
     * Edit category
     * @since 1.0
     * @param cid Category id
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping(value = "/admin/category/{id}/edit")
    public String edit(@PathVariable("id") Long cid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        model.addAttribute("categoryForm", service.getFormById(cid));
        model.addAttribute("categories", service.getByParentIsNullAndIdNot(cid));
        return "shop/admin-category-form";
    }

    /**
     * Creat or update category
     * @since 1.0
     * @param categoryForm Category form
     * @param result Binding result
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @PostMapping("/admin/category")
    public String createOrUpdate(@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm,
                                 BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            setCommonAttributes(request, model);
            model.addAttribute("categories", service.getByParentIsNull());
            return "shop/admin-category-form";
        }
        CategoryEntity categoryEntity = service.createOrUpdate(categoryForm);
        return "redirect:/admin/category/" + categoryEntity.getId() + "/edit";
    }

    /**
     * Delete category (GET)
     * @since 1.0
     * @param cid Category ID
     * @return page
     */
    @GetMapping(value = "/admin/category/{id}/delete")
    public String deleteGet(@PathVariable("id") Long cid) {
        service.delete(cid);
        return "redirect:/admin/category";
    }

    /**
     * Delete category (POST)
     * @since 1.0
     * @param cid Category ID
     * @return page
     */
    @DeleteMapping(value = "/admin/category/{id}/delete")
    public String deletePost(@PathVariable("id") Long cid) {
        service.delete(cid);
        return "redirect:/admin/category";
    }

    /**
     * Add template
     * @since 1.0
     * @param cid Category ID
     * @param model Model attributes
     * @param request HTTP servlet request
     * @return page
     */
    @GetMapping(value = "/admin/category/{id}/template/add")
    public String addTemplate(@PathVariable("id") Long cid, Model model, HttpServletRequest request) {
        setCommonAttributes(request, model);
        templateService.addTemplate(cid);
        model.addAttribute("categoryForm", service.getDtoById(cid));
        model.addAttribute("categories", service.getByParentIsNullAndIdNot(cid));
        return "redirect:/admin/category/" + cid + "/edit";
    }

    /**
     * Delete template (GET)
     * @since 1.0
     * @param cid Category ID
     * @param tid Template ID
     * @return page
     */
    @GetMapping(value = "/admin/category/{cid}/template/{tid}/delete")
    public String deleteTemplateGet(@PathVariable Long cid, @PathVariable Long tid) {
        templateService.delete(cid, tid);
        return "redirect:/admin/category/" + cid + "/edit";
    }

    /**
     * Delete template (POST)
     * @since 1.0
     * @param cid Category ID
     * @param tid Template ID
     * @return page
     */
    @PostMapping(value = "/admin/category/{cid}/template/{tid}/delete")
    public String deleteTemplatePost(@PathVariable Long cid, @PathVariable Long tid) {
        templateService.delete(cid, tid);
        return "redirect:/admin/category/" + cid + "/edit";
    }

    /**
     * Set common attributes
     * @since 1.0
     * @param request HTTP servlet request
     * @param model Model attributes
     */
    private void setCommonAttributes(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getAdminBottomMenu(uri));
        model.addAttribute("middleMenus", new MenuNavigator().getAdminMiddleMenu(uri));
    }

}

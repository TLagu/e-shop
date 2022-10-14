package com.lagu.eshop.module.product;

import com.lagu.eshop.core.pagination.MenuNavigator;
import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.module.user.dto.UserForm;
import com.lagu.eshop.module.user.entity.ContactType;
import com.lagu.eshop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Account web controller
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Controller
public class AccountWebController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public AccountWebController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    /**
     * Account page
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @GetMapping(value = "/account")
    public String form(Model model, HttpServletRequest request, Authentication authentication) {
        generateMenu(model, request);
        model.addAttribute("userForm", userService.getFormByEmail(authentication.getName()));
        return "shop/account";
    }

    /**
     * Update account page
     * @since 1.0
     * @param userForm User form
     * @param result Binding result
     * @param model Model attributes
     * @param request HTTP Servlet Request
     * @param authentication Authentication
     * @return page
     */
    @PostMapping(value = "/account")
    public String update(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, Model model,
                         HttpServletRequest request, Authentication authentication
    ) {
        userForm.setEmail(authentication.getName());
        if (!result.hasErrors() && !userForm.getUuid().isEmpty()) {
            userService.createOrUpdate(userForm, encoder);
            model.addAttribute("userForm", userService.getFormByEmail(authentication.getName()));
        }
        generateMenu(model, request);
        return "shop/account";
    }

    /**
     * Menu generator
     * @since 1.0
     * @param model Model attributes
     * @param request HTTP Servlet Request
     */
    private void generateMenu(
            Model model,
            HttpServletRequest request
    ) {
        String uri = request.getRequestURI();
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, true));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, true));
        model.addAttribute("contacts", ControllerTools.getEnumAsStringList(ContactType.values()));
    }

}

package com.lagu.eshop.module.user;

import com.lagu.eshop.core.util.ControllerTools;
import com.lagu.eshop.module.user.dto.UserDto;
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

import javax.validation.Valid;

/**
 * User controller
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Controller
public class UserWebController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserWebController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    /**
     * Login page
     * @since 1.0
     * @param model Model attributes
     * @return page
     */
    @GetMapping("/login")
    public String viewHomePage(Model model) {
        model.addAttribute("contacts", ControllerTools.getEnumAsStringList(ContactType.values()));
        model.addAttribute("user", new UserForm());
        return "/shop/register-login";
    }

    /**
     * Registering page
     * @since 1.0
     * @param userForm User form
     * @param result Binding Result
     * @param model Model attributes
     * @param authentication Authentication
     * @return page
     */
    @PostMapping("/process-register")
    public String processRegister(
            @Valid @ModelAttribute("user") UserForm userForm,
            BindingResult result, Model model, Authentication authentication
    ) {
        UserDto userDto = userService.getDtoByEmail(userForm.getEmail());
        String comment;
        if (result.hasErrors()) {
            comment = "Błąd wypełnienia danych";
        } else {
            if (authentication != null && authentication.isAuthenticated()) {
                comment = "Jesteś zalogowany, nie możesz się zarejestrować";
            } else if (userDto == null) {
                ContactType contact = ControllerTools.setEnumValue(ContactType.values(), ContactType.EMAIL,
                        userForm.getContact());
                userService.save(userForm, contact, encoder);
                comment = "Konto zostało zarejestrowane";
            } else {
                comment = "Taki adres został już zarejestrowany";
            }
            model.addAttribute("user", userForm);
        }
        model.addAttribute("contacts", ControllerTools.getEnumAsStringList(ContactType.values()));
        model.addAttribute("comment", comment);
        return "shop/register-login";
    }

}
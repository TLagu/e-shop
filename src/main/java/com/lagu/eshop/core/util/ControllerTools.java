package com.lagu.eshop.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.pagination.MenuNavigator;
import com.lagu.eshop.module.product.ProductWebController;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Tool for managing common parameters of the Model
 * @author Tomasz Łagowski
 * @version 1.3
 */
public class ControllerTools {

    /**
     * Setting common parameters of the Model
     * @since 1.1
     * @param model Model attributes
     * @param authentication Security information
     * @param httpSession HTTP Session
     * @param objectMapper Object Mapper
     * @param isLogged Login status
     * @param uri URI of the current page
     */
    public static void setCommonModelSettings(
            Model model,
            Authentication authentication,
            HttpSession httpSession,
            UserService userService,
            ObjectMapper objectMapper,
            boolean isLogged,
            String uri
    ) {
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
    }

    /**
     * Verifying that the user is logged in
     * @since 1.1
     * @param authentication Security information
     * @return Is logged? (True/False)
     */
    public static boolean isLogged(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Replace text value with proper enum
     * @since 1.2
     * @param enumArray Array of enum values
     * @param enumDefault The default if no match is found
     * @param enumText Search text
     * @return Enum value
     * @param <R> Enum type
     */
    public static <R> R setEnumValue(R[] enumArray, R enumDefault, String enumText) {
        for (R enumItem : enumArray) {
            if (enumItem.toString().equals(enumText)) {
                return enumItem;
            }
        }
        return enumDefault;
    }

    /**
     * Get all text values from an enum
     * @since 1.2
     * @param enumArray Array of enum values
     * @return List of all text values from enum
     * @param <R> Enum type
     */
    public static <R> List<String> getEnumAsStringList(R[] enumArray) {
        List<String> listSting = new ArrayList<>();
        for (R enumItem : enumArray) {
            listSting.add(enumItem.toString());
        }
        return listSting;
    }

    /**
     * Redirect to shop (with params)
     * @since 1.3
     * @param httpSession HTTP session
     * @return Model and view
     */
    public static ModelAndView redirectToShop(HttpSession httpSession) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("/shop");
        Object oPage = httpSession.getAttribute("page");
        Object oSize = httpSession.getAttribute("size");
        int page = Integer.parseInt((oPage == null) ? ProductWebController.DEFAULT_PAGE : oPage.toString());
        int size = Integer.parseInt((oSize == null) ? ProductWebController.DEFAULT_SIZE : oSize.toString());
        uriComponentsBuilder.queryParam("page", page);
        uriComponentsBuilder.queryParam("size", size);
        Object oCategory = httpSession.getAttribute("category");
        if (oCategory != null) {
            String category = oCategory.toString();
            uriComponentsBuilder.queryParam("category", category);
        }
        return new ModelAndView("redirect:" + uriComponentsBuilder.toUriString());
    }

    /**
     * Redirect to shop
     * @since 1.3
     * @return Model and view
     */
    public static ModelAndView redirectToShop() {
        return new ModelAndView("redirect:/shop");
    }

    /**
     * Add or remove
     * @since 1.3
     * @param uuid UUID
     * @param authentication Authentication
     * @param action Bi consumer
     * @param httpSession HTTP session
     * @return Model and view
     */
    public static ModelAndView addOrRemove(
            String uuid,
            Authentication authentication,
            BiConsumer<String, String> action,
            HttpSession httpSession
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            action.accept(uuid, authentication.getName());
            return redirectToShop(httpSession);
        }
        return redirectToShop();
    }

    /**
     * Set product as added
     * @since 1.3
     * @param products List of products
     * @param authentication Authentication
     * @param service Function
     * @param setValue Bi consumer
     * @return List of products
     */
    public static List<ProductDto> setProductAsAdded(
            List<ProductDto> products,
            Authentication authentication,
            Function<String, Set<String>> service,
            BiConsumer<ProductDto, Boolean> setValue
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            Set<String> items = service.apply(authentication.getName());
            for (ProductDto product : products) {
                if (items.contains(product.getUuid())) {
                    setValue.accept(product, true);
                }
            }
        }
        return products;
    }

}

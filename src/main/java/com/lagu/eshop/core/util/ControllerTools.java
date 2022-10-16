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
 *
 * @author Tomasz Łagowski
 * @version 1.3
 */
public class ControllerTools {

    /**
     * Setting common parameters of the Model
     *
     * @param model          Model attributes
     * @param authentication Security information
     * @param httpSession    HTTP Session
     * @param objectMapper   Object Mapper
     * @param isLogged       Login status
     * @param uri            URI of the current page
     * @since 1.1
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
     *
     * @param authentication Security information
     * @return Is logged? (True/False)
     * @since 1.1
     */
    public static boolean isLogged(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Replace text value with proper enum
     *
     * @param enumArray   Array of enum values
     * @param enumDefault The default if no match is found
     * @param enumText    Search text
     * @param <R>         Enum type
     * @return Enum value
     * @since 1.2
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
     *
     * @param enumArray Array of enum values
     * @param <R>       Enum type
     * @return List of all text values from enum
     * @since 1.2
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
     *
     * @param httpSession HTTP session
     * @return Model and view
     * @since 1.3
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
     *
     * @return Model and view
     * @since 1.3
     */
    public static ModelAndView redirectToShop() {
        return new ModelAndView("redirect:/shop");
    }

    /**
     * Add or remove
     *
     * @param uuid           UUID
     * @param authentication Authentication
     * @param action         Bi consumer
     * @param httpSession    HTTP session
     * @return Model and view
     * @since 1.3
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
     *
     * @param products       List of products
     * @param authentication Authentication
     * @param service        Function
     * @param setValue       Bi consumer
     * @return List of products
     * @since 1.3
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

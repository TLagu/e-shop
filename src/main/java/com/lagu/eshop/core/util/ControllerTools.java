package com.lagu.eshop.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.pagination.MenuNavigator;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Tool for managing common parameters of the Model
 * @author Tomasz Łagowski
 * @version 1.2
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

}

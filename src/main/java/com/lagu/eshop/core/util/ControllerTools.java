package com.lagu.eshop.core.util;

import com.lagu.eshop.core.pagination.MenuNavigator;
import org.springframework.ui.Model;

/**
 * Tool for managing common parameters of the Model
 * @author Tomasz Łagowski
 * @version 1.1
 */
public class ControllerTools {

    /**
     * Setting common parameters of the Model
     * @since 1.0
     * @param model Model attributes
     * @param isLogged Login status
     * @param uri URI of the current page
     */
    public static void setCommonModelSettings(
            Model model,
            boolean isLogged,
            String uri
    ) {
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
    }

}

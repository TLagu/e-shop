package com.lagu.eshop.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagu.eshop.core.pagination.MenuNavigator;
import org.springframework.ui.Model;

public class ControllerTools {
    public static void setCommonModelSettings(
            Model model,
            ObjectMapper objectMapper,
            boolean isLogged,
            String uri
    ) {
        model.addAttribute("bottomMenus", new MenuNavigator().getUserBottomMenu(uri, isLogged));
        model.addAttribute("middleMenus", new MenuNavigator().getUserMiddleMenu(uri, isLogged));
    }

}

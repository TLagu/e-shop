package com.lagu.eshop.core.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Object used to build the menu
 * @author Tomasz Łagowski
 * @version 1.1
 */
public class MenuNavigator {

    private final List<Menu> userMiddleMenu = List.of(
            new Menu("", "fa fa-lock", "Logowanie", "/login")
    );

    private final List<Menu> loggedUserMiddleMenu = List.of(
            new Menu("", "fa fa-user", "Konto", "/account"),
            new Menu("", "fa fa-star", "Zamówienia", "/order"),
            new Menu("", "fa fa-shopping-cart", "Koszyk", "/cart"),
            new Menu("", "fa fa-lock", "Wylogowanie", "/logout")
    );

    private final List<Menu> userBottomMenu = List.of(
            new Menu("", "", "Strona główna", "/home"),
            new Menu("", "", "Sklep", "/shop"),
            new Menu("", "", "Kontakt", "/contact")
    );

    private final List<Menu> loggedUserBottomMenu = List.of(
            new Menu("", "", "Strona główna", "/home"),
            new Menu("", "", "Sklep", "/shop"),
            new Menu("", "", "Kontakt", "/contact")
    );

    private final List<Menu> loggedAdminMiddleMenu = List.of(
            new Menu("", "fa fa-suitcase", "Produkty", "/admin/product"),
            new Menu("", "fa fa-gear", "Słowniki", "/admin/category"),
            new Menu("", "fa fa-user", "Użytkownicy", "/admin/user"),
            new Menu("", "fa fa-lock", "Wylogowanie", "/logout")
    );

    /**
     * The method that generates the bottom menu content for the user
     * @since 1.0
     * @param url Relative website address
     * @param isLogged Login status
     * @return Menu data list
     */
    public List<Menu> getUserBottomMenu(String url, boolean isLogged) {
        return getMenu(url, (isLogged) ? loggedUserBottomMenu : userBottomMenu);
    }

    /**
     * The method that generates the Middle menu content for the user
     * @since 1.0
     * @param url Relative website address
     * @param isLogged Login status
     * @return Menu data list
     */
    public List<Menu> getUserMiddleMenu(String url, boolean isLogged) {
        return getMenu(url, (isLogged) ? loggedUserMiddleMenu : userMiddleMenu);
    }

    /**
     * The method that generates the bottom menu content for the admin
     * @since 1.1
     * @param url Relative website address
     * @return Menu data list
     */
    public List<Menu> getAdminBottomMenu(String url) {
        return getMenu(url, new ArrayList<>());
    }

    /**
     * The method that generates the middle menu content for the admin
     * @since 1.1
     * @param url Relative website address
     * @return Menu data list
     */
    public List<Menu> getAdminMiddleMenu(String url) {
        return getMenu(url, loggedAdminMiddleMenu);
    }

    /**
     * Sets the style of the active page in the menu
     * @since 1.0
     * @param url Relative website address
     * @param menu List of menu items
     * @return Menu data list
     */
    public List<Menu> getMenu(String url, List<Menu> menu) {
        for (Menu menuItem : menu) {
            if (menuItem.getUrl().equals(url)) {
                menuItem.setClassState("active");
            }
        }
        return menu;
    }

}

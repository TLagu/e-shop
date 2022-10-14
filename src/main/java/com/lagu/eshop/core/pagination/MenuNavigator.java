package com.lagu.eshop.core.pagination;

import java.util.ArrayList;
import java.util.List;

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

    public List<Menu> getUserBottomMenu(String url, boolean isLogged) {
        return getMenu(url, (isLogged) ? loggedUserBottomMenu : userBottomMenu);
    }

    public List<Menu> getUserMiddleMenu(String url, boolean isLogged) {
        return getMenu(url, (isLogged) ? loggedUserMiddleMenu : userMiddleMenu);
    }

    public List<Menu> getAdminBottomMenu(String url) {
        return getMenu(url, new ArrayList<>());
    }

    public List<Menu> getAdminMiddleMenu(String url) {
        return getMenu(url, loggedAdminMiddleMenu);
    }

    public List<Menu> getMenu(String url, List<Menu> menu) {
        for (Menu menuItem : menu) {
            if (menuItem.getUrl().equals(url)) {
                menuItem.setClassState("active");
            }
        }
        return menu;
    }

}

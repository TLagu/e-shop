package com.lagu.eshop.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Object containing css/html parameters for menu display
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class Menu {

    private String classState;
    private String classType;
    private String textValue;
    private String url;

    public Menu setClassState(String classState) {
        this.classState = classState;
        return this;
    }

    public Menu setUrl(String url) {
        this.url = url;
        return this;
    }

}

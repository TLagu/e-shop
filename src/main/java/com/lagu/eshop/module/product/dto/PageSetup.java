package com.lagu.eshop.module.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class PageSetup {

    private final String uri;
    private final boolean isLogged;

}

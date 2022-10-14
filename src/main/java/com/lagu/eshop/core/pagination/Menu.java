package com.lagu.eshop.core.pagination;

public class Menu {
    private String classState;
    private String classType;
    private String textValue;
    private String url;

    public Menu(String classState, String classType, String textValue, String url) {
        this.classState = classState;
        this.classType = classType;
        this.textValue = textValue;
        this.url = url;
    }

    public String getClassState() {
        return classState;
    }

    public Menu setClassState(String classState) {
        this.classState = classState;
        return this;
    }

    public String getClassType() {
        return classType;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getUrl() {
        return url;
    }

    public Menu setUrl(String url) {
        this.url = url;
        return this;
    }
}

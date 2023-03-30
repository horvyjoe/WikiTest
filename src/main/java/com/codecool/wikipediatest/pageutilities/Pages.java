package com.codecool.wikipediatest.pageutilities;

public enum Pages {

    LANDING_PAGE("https://en.wikipedia.org/");
    private final String url;

    Pages(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}

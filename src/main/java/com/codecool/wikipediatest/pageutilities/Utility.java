package com.codecool.wikipediatest.pageutilities;

import org.openqa.selenium.WebDriver;

abstract class Utility {

    private final String url;
    protected final WebDriver driver;

    protected Utility(String url, WebDriver driver) {
        this.url = url;
        this.driver = driver;
    }

    public final void navigateToUrl() {
        driver.get(url);
    }
}

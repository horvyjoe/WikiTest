package com.codecool.wikipediatest.pageutilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPageUtilities extends Utility {

    //Login Credentials
    private static final String USERNAME = System.getenv("username");
    private static final String PASSWORD = System.getenv("password");

    //Path
    private static final By LINK_LOGIN = By.id("pt-login-2");
    private static final By INPUT_USERNAME = By.id("wpName1");
    private static final By INPUT_PASSWORD = By.id("wpPassword1");
    private static final By BUTTON_LOGIN = By.id("wpLoginAttempt");
    private static final By TEXT_VERIFY_LOGIN = By.id("pt-userpage-2");
    private static final By INPUT_SEARCH = By.id("searchInput");

    private static final By BUTTON_USER_MENU = By.id("vector-user-links-dropdown-checkbox");
    private static final By BUTTON_PREFERENCES_MENU = By.xpath("//*[@id=\"pt-preferences\"]/a");
    private static final By BUTTON_LANGUAGE_SETTINGS = By.xpath("//*[@title='Language settings']");

    private static final By BUTTON_FONTS = By.id("uls-display-settings-fonts-tab");

    private static final By CHECKBOX_DOWNLOAD_FONT = By.id("webfonts-enable-checkbox");
    private static final By SELECT_FONT = By.id("content-font-selector");
    private static final By BUTTON_APPLY = By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[2]");

    public LandingPageUtilities(WebDriver driver) {
        super(Pages.LANDING_PAGE.getUrl(), driver);

    }

    public void performLogin() {
        driver.findElement(LINK_LOGIN).click();
        driver.findElement(INPUT_USERNAME).sendKeys(USERNAME);
        driver.findElement(INPUT_PASSWORD).sendKeys(PASSWORD);
        driver.findElement(BUTTON_LOGIN).click();

    }

    public boolean verifyLogin() {
        return driver.findElement(TEXT_VERIFY_LOGIN).getText().equals(USERNAME);
    }

    public void performSearch(String wordToSearch) {
        driver.findElement(INPUT_SEARCH).sendKeys(wordToSearch, Keys.ENTER);
    }

    public void goToLink(String linkText) {
        driver.findElement(By.xpath("//*[@title='" + linkText + "']")).click();
    }

    public void performSettingFontStyle() {
        String fontName = "ComicNeue";
        driver.findElement(BUTTON_USER_MENU).click();
        driver.findElement(BUTTON_PREFERENCES_MENU).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(BUTTON_LANGUAGE_SETTINGS)).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(BUTTON_FONTS)).click();
        WebElement checkBox = driver.findElement(CHECKBOX_DOWNLOAD_FONT);
        if(!checkBox.isSelected()) {
            checkBox.click();
        }
        new Select(driver.findElement(SELECT_FONT)).selectByVisibleText(fontName);
        driver.findElement(BUTTON_APPLY).click();
    }

    public boolean verifyFontStyle(String fontName) {
        driver.findElement(BUTTON_LANGUAGE_SETTINGS).click();
        return new Select(driver.findElement(SELECT_FONT)).getFirstSelectedOption().getAttribute("value").equals(fontName);
    }

}

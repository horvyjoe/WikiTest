package com.codecool.wikipediatest;

import com.codecool.wikipediatest.pageutilities.LandingPageUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class WikipediaTest {

    private static WebDriver driver;
    private static LandingPageUtilities landingPageUtilities;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*", "--start-maximized", "--incognito", "--headless=new");  //"--headless=new"

        driver = new ChromeDriver(options);
        landingPageUtilities = new LandingPageUtilities(driver);

        landingPageUtilities.navigateToUrl();
    }

    @Test
    @DisplayName("Login to wikipedia")
    void login() {
        landingPageUtilities.performLogin();

        Assertions.assertTrue(landingPageUtilities.verifyLogin());
    }

    @Test
    @DisplayName("Search Selenium")
    void searchForSelenium() {
        landingPageUtilities.performSearch("Selenium");

        String expectedUrl = "https://en.wikipedia.org/wiki/Selenium_(software)";

        landingPageUtilities.goToLink("Selenium (software)");

        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Search for Framework")
    void searchForFramework() {
        String searchWord = "Framework";
        landingPageUtilities.performSearch(searchWord);
        String expectedUrl = "https://en.wikipedia.org/wiki/" + searchWord;
        landingPageUtilities.goToLink(searchWord);
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Find a paragraph about Selenium Grid on Wikipedia")
    void searchForSeleniumGrid() {
        landingPageUtilities.performSearch("Selenium");
        landingPageUtilities.goToLink("Selenium (software)");
        Assertions.assertNotNull(driver.findElement(By.xpath("//p[contains(text(), 'Selenium Grid')]")));

    }


    @Test
    @DisplayName("Set font style")
    void setFontStyle() {
        landingPageUtilities.performLogin();
        landingPageUtilities.performSettingFontStyle();

        Assertions.assertTrue(landingPageUtilities.verifyFontStyle("ComicNeue"));
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
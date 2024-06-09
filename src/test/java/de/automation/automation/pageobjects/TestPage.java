package de.automation.automation.pageobjects;

import de.automation.automation.config.WebDriverConfig;
import de.automation.automation.pages.Base;
import de.automation.automation.pages.qyterawebsite.SearchComponent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.AfterMethod;


public class TestPage extends Base {
    @Autowired
    public SearchComponent searchComponent;

    @Autowired
    public WebDriverConfig webDriverConfig;

    @Override
    public boolean isLoaded() {
        return false;
    }

    //@AfterMethod
    public void tearDown() {
        if (chromeDriver() != null) {
            chromeDriver().quit();
        }
    }
}

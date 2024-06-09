package de.automation.automation;

import com.github.javafaker.Faker;
import de.automation.automation.config.WebDriverConfig;
import de.automation.automation.listeners.TestListener;
import de.automation.automation.pages.Base;
import de.automation.automation.pages.qyterawebsite.SearchComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@SpringBootTest
@Listeners(TestListener.class)
class AutomationApplicationTests extends Base {

    @Autowired
    SearchComponent searchComponent;

    @Autowired
    WebDriverConfig webDriverConfig;

    @Test
    void contextLoads() {
        searchComponent.openQyteraWebsite();
        searchComponent.goToQyteraSeminars();
        System.out.println(webDriverConfig.timeout);
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
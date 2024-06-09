package de.automation.automation;

import com.github.javafaker.Faker;
import de.automation.automation.config.WebDriverConfig;
import de.automation.automation.listeners.TestListener;
import de.automation.automation.pageobjects.TestPage;
import de.automation.automation.pages.Base;
import de.automation.automation.pages.qyterawebsite.SearchComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@SpringBootTest
class AutomationApplicationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    TestPage testPage;

    @Test
    void contextLoads() {
        System.out.println("HNAAAA");
        /*testPage.searchComponent.openQyteraWebsite();
        testPage.searchComponent.goToQyteraSeminars();
        System.out.println(testPage.webDriverConfig.timeout);*/
    }


}
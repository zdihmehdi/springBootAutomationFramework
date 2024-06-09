package de.automation.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest
class AutomationApplicationTests extends AbstractTestNGSpringContextTests {

    @Test
    void contextLoads() {
        WebDriver driver = chromeDriver();
        driver.get("https://www.qytera.de/");
    }

    public static WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }
}
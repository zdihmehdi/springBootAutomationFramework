package de.automation.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@SpringBootTest
class AutomationApplicationTests extends AbstractTestNGSpringContextTests {

    @Test
    void contextLoads() throws MalformedURLException {
        WebDriver driver = chromeDriver();
        driver.get("https://www.qytera.de/");
    }

    public static WebDriver chromeDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // URL of the Selenium hub
        String seleniumHubUrl = "http://54.93.239.34:4444/wd/hub";
        return new RemoteWebDriver(new URL(seleniumHubUrl), options);
    }
}
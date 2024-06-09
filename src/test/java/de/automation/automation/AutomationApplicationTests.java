package de.automation.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest
class AutomationApplicationTests extends AbstractTestNGSpringContextTests {

    @Test
    void contextLoads() throws MalformedURLException {
        chromeDriver().get("https://www.qytera.de/");
    }

    public RemoteWebDriver chromeDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Optional: Run Chrome in headless mode
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        options.addArguments("--safebrowsing-disable-auto-update ");
        options.addArguments("--disable-background-networking");
        options.addArguments("--no-proxy-server");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.addArguments("--force-device-scale-factor=1");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        // URL of the remote Selenium Grid hub
        //String remoteWebDriverUrl = System.getProperty("remote.webdriver.url", "http://localhost:4444/wd/hub");
        URL hubUrl = new URL("http://localhost:4444/wd/hub");
        return new RemoteWebDriver(hubUrl, options);
    }
}
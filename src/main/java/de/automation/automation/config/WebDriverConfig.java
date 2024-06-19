package de.automation.automation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class WebDriverConfig {

    private static final String SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";

    @Bean
    @ConditionalOnExpression("'${automation.browser:chrome}'.equals('chrome')")
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    @Bean
    @ConditionalOnExpression("'${automation.browser:remotechrome}'.equals('remotechrome')")
    public static WebDriver remoteChromeDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new RemoteWebDriver(new URL(SELENIUM_HUB_URL), options);
    }
}

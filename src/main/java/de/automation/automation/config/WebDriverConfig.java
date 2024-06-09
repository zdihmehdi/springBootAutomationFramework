package de.automation.automation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class WebDriverConfig {

    @Value("${default.timeout:40}")
    public long timeout;

    @Bean
    @ConditionalOnExpression("'${automation.browser:chrome}'.equals('chrome')")
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--headless");
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }


    @Bean
    @ConditionalOnExpression("'${automation.browser:remote}'.equals('remote')")
    public WebDriver remoteChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new RemoteWebDriver(options);
    }

    @Bean
    @ConditionalOnExpression("'${automation.browser:chrome}'.equals('edge')")
    public WebDriver edgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        //options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    @Bean
    @ConditionalOnExpression("'${automation.browser:chrome}'.equals('firefox')")
    public WebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        return new FirefoxDriver(options);
    }

    @Bean
    public WebDriverWait webDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(this.timeout));
    }
}
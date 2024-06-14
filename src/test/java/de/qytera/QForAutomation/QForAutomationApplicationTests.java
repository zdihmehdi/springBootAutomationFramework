package de.qytera.QForAutomation;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SpringBootTest
class QForAutomationApplicationTests {

    //@BeforeMethod
    public void setUp() {
        // Set up WebDriverManager to automatically download the WebDriver binary
        WebDriverManager.chromedriver().setup();

        // Configure Selenide to use the local WebDriver
        Configuration.browser = "chrome";

        // Set Chrome options to run in headless mode
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // applicable to Windows OS
        options.addArguments("--window-size=1920,1080");

        // Apply the Chrome options
        Configuration.browserCapabilities = options;
    }

    //@BeforeSuite
    public void setUpRemoteDriver() {
        // Configure Selenide to use the remote WebDriver
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "remote-chrome";

        // Set Chrome options to run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu"); // applicable to Windows OS
        options.addArguments("--window-size=1920,1080");

        // Set desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("remote-chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        // Ensure the remote driver is used
        //Configuration.driverManagerEnabled = false;
    }

    @Test
    void contextLoads() {
        System.out.println("TEST BEGIN");
        open("https://www.qytera.de/");
        // Click on the "Kontakt" link
        $x("//a[contains(text(), 'Kontakt')]").click();
        System.out.println("TEST FINISHED");
    }

}

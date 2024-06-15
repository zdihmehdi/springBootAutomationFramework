package de.qytera.QForAutomation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SpringBootTest
class QForAutomationApplicationTests {

    public static WebDriver remoteChromeDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    //@BeforeSuite
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
        RemoteWebDriver driver = null;
        /*try {
            /*System.out.println("TEST BEGIN");

        WebDriver driver = getGridDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.get("https://www.qytera.de/");
        // Click on the "Kontakt" link
        $x("//a[contains(text(), 'Kontakt')]").click();

        System.out.println("TEST FINISHED");*/

            String seleniumHubUrl = "http://selenium-hub:4444/wd/hub/status";
            pingSeleniumHub(seleniumHubUrl);

////////////////////////////////SELENIUM//////////////////////////////////////////////////////
            /*ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), chromeOptions);
            driver.get("http://www.google.com");
            System.out.println(driver.getTitle());
            } catch(MalformedURLException e) {
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    // Close the browser and end the WebDriver session
                    driver.quit();
                }
            }}*/
////////////////////////////////SELENIDE//////////////////////////////////////////////////////

        // Configure ChromeOptions
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--remote-allow-origins=*");

            // Set ChromeOptions as capabilities
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        // Set the remote WebDriver URL
        //RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        // Configure Selenide to use the remote WebDriver
        //Configuration.remote = "http://selenium-hub:4444/wd/hub";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserCapabilities = capabilities;

        // Open the desired URL
        Selenide.open("http://www.google.com");

    }

    public WebDriver getGridDriver() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName("chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        dc.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        return driver;
    }





    public static void pingSeleniumHub(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Selenium Grid hub is running.");
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Selenium Grid hub returned status code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error pinging Selenium Grid hub: " + e.getMessage());
        }
    }

}

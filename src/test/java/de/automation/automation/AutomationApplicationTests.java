package de.automation.automation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@SpringBootTest
@ExtendWith(AllureTestWatcher.class)
public class AutomationApplicationTests extends AbstractTestNGSpringContextTests {

    private static String message = """
            Hallo Qytera,
                       
            Wir sind besonders daran interessiert, unsere Kenntnisse in der Verwendung von Playwright für automatisierte Tests zu vertiefen. Daher möchten wir bei Ihnen anfragen, ob Sie Schulungen oder Workshops zu diesem Thema anbieten. Uns interessieren insbesondere folgende Punkte:
                        
            Grundlagen und Einführung in Playwright
            Erweiterte Techniken und Best Practices
            Integration von Playwright in bestehende CI/CD-Pipelines
            Praxisnahe Übungen und Anwendungsbeispiele
            Bitte teilen Sie uns mit, ob Sie maßgeschneiderte Schulungen für Unternehmen anbieten und welche Optionen bezüglich Dauer, Kosten und Terminen zur Verfügung stehen.
                        
            Wir würden uns sehr über ein Angebot oder weitere Informationen freuen. Bei Rückfragen stehe ich Ihnen jederzeit zur Verfügung.
                        
            Vielen Dank im Voraus und mit freundlichen Grüßen,
            Mehdi
             """;

    private static final String SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";

    public static WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    @BeforeEach
    public void setupSuite() {
        System.out.println("Setup Suite!");
    }

    @Test
    public void contextLoadsChromeDriver() {
        WebDriver driver = chromeDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.get("https://www.qytera.de/");
        Assert.assertEquals("mehdi", "mido");
    }

    @Step
    public void goUrl() {
        WebDriver driver = chromeDriver();
        driver.get("https://www.qytera.de/");
    }

    //@Test
    public void contextLoads() throws InterruptedException {
        String gridUrl = "http://localhost:4444/wd/hub";
        WebDriver driver = chromeDriver();
        driver.get("https://www.qytera.de/");
        WebDriverRunner.setWebDriver(chromeDriver());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        Configuration.remote = gridUrl;
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = capabilities;
        open("https://www.qytera.de/");
        // Assert.assertEquals("mehdi", "mido");
        System.out.println("HNA ZDIH: " + $("div[class='content'] h1").getText());

        $("#edit-name").sendKeys("MEHDI");
        $("#edit-mail").sendKeys("mehdi@gmail.com");
        $("#edit-subject-0-value").sendKeys("Schulung");
        writeText();
        $("#my-submit-button-id").click();
        Thread.sleep(5000);
        $("div[class='messages-list']").should(Condition.visible);
    }

    @Step("click contact tab")
    public void clickConntact() {
        $("li[class='tbm-item level-1']").click();
    }

    @Step("Write text")
    public void writeText() {
        $("#edit-message-0-value").sendKeys(message);
    }

    public static WebDriver remoteChromeDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new RemoteWebDriver(new URL(SELENIUM_HUB_URL), options);
    }

    //@AfterAll
    public static void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            takeScreenshot();
        }
    }

    public static void takeScreenshot() {
        File screenshot = Screenshots.takeScreenShotAsFile();
        if (screenshot != null) {
            try (FileInputStream screenshotStream = new FileInputStream(screenshot)) {
                Allure.addAttachment("Screenshot", screenshotStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package de.automation.automation;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = AutomationApplication.class)
class AutomationApplicationTests extends AbstractTestNGSpringContextTests {

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

    @Test
    void contextLoads() throws InterruptedException, MalformedURLException {

        String gridUrl = "http://localhost:4444/wd/hub"; // Replace with your grid URL

        // Set desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome"); // Change to the browser you want to use

        // Configure Selenide to use remote WebDriver
        Configuration.remote = gridUrl;
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = capabilities;

        //WebDriver driver = remoteChromeDriver();
        open("https://www.qytera.de/");
        System.out.println("HNA ZDIH: " + $("div[class='content'] h1").getText());
        $("li[class='tbm-item level-1']").click();
        $("#edit-name").sendKeys("MEHDI");
        $("#edit-mail").sendKeys("mehdi@gmail.com");
        $("#edit-subject-0-value").sendKeys("Schulung");
        writeText();
        $("#my-submit-button-id").click();
        Thread.sleep(5000);
        $("div[class='messages-list']").should(Condition.visible);
    }

    @Step(value = "Write text")
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

    private SelenideElement searchInput() {
        return $(By.id("edit-keys"));
    }
}
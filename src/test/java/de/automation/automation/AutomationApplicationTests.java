package de.automation.automation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

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
        System.out.println("Test begins!");
    }

    //@Test
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

    @Test
    public void contextLoads() throws MalformedURLException {
        String gridUrl = "http://localhost:4444/wd/hub";
        WebDriverRunner.setWebDriver(remoteChromeDriver());

        setUpCapabilities(gridUrl);
        openWebsite("https://www.qytera.de");
        maximizeWindow();
        checkHomePageContent();
        openContactPage();
        fillContactForm("MEHDI", "mehdi@gmail.com", "Schulung", message);
        //verifySubmissionButtonVisibility();
        //submitForm();
    }

    @Step("Set up browser capabilities")
    public void setUpCapabilities(String gridUrl) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        Configuration.remote = gridUrl;
        Configuration.browserCapabilities = capabilities;
    }

    @Step("Open website: {url}")
    public void openWebsite(String url) {
        open(url);
    }

    @Step("Maximize browser window")
    public void maximizeWindow() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }

    @Step("Check home page content")
    public void checkHomePageContent() {
        System.out.println("Home Page Content: " + $("div[class='content'] h1").getText());
    }

    @Step("Open contact page")
    public void openContactPage() {
        open("https://www.qytera.de/kontakt");
    }

    @Step("Fill contact form with Name: {name}, Email: {email}, Subject: {subject}, Message: {message}")
    public void fillContactForm(String name, String email, String subject, String message) {
        $("#edit-name").sendKeys(name);
        $("#edit-mail").sendKeys(email);
        $("#edit-subject-0-value").sendKeys(subject);
        $("#edit-message-0-value").sendKeys(message);
    }

    @Step("Submit contact form")
    public void submitForm() {
        $("#my-submit-button-id").click();
    }

    @Step("Verify form submission")
    public void verifySubmissionButtonVisibility() throws InterruptedException {
        Thread.sleep(5000);
        $("div[class='messages-list']").should(Condition.visible);
    }

    @Step("click contact tab")
    public void clickContact() {
        $("li[class='tbm-item level-1']").click();
    }

    public static WebDriver remoteChromeDriver() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new RemoteWebDriver(new URL(SELENIUM_HUB_URL), options);
    }
}

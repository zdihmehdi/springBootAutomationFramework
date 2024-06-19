package de.automation.automation.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import de.automation.automation.elements.ButtonElement;
import de.automation.automation.elements.TextInput;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class ContactPage extends Base {

    private TextInput name() {
        return textInput("name text input", () -> $($("#edit-name")));
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

    @Step("Fill contact form with Name: {name}, Email: {email}, Subject: {subject}")
    public void fillContactForm(String name, String email, String subject, String message) {
        name().sendKeys(name);
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


    @Override
    public boolean isLoaded() {
        return false;
    }
}

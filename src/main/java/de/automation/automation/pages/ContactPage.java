package de.automation.automation.pages;

import com.codeborne.selenide.WebDriverRunner;
import de.automation.automation.elements.ButtonElement;
import de.automation.automation.elements.FormElement;
import de.automation.automation.elements.TextInput;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class ContactPage extends Base {

    private TextInput name() {
        return textInput("name text input", () -> $("#edit-name_1"));
    }

    private TextInput email() {
        return textInput("email text input", () -> $("#edit-mail"));
    }

    private TextInput subject() {
        return textInput("subject text input", () -> $("#edit-subject-0-value"));
    }

    private TextInput message() {
        return textInput("message text input", () -> $("#edit-message-0-value"));
    }

    private FormElement websiteHeader() {
        return formElement("get the specefied form element", () -> $("div[class='content'] h1"));
    }

    private ButtonElement submitButton() {
        return buttonElement("submit button", () -> $("#my-submit-button-id"));
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

    @Step("Verify if the header of qytera de is correctly loaded")
    public void checkHomePageContent() {
        Assert.assertTrue(websiteHeader().getText().contains("Wir unterstützen Sie dabei"), "verify if the header of qytera de is correctly loaded");
    }

    @Step("Open qytera website")
    public void openQyteraPage() {
        open("https://www.qytera.de");
    }

    @Step("Open contact page")
    public void openContactPage() {
        open("https://www.qytera.de/kontakt");
    }

    @Step("Fill contact form")
    public void fillContactForm(String name, String email, String subject, String message) {
        name().sendKeys(name);
        email().sendKeys(email);
        subject().sendKeys(subject);
        message().sendKeys(message);
    }

    @Step("Click submit button")
    public void clickSubmitButton() {
        submitButton().click();
    }
}
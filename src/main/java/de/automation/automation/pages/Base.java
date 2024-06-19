package de.automation.automation.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import de.automation.automation.elements.ButtonElement;
import de.automation.automation.elements.FormElement;
import de.automation.automation.elements.TextInput;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public abstract class Base {
    @Autowired
    protected WebDriver driver;

    @Autowired
    protected ButtonElement buttonElement;

    @Autowired
    protected TextInput textInput;

    @Autowired
    protected FormElement formElement;

    protected ButtonElement buttonElement(String name, Supplier<SelenideElement> supplier) {
        return buttonElement.init(name, supplier);
    }

    protected TextInput textInput(String name, Supplier<SelenideElement> supplier) {
        return textInput.init(name, supplier);
    }

    protected FormElement formElement(String name, Supplier<SelenideElement> supplier) {
        return formElement.init(name, supplier);
    }

    @Step("Set up browser capabilities")
    public void setUpCapabilities(String gridUrl) {
        WebDriverRunner.setWebDriver(driver);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        Configuration.remote = gridUrl;
        Configuration.browserCapabilities = capabilities;
    }
}

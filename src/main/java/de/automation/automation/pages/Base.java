package de.automation.automation.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import de.automation.automation.elements.ButtonElement;
import de.automation.automation.elements.TextInput;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Component
public abstract class Base {
    @Autowired
    @Lazy
    protected WebDriver driver;

    @Autowired
    protected ButtonElement buttonElement;

    @Autowired
    protected TextInput textInput;

    @Autowired
    protected WebDriverWait wait;

    @PostConstruct
    private void init() {
        WebDriverRunner.setWebDriver(this.driver);
    }

    @Step(value = "verify if the page is loaded.")
    public abstract boolean isLoaded();

    protected ButtonElement buttonElement(String name, Supplier<SelenideElement> supplier) {
        return buttonElement.init(name, supplier);
    }

    protected TextInput textInput(String name, Supplier<SelenideElement> supplier) {
        return textInput.init(name, supplier);
    }

}
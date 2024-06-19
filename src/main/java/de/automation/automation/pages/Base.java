package de.automation.automation.pages;

import com.codeborne.selenide.SelenideElement;
import de.automation.automation.elements.ButtonElement;
import de.automation.automation.elements.TextInput;
import org.openqa.selenium.WebDriver;
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

    protected ButtonElement buttonElement(String name, Supplier<SelenideElement> supplier) {
        return buttonElement.init(name, supplier);
    }

    protected TextInput textInput(String name, Supplier<SelenideElement> supplier) {
        return textInput.init(name, supplier);
    }

    public abstract boolean isLoaded();
}

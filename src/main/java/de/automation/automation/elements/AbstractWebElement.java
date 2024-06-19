package de.automation.automation.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;

public class AbstractWebElement {

    protected String name;
    protected By selector;
    protected Supplier<SelenideElement> supplier;

    public <T> T init(String name, By selector) {
        this.name = name;
        this.selector = selector;
        return (T) this;
    }

    public <T> T init(String name, Supplier<SelenideElement> supplier) {
        this.name = name;
        this.supplier = supplier;
        return (T) this;
    }

    public SelenideElement element() {
        if (selector != null) {
            return $(selector);
        }

        if (supplier != null) {
            return supplier.get();
        }
        try {
            throw new IllegalAccessException("Neither a selector nor a supplier were found.");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

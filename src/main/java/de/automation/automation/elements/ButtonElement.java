package de.automation.automation.elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ButtonElement extends AbstractWebElement {
    /**
     * Click a button
     */
    @Step(value = "click button")
    public void click() {
        element()
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldBe(Condition.interactable, Duration.ofSeconds(10))
                .scrollIntoView(true)
                .click();
    }

    /**
     * Verify if the button is enabled
     */
    @Step(value = "check whether the button is enabled")
    public boolean isEnabled() {
        return element()
                .scrollIntoView(true)
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .is(Condition.enabled);
    }

    /**
     * Verify if the button is enabled after a specific duration
     */
    @Step(value = "check if the button becomes enabled after a specific duration")
    public boolean isEnabled(Duration duration) {
        return element()
                .scrollIntoView(true)
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .is(Condition.enabled, duration);
    }

    /**
     * Verify if the button exists
     */
    @Step(value = "check if the element exists")
    public boolean exist() {
        return element().exists();
    }
}

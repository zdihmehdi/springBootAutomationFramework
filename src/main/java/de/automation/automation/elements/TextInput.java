package de.automation.automation.elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class TextInput extends AbstractWebElement {

    /**
     * Type a value into the input or hidden field
     *
     * @param value the value to type
     */
    @Step(value = "type a value into the field")
    public void type(String value) {
        element()
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldBe(Condition.interactable, Duration.ofSeconds(10))
                .scrollIntoView(true)
                .type(value);
    }

    /**
     * Send a value into the input field
     *
     * @param value the value to type
     */
    @Step(value = "send keys into a field")
    public void sendKeys(String value) {
        element()
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .scrollIntoView(true)
                .sendKeys(value);
    }

    /**
     * Clear the value of the input or hidden field
     */
    @Step(value = "clear value of field")
    public void clear() {
        element()
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldBe(Condition.interactable, Duration.ofSeconds(10))
                .scrollIntoView(true)
                .clear();
    }

    /**
     * Verify if the input element exists
     */
    @Step(value = "clear value of field")
    public boolean exists() {
        return element().exists();
    }
}

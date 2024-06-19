package de.automation.automation.elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class FormElement extends AbstractWebElement{
    @Step(value = "get form element text")
    public String getText() {
        return element()
                .should(Condition.visible, Duration.ofSeconds(10))
                .scrollIntoView(true)
                .getText();
    }
}

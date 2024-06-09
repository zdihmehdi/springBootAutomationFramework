package de.automation.automation.pages.qyterawebsite;

import de.automation.automation.elements.ButtonElement;
import de.automation.automation.elements.TextInput;
import de.automation.automation.pages.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Component
public class SearchComponent extends Base {

    public TextInput searchInput() {
        return textInput("search input", () -> $($(By.id("edit-keys"))));
    }

    @Step(value = "open qytera website")
    public void openQyteraWebsite() {
        open("https://www.qytera.de/seminare-trainings");
    }

    @Step(value = "go to qytera seminars")
    public void goToQyteraSeminars() {
        searchInput().type("DevOps");
        searchInput().sendKeys(Keys.ENTER.toString());
    }

    @Override
    public boolean isLoaded() {
        return false;
    }
}

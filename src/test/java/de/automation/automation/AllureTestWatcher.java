package de.automation.automation;

import com.codeborne.selenide.Screenshots;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AllureTestWatcher implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            takeScreenshot();
        }
    }

    public void takeScreenshot() {
        File screenshot = Screenshots.takeScreenShotAsFile();
        if (screenshot != null) {
            try (FileInputStream screenshotStream = new FileInputStream(screenshot)) {
                Allure.addAttachment("Screenshot", screenshotStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
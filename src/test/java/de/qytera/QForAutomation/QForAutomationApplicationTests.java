package de.qytera.QForAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeSuite;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SpringBootTest
class QForAutomationApplicationTests {

	@BeforeSuite
	public void setUp() {
		// Set up WebDriverManager to automatically download the WebDriver binary
		WebDriverManager.chromedriver().setup();

		// Set up the browser and other configurations
		Configuration.browser = "chrome";
		getWebDriver().manage().window().maximize();
	}

	@Test
	void contextLoads() {
		open("https://www.qytera.de/");

		// Click on the "Kontakt" link
		$x("//a[contains(text(), 'Kontakt')]").click();
	}

}

package de.automation.automation;

import de.automation.automation.pages.ContactPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(AllureTestWatcher.class)
class AutomationApplicationTests {

    private static String message = """
            Hallo Qytera,
                       
            Wir sind besonders daran interessiert, unsere Kenntnisse in der Verwendung von Playwright für automatisierte Tests zu vertiefen. Daher möchten wir bei Ihnen anfragen, ob Sie Schulungen oder Workshops zu diesem Thema anbieten. Uns interessieren insbesondere folgende Punkte:
                        
            Grundlagen und Einführung in Playwright
            Erweiterte Techniken und Best Practices
            Integration von Playwright in bestehende CI/CD-Pipelines
            Praxisnahe Übungen und Anwendungsbeispiele
            Bitte teilen Sie uns mit, ob Sie maßgeschneiderte Schulungen für Unternehmen anbieten und welche Optionen bezüglich Dauer, Kosten und Terminen zur Verfügung stehen.
                        
            Wir würden uns sehr über ein Angebot oder weitere Informationen freuen. Bei Rückfragen stehe ich Ihnen jederzeit zur Verfügung.
                        
            Vielen Dank im Voraus und mit freundlichen Grüßen,
            Mehdi
             """;

    private static final String SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";

    @Autowired
    ContactPage contactPage;

    @BeforeEach
    public void setupSuite() {
        System.out.println("Test begins!");
    }

    @Test
    void contextLoads() {
        contactPage.setUpCapabilities(SELENIUM_HUB_URL);
        contactPage.openQyteraPage();
        contactPage.maximizeWindow();
        contactPage.checkHomePageContent();
        contactPage.openContactPage();
        contactPage.fillContactForm("MEHDI", "mehdi@gmail.com", "Schulung", message);
        //contactPage.clickSubmitButton();
        //contactPage.submitForm();
    }
}

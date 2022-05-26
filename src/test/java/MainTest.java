import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageAccueil;
import pages.PageAjouterOrdinateur;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private static WebDriver driver;
    private static String REF_PATH = "src/main/resources/references.json";
    private static String DATA_PATH = "src/main/resources/data.json";

    @BeforeAll
    public static void initDriver() {
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void close() {
        driver.quit();
    }

    @Test
    public void creerOrdinateur() {
        try {
            PageAjouterOrdinateur ajout = new PageAjouterOrdinateur(REF_PATH, driver);
            PageAccueil accueil = ajout.ajouterOrdinateur("Mac M1", "2021-03-12", "2022-03-08", "Apple Inc.");
//            assertTrue(accueil.verifierPresenceElement("alerteSuccesCreation"));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void verifierPresenceOrdinateur() {
        PageAccueil accueil = null;
        try {
            accueil = new PageAccueil(REF_PATH, driver);
            accueil.ajouterLecteurDonnees(DATA_PATH);
        } catch (FileNotFoundException e) {
            Assertions.fail(e);
        }
        try {
            assertTrue(accueil.verifierPresenceOrdinateur(accueil.getDonnee("ordinateurs/1/nom")));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void verifierPresenceAlerteCreation() {
        PageAccueil accueil = null;
        try {
            accueil = new PageAccueil(REF_PATH, driver);
        } catch (FileNotFoundException e) {
            Assertions.fail(e.getMessage());
        }
        try {
            assertFalse(accueil.verifierPresenceElement("alerteSuccesCreation"));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        try {
            PageAjouterOrdinateur ajout = accueil.gotoAjouterUnOrdinateur();
            accueil = ajout.ajouterOrdinateur("Mac M1", "2021-03-18", "2022-03-08", "Apple Inc.");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        try {
            assertTrue(accueil.verifierPresenceElement("alerteSuccesCreation"));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}

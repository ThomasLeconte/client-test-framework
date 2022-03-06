import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageAccueil;
import pages.PageAjouterOrdinateur;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private static WebDriver driver;
    private static String REF_PATH = "src/main/resources/references.json";

    @BeforeAll
    public static void init(){
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void close(){
        driver.quit();
    }

    @Test
    public void creerOrdinateur() {
        PageAjouterOrdinateur ajout = new PageAjouterOrdinateur("ajouterOrdinateur", REF_PATH, driver);
        try{
            PageAccueil accueil = ajout.ajouterOrdinateur("Mac M1", "2021-03-12", "2022-03-08", "Apple Inc.");
//            assertTrue(accueil.verifierPresenceElement("alerteSuccesCreation"));
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void verifierPresenceAlerteCreation(){
        PageAccueil accueil = new PageAccueil("accueil", REF_PATH, driver);
        try {
            assertFalse(accueil.verifierPresenceElement("alerteSuccesCreation"));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        PageAjouterOrdinateur ajout = accueil.gotoAjouterUnOrdinateur();
        try {
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

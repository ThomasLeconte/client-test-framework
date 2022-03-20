import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AbstractPage;
import pages.Page;
import pages.PageAccueil;
import pages.PageAjouterOrdinateur;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private static WebDriver driver;
    private static String REF_PATH = "src/main/resources/references.json";
    private AbstractPage page;

    @BeforeAll
    @Before("@BeforeInitDriver")
    public static void initDriver(){
        driver = new ChromeDriver();
    }

    @After("@AfterFermerFenetre")
    public void fermerFenetre(){
        driver.close();
    }

    @AfterAll
    public static void close(){
        driver.quit();
    }

    @Test
    @Given("today is Sunday")
    public void creerOrdinateur() {
        try{
            PageAjouterOrdinateur ajout = new PageAjouterOrdinateur(REF_PATH, driver);
            PageAccueil accueil = ajout.ajouterOrdinateur("Mac M1", "2021-03-12", "2022-03-08", "Apple Inc.");
//            assertTrue(accueil.verifierPresenceElement("alerteSuccesCreation"));
        }catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void verifierPresenceAlerteCreation(){
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

    @Given("Je suis sur le site {string}")
    public void jeSuisSurLeSite(String url) {
        try {
            page = new Page("accueil", REF_PATH, driver, url);
        } catch (FileNotFoundException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @And("Je charge les references de la page {string}")
    public void jeChargeLesReferencesDeLaPage(String nomPage) {
        page.mettreAJourReferences(nomPage);
    }


    @When("Je clique sur le bouton de reference {string}")
    public void jeCliqueSurLeBoutonDeReference(String refBouton) {
        page.clicSurBouton(refBouton);
    }

    @Then("Je suis sur la page {string}")
    public void jeSuisSurLaPage(String url) {
        assertEquals(page.getUrl(), url);
    }

    @When("Je rempli le champs de reference {string} avec le nom {string}")
    public void jeRempliLeChampsDeReferenceAvecLeNom(String refChamps, String valeur) {
        try {
            page.remplirChamps(refChamps, valeur);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @And("Je selectionne la valeur {string} dans la liste déroulante de référence {string}")
    public void jeSelectionneLaValeurDansLaListeDeroulanteDeReference(String valeur, String refListe) {
        page.selectionnerValeurDansListe(refListe, valeur);
    }

    @And("J'attends {string} seconde")
    public void jAttendsSeconde(String nbSecondes) {
        int seconds = 0;
        try{
            seconds = Integer.parseInt(nbSecondes);
        }catch(Exception e){
            Assertions.fail(nbSecondes + " n'est pas un chiffre / nombre !");
        }
        page.attendre(seconds);
    }

    @And("L'element de reference {string} est present sur la page")
    public void lElementDeReferenceEstPresentSurLaPage(String refElement) {
        try {
            page.verifierPresenceElement(refElement);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}

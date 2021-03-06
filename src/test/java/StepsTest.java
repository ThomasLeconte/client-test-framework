import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AbstractPage;
import pages.Page;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class StepsTest {

    private static WebDriver driver;
    private static String REF_PATH = "src/main/resources/references.json";
    private static String DATA_PATH = "src/main/resources/data.json";
    private AbstractPage page;

    @Before("@BeforeInitDriver")
    public static void initDriver() {
        driver = new ChromeDriver();
    }

    @After("@AfterFermerFenetre")
    public void fermerFenetre() {
        driver.close();
    }


    @Given("Je suis sur le site {string}")
    public void jeSuisSurLeSite(String url) {
        try {
            page = new Page("accueil", url, driver, REF_PATH);
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
        assertEquals(url, page.getUrl());
    }

    @And("Je ne suis pas sur la page {string}")
    public void jeNeSuisPasSurLaPage(String url) {
        assertNotEquals(page.getUrl(), url);
    }

    @When("Je rempli le champs de reference {string} avec le nom {string}")
    public void jeRempliLeChampsDeReferenceAvecLeNom(String refChamps, String valeur) {
        try {
            page.remplirChamps(refChamps, valeur);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @And("Je selectionne la valeur {string} dans la liste d??roulante de r??f??rence {string}")
    public void jeSelectionneLaValeurDansLaListeDeroulanteDeReference(String valeur, String refListe) {
        page.selectionnerValeurDansListe(refListe, valeur);
    }

    @And("J'attends {int} seconde")
    public void jAttendsSeconde(int nbSecondes) {
        page.attendre(nbSecondes);
    }

    @And("L'element de reference {string} est present sur la page")
    public void lElementDeReferenceEstPresentSurLaPage(String refElement) {
        try {
            page.verifierPresenceElement(refElement);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @And("J\'ouvre un nouvel onglet avec l\'url {string}")
    public void jOuvreUnNouvelOngletAvecLUrl(String url) {
        page.nouvelOnglet(url);
    }

    @Then("Je vais sur l'onglet num??ro {int}")
    public void jeVaisSurLOngletNumero(int indexOnglet) {
        page.allerSurOnglet(indexOnglet - 1);
    }

    @And("Je d??clare une nouvelle r??f??rence de t??l??phone avec le nom {string} et les dimensions {string}")
    public void jeDeclareUneNouvelleR??f??renceDeTelephoneAvecLeNomEtLesDimensions(String ref, String dimensions) throws FileNotFoundException {
        String[] d = dimensions.split("x");
        int width = 0, height = 0;
        try {
            width = Integer.parseInt(d[0]);
            height = Integer.parseInt(d[1]);
        } catch (Exception e) {
            Assertions.fail("Une des deux dimensions n'est pas un entier, veuillez v??rifier les informations saisies.");
        }
        page.ajouterReferenceMobile(ref, height, width);
    }

    @Then("Je passe en mode mobile sur le mod??le {string}")
    public void jePasseEnModeMobileSurLeMod??le(String ref) throws Exception {
        page.passerEnModeMobile(ref);
    }

    @And("L'??l??ment avec le xpath {string} n'existe pas")
    public void l??l??mentAvecLeXpathGuideIconNExistePas(String xpath) {
        assertFalse(page.recupElement(xpath) instanceof WebElement);
    }

    @And("Je passe en mode desktop")
    public void jeRetireLeModeMobile() {
        page.passerEnModeDesktop();
    }

    @Then("L'??l??ment avec le xpath {string} existe")
    public void l??l??mentAvecLeXpathGuideIconExiste(String xpath) {
        assertTrue(page.recupElement(xpath) instanceof WebElement);
    }

    @And("Je definis l'element {string} avec la r??f??rence {string}")
    public void jeDefinisLElementAvecLaR??f??rence(String ref, String xpath) {
        page.ajouterReferenceDynamique(ref, xpath);
    }

    @Then("Je supprime les cookies du navigateur")
    public void jeSupprimeLesCookiesDuNavigateur() {
        page.supprimerCookies();
    }

    @Then("Je raffraichis la page")
    public void jeRaffraichisLaPage() {
        page.raffraichir();
    }

    @Then("L'??l??ment avec la r??f??rence {string} existe")
    public void l??l??mentAvecLaR??f??renceExiste(String ref) {
        try {
            assertNotNull(page.recupElementParReference(ref));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}

package pages;

import exceptions.ReferenceNotFoundException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import tools.DataReader;
import tools.MobileResizer;
import tools.ReferenceReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractPage {
    protected ReferenceReader refReader;
    protected String refPath;
    protected String pageName;
    protected String url;
    protected Map<String, String> references;
    protected WebDriver driver;
    protected Actions driverActions;
    protected DataReader jsonReader;
    protected MobileResizer mobileResizer;

    public AbstractPage(String pageName, WebDriver driver, String url, String refPath) throws FileNotFoundException {
        this.pageName = pageName;
        this.driver = driver;
        this.driverActions = new Actions(driver);
        this.mobileResizer = new MobileResizer();
        this.references = new HashMap<>();
        this.driver.manage().window().maximize();
        this.url = url;
        if (!this.driver.getCurrentUrl().equals(url)) {
            this.driver.navigate().to(url);
        }

        if (refPath != null) this.ajouterLecteurReferences(refPath);
    }

    public void mettreAJourReferences(String nomPage) {
        this.references = refReader.getReferencesOfpage(nomPage);
    }

    public void ajouterLecteurDonnees(String cheminFichier) throws FileNotFoundException {
        this.jsonReader = new DataReader(cheminFichier);
    }

    public void ajouterReferenceMobile(String nom, int hauteur, int largeur) {
        this.mobileResizer.addReference(nom, largeur, hauteur);
    }

    public void ajouterLecteurReferences(String refPath) throws FileNotFoundException {
        this.refPath = refPath;
        this.refReader = new ReferenceReader(refPath);
        this.references = refReader.getReferencesOfpage(this.pageName);
    }

    public void modifierTailleEcran(int largeur, int hauteur) {
        this.driver.manage().window().setSize(new Dimension(largeur, hauteur));
    }

    public void supprimerCookies() {
        this.driver.manage().deleteAllCookies();
    }

    public void nouvelOnglet() {
        this.driver.switchTo().newWindow(WindowType.TAB);
    }

    public void nouvelOnglet(String url) {
        this.nouvelOnglet();
        this.driver.navigate().to(url);
    }

    public void allerSurOnglet(int indexOnglet) {
        this.driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(indexOnglet));
    }

    public void raffraichir() {
        this.driver.navigate().refresh();
    }

    public void pagePrecedente() {
        this.driver.navigate().back();
    }

    public void pageSuivante() {
        this.driver.navigate().forward();
    }

    public void ajouterReferenceDynamique(String nomReference, String xpath) {
        this.references.put(nomReference, xpath);
    }

    /**
     * Basé sur la taille d'écran d'un iPhone 12 / 13 Pro Max
     */
    public void passerEnModeMobile() {
        this.modifierTailleEcran(428, 926);
    }

    public void passerEnModeMobile(String referenceTelephone) throws Exception {
        this.mobileResizer.resizePage(this, referenceTelephone);
    }

    public void passerEnModeDesktop() {
        this.modifierTailleEcran(1920, 1080);
    }

    public String getDonnee(String cheminJson) throws Exception {
        if (this.jsonReader == null) {
            throw new Exception("Vous n'avez pas ajouté de lecteur de données à votre page. Utilisez la méthode ajouterLecteurDonnees() pour l'ajouter !");
        }
        String result = null;
        try {
            result = this.jsonReader.getDonnee(this.jsonReader.getFileContent(), cheminJson).getAsString();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de la donnée. Veuillez vérifier le chemin spécifié.");
        }
        return result;
    }

    private WebDriver driver() {
        return this.driver;
    }

    private String $(String key) throws Exception {
        if (this.references.containsKey(key)) {
            return this.references.get(key);
        } else {
            throw new ReferenceNotFoundException(key, this.pageName);
        }
    }

    private WebElement getByXPath(String xPath) {
        return this.driver.findElement(By.xpath(xPath));
    }

    public WebElement recupElementParReference(String ref) throws Exception {
        return this.getByXPath($(ref));
    }

    public WebElement recupElement(String xPath) {
        return this.getByXPath(xPath);
    }

    public void viderChamps(String referenceChamps) throws Exception {
        this.getByXPath(this.$(referenceChamps)).clear();
    }

    public AbstractPage attendre(int nbSecondes) {
        try {
            Thread.sleep(nbSecondes * 1000);
            return this;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remplirChamps(String referenceChamps, String valeur) throws Exception {
        this.viderChamps(referenceChamps);
        this.getByXPath(this.$(referenceChamps)).sendKeys(valeur);
    }

    public void selectionnerValeurDansListe(String referenceListe, String option) {
        try {
            Select selectField = new Select(this.getByXPath(this.$(referenceListe)));
            List<WebElement> options = selectField.getOptions();
            if (options.stream().anyMatch(e -> e.getText().equals(option))) {
                selectField.selectByVisibleText(option);
            } else {
                throw new RuntimeException("La valeur " + option + " est introuvable dans la liste déroulante " + referenceListe + " !");
            }
            selectField.selectByVisibleText(option);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clicSurBouton(String referenceBouton) {
        try {
            this.getByXPath(this.$(referenceBouton)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifierPresenceValeurDansTableau(String valueToFind) {
        return this.getByXPath("//table//tr//td[.='" + valueToFind + "']") != null;
    }

    public boolean verifierPresenceElement(String referenceElement) throws Exception {
        try {
            return this.getByXPath(this.$(referenceElement)) != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

/*    public Page genererPage(String pageName, String url) throws FileNotFoundException {
        return new Page(pageName, driver, url, this.refPath);
    }*/

    public String getUrl() {
        return this.driver.getCurrentUrl();
    }

    public void faireCaptureEcran(String destination) throws IOException {
        TakesScreenshot driver = (TakesScreenshot) this.driver;
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        String destinationResult = destination +
                File.separator +
                this.pageName +
                "_" +
                LocalDateTime.now().toString().replace(".", "_").replace(":", "_") +
                ".jpg";
        FileUtils.copyFile(screenshot, new File(destinationResult));
        log("Capture d'écran effectuée !", "Destination : " + destinationResult);
    }

    private void log(String title, String desc) {
        System.out.println("\n# - - - - " + title + " - - - - #\n");
        System.out.println("# " + desc + "\n");
        System.out.println("# - - - - - - - - - - - - - - - - #\n");
    }
}

package pages;

import exceptions.ReferenceNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import tools.DataReader;
import tools.ReferenceReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    public AbstractPage(String pageName, String refPath, WebDriver driver, String url) throws FileNotFoundException {
        this.pageName = pageName;
        this.refPath = refPath;
        this.refReader = new ReferenceReader(refPath);
        this.references = refReader.getReferencesOfpage(this.pageName);
        this.driver = driver;
        this.driverActions = new Actions(driver);
        this.driver.manage().window().maximize();
        this.url = url;
        if(!this.driver.getCurrentUrl().equals(url)){
            this.driver().navigate().to(url);
        }
    }

    public void ajouterLecteurDonnees(String cheminFichier) throws FileNotFoundException {
        this.jsonReader = new DataReader(cheminFichier);
    }

    public void modifierTailleEcran(int largeur, int hauteur){
        this.driver.manage().window().setSize(new Dimension(largeur, hauteur));
    }

    public void supprimerCookies(){
        this.driver.manage().deleteAllCookies();
    }

    public void nouvelOnglet(){
        this.driver.switchTo().newWindow(WindowType.TAB);
    }

    public void nouvelOnglet(String url){
        this.nouvelOnglet();
        this.driver.navigate().to(url);
    }

    public void allerSurOnglet(int indexOnglet){
        this.driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(indexOnglet));
    }

    /**
     * Basé sur la taille d'écran d'un iPhone 12 / 13 Pro Max
     */
    public void passerEnModeMobile(){
        this.modifierTailleEcran(428, 926);
    }

    public String getDonnee(String cheminJson) throws Exception {
        if(this.jsonReader == null){
            throw new Exception("Vous n'avez pas ajouté de lecteur de données à votre page. Utilisez la méthode ajouterLecteurDonnees() pour l'ajouter !");
        }
        String result = "";
        try{
            result = this.jsonReader.getDonnee(this.jsonReader.getFileContent(), cheminJson).getAsString();
        }catch (Exception e){
            System.out.println("Erreur lors de la récupération de la donnée. Veuillez vérifier le chemin spécifié.");
        }
        return result;
    }

    public WebDriver driver() {
        return this.driver;
    }

    public String $(String key) throws Exception {
        if (this.references.containsKey(key)) {
            return this.references.get(key);
        } else {
            throw new ReferenceNotFoundException(key, this.pageName);
        }
    }

    public WebElement getByXPath(String xPath) {
        return this.driver.findElement(By.xpath(xPath));
    }

    public WebElement recupElement(String xPath){
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

    public Page genererPage(String pageName, String url) throws FileNotFoundException {
        return new Page(pageName, this.refPath, driver, url);
    }
}

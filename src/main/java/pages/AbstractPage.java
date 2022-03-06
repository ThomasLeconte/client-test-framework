package pages;

import exceptions.ReferenceNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tools.ReferenceReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class AbstractPage {
    protected ReferenceReader refReader;
    protected String refPath;
    protected String pageName;
    protected String url;
    protected Map<String, String> references;
    protected WebDriver driver;

    public AbstractPage(String pageName, String refPath, WebDriver driver) {
        try {
            this.pageName = pageName;
            this.refPath = refPath;
            this.refReader = new ReferenceReader(refPath);
            this.references = refReader.getReferencesOfpage(this.pageName);
            this.driver = driver;
        } catch (FileNotFoundException e) {

        }
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

    public void attendre(int nbSecondes) {
        try {
            Thread.sleep(nbSecondes * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remplirChamps(String referenceChamps, String valeur) throws Exception {
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

    public void clicSurBouton(String referenceBouton){
        try {
            this.getByXPath(this.$(referenceBouton)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

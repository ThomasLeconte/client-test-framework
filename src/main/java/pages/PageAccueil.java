package pages;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class PageAccueil extends AbstractPage {

    public PageAccueil(String refPath, WebDriver driver) throws FileNotFoundException {
        super("accueil", refPath, driver, "https://computer-database.gatling.io/computers");
    }

    public PageAjouterOrdinateur gotoAjouterUnOrdinateur() throws FileNotFoundException {
        return new PageAjouterOrdinateur(this.refPath, driver);
    }

    public boolean verifierPresenceOrdinateur(String computerName) throws Exception {
        this.remplirChamps("rechercheOrdinateur", computerName);
        this.clicSurBouton("rechercheOrdinateurBtn");
        return this.verifierPresenceValeurDansTableau(computerName);
    }
}

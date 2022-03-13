package pages;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class PageAccueil extends AbstractPage {

    public PageAccueil(String name, String refPath, WebDriver driver) throws FileNotFoundException {
        super(name, refPath, driver);
        String url = "https://computer-database.gatling.io/computers";
        if(!this.driver.getCurrentUrl().equals(url)){
            this.driver().navigate().to(url);
        }
    }

    public PageAjouterOrdinateur gotoAjouterUnOrdinateur() throws FileNotFoundException {
        return new PageAjouterOrdinateur("ajouterOrdinateur", this.refPath, driver);
    }

    public boolean verifierPresenceOrdinateur(String computerName) throws Exception {
        this.remplirChamps("rechercheOrdinateur", computerName);
        this.clicSurBouton("rechercheOrdinateurBtn");
        return this.verifierPresenceValeurDansTableau(computerName);
    }
}

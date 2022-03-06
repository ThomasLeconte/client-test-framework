package pages;

import org.openqa.selenium.WebDriver;

public class PageAccueil extends AbstractPage {

    public PageAccueil(String name, String refPath, WebDriver driver) {
        super(name, refPath, driver);
        String url = "https://computer-database.gatling.io/computers";
        if(!this.driver.getCurrentUrl().equals(url)){
            this.driver().navigate().to(url);
        }
    }

    public PageAjouterOrdinateur gotoAjouterUnOrdinateur(){
        return new PageAjouterOrdinateur("ajouterOrdinateur", this.refPath, driver);
    }

    public boolean verifierPresenceOrdinateur(String computerName) throws Exception {
        this.remplirChamps("rechercheOrdinateur", computerName);
        this.clicSurBouton("rechercheOrdinateurBtn");
        return this.verifierPresenceValeurDansTableau(computerName);
    }
}

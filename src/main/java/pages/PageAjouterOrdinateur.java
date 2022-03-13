package pages;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class PageAjouterOrdinateur extends AbstractPage {

    public PageAjouterOrdinateur(String name, String refPath, WebDriver driver) throws FileNotFoundException {
        super(name, refPath, driver);
        String url = "https://computer-database.gatling.io/computers/new";
        if(!this.driver.getCurrentUrl().equals(url)){
            this.driver().navigate().to(url);
        }
    }

    public void remplirFormulaire(String computerName, String introducedDate, String discontinuedDate, String company) throws Exception {
        this.remplirChamps("champsNom", computerName);
        this.remplirChamps("champsDateSortie", introducedDate);
        this.remplirChamps("champsDateFin", discontinuedDate);
        this.selectionnerValeurDansListe("listeCompagnies", company);
    }

    public PageAccueil ajouterOrdinateur(String computerName, String introducedDate, String discontinuedDate, String company) throws Exception {
        this.remplirFormulaire(computerName, introducedDate, discontinuedDate, company);
        this.clicSurBouton("createBouton");
        return new PageAccueil("accueil", this.refPath, this.driver);
    }
}

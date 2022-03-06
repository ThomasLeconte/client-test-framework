package pages;

import org.openqa.selenium.WebDriver;

public class PageAjouterOrdinateur extends AbstractPage {

    public PageAjouterOrdinateur(String name, String refPath, WebDriver driver) {
        super(name, refPath, driver);
        this.url = "https://computer-database.gatling.io/computers/new";
        this.driver().navigate().to(url);
    }

    public void remplirFormulaire(String computerName, String introducedDate, String discontinuedDate, String company) throws Exception {
        this.remplirChamps("champsNom", computerName);
        this.remplirChamps("champsDateSortie", introducedDate);
        this.remplirChamps("champsDateFin", discontinuedDate);
        this.selectionnerValeurDansListe("listeCompagnies", company);
    }
}

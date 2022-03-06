package pages;

import org.openqa.selenium.WebDriver;

public class PageAccueil extends AbstractPage {

    public PageAccueil(String name, String refPath, WebDriver driver) {
        super(name, refPath, driver);
        this.driver().get("https://computer-database.gatling.io/computers");
    }

    public void clicSurAjouterOrdinateur() throws InterruptedException {
        try {
            this.getByXPath(this.$("addComputerBtn")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PageAjouterOrdinateur gotoAjouterUnOrdinateur(){
        return new PageAjouterOrdinateur("ajouterOrdinateur", this.refPath, driver);
    }
}

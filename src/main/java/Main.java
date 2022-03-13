import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageAccueil;
import tools.DataReader;

public class Main {

    public static final String URL = "https://computer-database.gatling.io/computers";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
        try {
            launchWebDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchWebDriver() throws Exception {
//        DataReader reader = new DataReader("src/main/resources/data.json");
//        System.out.println(reader.getDonnee(reader.getFileContent(), "ordinateurs/1/nom").getAsString());
        PageAccueil accueil = new PageAccueil("accueil", "src/main/resources/references.json", new ChromeDriver());
        accueil.ajouterLecteurDonnees("src/main/resources/data.json");
        accueil.nouvelOnglet("https://google.com");
//        accueil.passerEnModeMobile();
//        accueil.nouvelOnglet();
//        accueil.attendre(1);
//        accueil.allerSurOnglet(0);
//        System.out.println(accueil.getDonnee("ordinatdedeeurs/1/nom"));
//        PageAjouterOrdinateur pageAjout = accueil.gotoAjouterUnOrdinateur();
//        accueil = pageAjout.ajouterOrdinateur("Mac M1", "2022-10-22", "2022-10-23", "Apple Inc.");
//        accueil.verifierPresenceElement("alerteSuccesCreation");
//        accueil.verifierPresenceOrdinateur("ACE");
//        pageAjout.driver().quit();
    }
}

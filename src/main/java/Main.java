public class Main {

    public static final String URL = "https://computer-database.gatling.io/computers";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver");
        try {
            launchWebDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchWebDriver() throws Exception {
//        DataReader reader = new DataReader("src/main/resources/data.json");
//        System.out.println(reader.getDonnee(reader.getFileContent(), "ordinateurs/1/nom").getAsString());
/*        PageAccueil accueil = new PageAccueil("src/main/resources/references.json", new ChromeDriver());
        accueil.ajouterLecteurDonnees("src/main/resources/data.json");
        accueil.faireCaptureEcran("C:\\Users\\bastu\\Desktop");*/

//        accueil.nouvelOnglet("https://google.com");
//        Page test = accueil.genererPage("toto", "https://www.youtube.com");
//        test.attendre(1).recupElement("//*[@id=\"content\"]/div[2]/div[5]/div[2]/ytd-button-renderer[2]/a").click();
//        test.attendre(1).recupElement("//input[@id=\"search\"]").sendKeys("coucou");
//        test.recupElement("//*[@id=\"search-icon-legacy\"]").click();

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

import exceptions.DataNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Page;
import tools.DataReader;
import tools.ReferenceReader;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class FrameworkTest {

    private static WebDriver driver;
    private static String REF_PATH = "src/main/resources/references.json";
    private static String DATA_PATH = "src/main/resources/data.json";


    public static void initDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
    }

    @AfterAll
    public static void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void referenceReaderTest() {
        assertDoesNotThrow(() -> new ReferenceReader(REF_PATH));
        assertThrows(FileNotFoundException.class, () -> new ReferenceReader(REF_PATH + "hello world !"));
    }

    @Test
    public void instanciatePage() {
        try {
            new Page("accueil", "https://youtube.com", driver, REF_PATH);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void lecteurDonnesTest() {
        try {
            DataReader d = new DataReader(DATA_PATH);
            assertNotNull(d.getDonnee(d.getFileContent(), "ordinateurs/1/nom"));
            assertThrows(DataNotFoundException.class, () -> d.getDonnee(d.getFileContent(), "ordinateurs/40/name"));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}

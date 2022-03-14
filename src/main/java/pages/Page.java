package pages;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class Page extends AbstractPage {

    public Page(String pageName, String refPath, WebDriver driver, String url) throws FileNotFoundException {
        super(pageName, refPath, driver, url);
    }
}

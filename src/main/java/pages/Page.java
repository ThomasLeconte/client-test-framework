package pages;

import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class Page extends AbstractPage {

    public Page(String pageName, String url, WebDriver driver, String refPath) throws FileNotFoundException {
        super(pageName, driver, url, refPath);
    }
}

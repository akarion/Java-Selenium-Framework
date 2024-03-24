package ParallelExperiments;

import ParallelExperiments.BrowserX;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class DriverAdapter {
    private ThreadLocal<WebDriver> webDriver;
    private ThreadLocal<Actions> actions;

// WORK IN PROGRESS

    public void launchBrowser(BrowserX browser){
        webDriver = new ThreadLocal<>();
        actions = new ThreadLocal<>();

        switch (browser){

            case CHROME:
                webDriver.set(new ChromeDriver());
            break;

            case EDGE:
                webDriver.set(new EdgeDriver());
            break;

            default:
                throw new IllegalArgumentException(browser.name());
        }

        actions.set(new Actions(webDriver.get()));

    }
    public void quit(){
        webDriver.get().quit();
}

    public void goToURL(String url){
        webDriver.get().get(url);
    }



}

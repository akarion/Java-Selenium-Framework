package setup;

import frameworkUtils.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Chrome extends Browser {

    private ChromeOptions chromeOptions;

    public Chrome() {
        this.chromeOptions = new ChromeOptions();
    }

    public void addArguments(List<String> e) {
        chromeOptions.addArguments(e);
    }


    public WebDriver launchBrowser() {
        addArguments(Arguments.readArguments());
        this.driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        return driver;
    }


    public RemoteWebDriver launchRemoteBrowser(String url) throws URISyntaxException, MalformedURLException {
        addArguments(Arguments.readArguments());
        this.remoteWebDriver = new RemoteWebDriver(new URI(url).toURL(), chromeOptions); // e.g. http://192.168.57.2:4444/wd/hub
        return remoteWebDriver;
    }

    public void disableAutomationInfobars() {
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    }

}

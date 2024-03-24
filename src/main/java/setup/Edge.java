package setup;

import frameworkUtils.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Edge extends Browser {

    private EdgeOptions edgeOptions;
    private WebDriver driver;

    public Edge() {
        this.edgeOptions = new EdgeOptions();
    }

    @Override
    public void addArguments(List<String> e) {
        edgeOptions.addArguments(e);
    }

    @Override
    public WebDriver launchBrowser() {
        driver = new EdgeDriver(edgeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        return driver;
    }

    @Override
    public void disableAutomationInfobars() {
        edgeOptions.setExperimentalOption("useAutomationExtension", false);
        edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    }

    public RemoteWebDriver launchRemoteBrowser(String remoteUrl) {
        addArguments(Arguments.readArguments()); // Assuming Arguments.readArguments() is a static method elsewhere
        try {
            URL url = new URL(remoteUrl);
            this.remoteWebDriver = new RemoteWebDriver(url, edgeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return remoteWebDriver;
    }
}

package setup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

public abstract class Browser {

    protected WebDriver driver;
    protected RemoteWebDriver remoteWebDriver;
    protected int timeout;

    public abstract void addArguments(List<String> strings);

    public abstract WebDriver launchBrowser();

    public abstract RemoteWebDriver launchRemoteBrowser(String url) throws URISyntaxException, MalformedURLException;

    public abstract void disableAutomationInfobars();

    public void getProperties() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader("src/configuration/general.properties"));
        timeout = Integer.parseInt(props.getProperty("timeout"));
    }

}

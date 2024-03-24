package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LandingPage {

    private static WebDriver driver;
    private static final By acceptCookies = By.xpath("//button/div[text()='Accept all']");
    private static final By declineCookies = By.xpath("//button/div[text()='Reject all']");
    private static final By searchInput = By.xpath("//textarea[@title='Search']");
    private static final By searchButton = By.xpath("//input[@aria-label='Google Search']");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    public LandingPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public static void acceptCookies(Boolean accept) throws InterruptedException {
        if (accept) {
            driver.findElement(acceptCookies).click();
        } else {
            driver.findElement(declineCookies).click();
        }
        Thread.sleep(1000); //to allow cookie to load
    }

    public static void pressEnterSearch() {

        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public static void sendInputToSearch(String input) {
        driver.findElement(searchInput).sendKeys(input);

    }


}

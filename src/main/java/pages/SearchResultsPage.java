package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private static WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    public SearchResultsPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public static List<WebElement> photosTopSixResults() {
        By catPhotos = By.xpath("//g-section-with-header/div[2]//div[@style='margin-right:-20px;margin-bottom:-20px']/div[@data-attrid='images universal']//div[@style='height:192px;width:204px']/img");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstPhoto = wait.until(ExpectedConditions.presenceOfElementLocated(catPhotos));
        wait.until(e -> firstPhoto.isDisplayed());
        return driver.findElements(catPhotos);

    }

    public static List<WebElement> getAllSearchResultTitles() {
        By catLinks = By.xpath("//a[not(@aria-label='More results')]/h3");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstLink = wait.until(ExpectedConditions.presenceOfElementLocated(catLinks));
        wait.until(e -> firstLink.isDisplayed());
        return driver.findElements(catLinks);

    }

}

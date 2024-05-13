package StandardTests;

import browserUtils.Cookies;
import browserUtils.Resolution;
import frameworkUtils.Arguments;
import frameworkUtils.ReadProperties;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SearchResultsPage;
import setup.Browser;
import setup.Chrome;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TestCatsPhotos2 {
    public WebDriver driver;

    @BeforeTest
    public void setup() throws IOException, URISyntaxException {
        ReadProperties properties = new ReadProperties();
        Browser chrome = new Chrome();
        chrome.addArguments(Arguments.readArguments());
        chrome.disableAutomationInfobars();
        driver = chrome.launchRemoteBrowser(properties.getValue("http://localhost:4444"));
//        driver = chrome.launchBrowser();
        Resolution.maximize(driver);

    }

    @Test(priority = 1)
    public void testAcceptCookies() throws InterruptedException {

        driver.get("https://www.google.com");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies(true);

    }

    @Test(priority = 2)
    public void checkCookieCreated() {

        Cookie googleCookie = Cookies.getCookieWithName(driver, "NID"); //this checks cookie with such a name exists
        AssertJUnit.assertEquals("Wrong domain field", ".google.com", googleCookie.getDomain());
        AssertJUnit.assertTrue("Cookie value is not as expected", googleCookie.getValue().contains("513=")); //only the first part of the value can be checked as google's dynamic cookies
    }

    @Test(priority = 3)
    public void searchContinueWithEnter() {

        LandingPage.sendInputToSearch("Funny cat photos");
        LandingPage.pressEnterSearch();
    }

    @Test(priority = 4)
    public void checkTopPhotosResultsForCats()  {
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        List<WebElement> photos = searchPage.photosTopSixResults();
        if (photos.isEmpty()) {
            AssertJUnit.fail("No cat photos here!");
        }
        for (WebElement photo : photos) {
            AssertJUnit.assertTrue("No cats here!", photo.getAttribute("alt").toLowerCase().contains("cat"));
        }
    }

    @Test(priority = 5, dependsOnMethods = {"checkTopPhotosResultsForCats"})
    public void checkAllLinksContainCat() {
        List<WebElement> resultLinks = SearchResultsPage.getAllSearchResultTitles();
        for (int i = 0; i < resultLinks.size() - 1; i++) { // we cycle through all but one as the last one is never a search result link
            WebElement result = resultLinks.get(i);
            if(result.getText().isEmpty()){ //safe assumption as meaningful links won't have a length of 0
                continue;
            }
            System.out.println("Checking link index no " + i);
            AssertJUnit.assertTrue("No cats found in search result number " + i + " \nFull link text where the expected text was not found:" + "\n" + result.getText(),
                    result.getText().toLowerCase().contains("cat"));
        }

    }

    @AfterClass
    public void rundown() {
        driver.quit();
        Arguments.resetArguments();
    }

}

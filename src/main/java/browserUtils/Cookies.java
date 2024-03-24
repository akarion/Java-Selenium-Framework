package browserUtils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class Cookies {


    public static Cookie getCookieWithName(WebDriver driver, String name) {

        return driver.manage().getCookieNamed(name);
    }

    public static void addCookie(WebDriver driver, String key, String value) {

        driver.manage().addCookie(new Cookie(key, value));
    }

    public static void deleteCookieNamed(WebDriver driver, String name) {

        driver.manage().deleteCookieNamed(name);
    }


}

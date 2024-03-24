package browserUtils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class Resolution {

    public static void setStartResolution(WebDriver driver, int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public static void maximize(WebDriver driver) {
        driver.manage().window().maximize();
    }

}

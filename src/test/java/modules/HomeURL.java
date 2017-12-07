package modules;

import configuration.TestBase;
import libraries.HomeUrlFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import support.CommonFunctions;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class HomeURL extends TestBase {
    HomeUrlFunctions homeUrl = new HomeUrlFunctions();

    @Test  // check city and check food menu
    private void home01_checkFoodMenu(){
        CommonFunctions.pause(1);
        driver.findElement(By.linkText("OK")).click();
        CommonFunctions.pause(1);
        homeUrl.checkCity(driver);
    }

    public void home02_checkRestaurantName(){

    }



}

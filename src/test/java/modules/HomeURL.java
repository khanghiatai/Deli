package modules;

import configuration.TestBase;
import libraries.HomeUrlFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import support.CommonFunctions;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class HomeURL extends TestBase {
    HomeUrlFunctions homeUrl = new HomeUrlFunctions();

    @Test
    private void checkFoodMenu(){
        homeUrl.checkMenuFood(driver);
    }



}

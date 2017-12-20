package modules;

import configuration.TestBase;
import libraries.HomeUrlFunctions;
import libraries.SSOFunctions;
import objects.SSOPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import support.CommonFunctions;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class HomeURL extends TestBase {
    HomeUrlFunctions homeUrl = new HomeUrlFunctions();

    // check city and check food menu
    @Test
    private void home001_checkFoodMenu(){
        CommonFunctions.pause(1);
        driver.findElement(By.linkText("OK")).click();
        CommonFunctions.pause(1);
        homeUrl.checkCity(driver);
    }

    @Test
    private void home002_checkRestaurantName(){
        // Chose HCM city
        driver.findElement(By.xpath("//*[@data-activates='location-select']")).click();
        driver.findElement(By.xpath("//*[@id='location-select']/li/a[1]")).click();
        // Check random
        homeUrl.checkInfoRestaurant(driver);
        homeUrl.checkBreadcrum(driver);
    }

    @Test
    private void home003_CheckMenuLogin(){
        // login
        SSOFunctions sso = new SSOFunctions(driver);
        driver.findElement(By.className("btn-login")).click();
        CommonFunctions.pause(1);
        Assert.assertEquals(driver.getTitle(), "Login");
        sso.loginSSO(SSOPage.USERNAME,SSOPage.PASSWORD);
        // check menu login
        driver.findElement(By.xpath("//div[@id='login-status']/a")).click();
        homeUrl.checkMenuLogin(driver);
        driver.findElement(By.xpath("//div[@id='login-status']/a")).click();
    }

}

package libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import support.CommonFunctions;

import java.util.List;

public class HomeUrlFunctions {
    public void checkMenuFood(WebDriver driver){
        List<WebElement> el1 = driver.findElements(By.cssSelector(".top-cate a"));
        String strURL = "danh-sach-dia-diem-phuc-vu-";
        String strURL1 = "-giao-tan-noi";
        CommonFunctions.pause(1);
        driver.findElement(By.linkText("OK")).click();
        int i = 1;
        for (WebElement el: el1) {
            CommonFunctions.pause(1);
            boolean a = CommonFunctions.explicitWait(driver, "//*[@class='top-cate']/a["+ i +"]", 10);
            if(a == true) {
                driver.findElement(By.xpath("//*[@class='top-cate']/a[" + i +"]")).click();
            } else {
                Assert.assertEquals(1, 0);
                System.out.println("not found xpath: //*[@class='top-cate']/a[1]");
            }
            String sURL = driver.getCurrentUrl();
            int indexStr = sURL.indexOf("/d");
            sURL = sURL.substring(indexStr + 1, sURL.length());
            if(i <= 1){
                Assert.assertEquals(sURL, "danh-sach-dia-diem-giao-tan-noi");
            } if( i > 1) {
                String sUrl = driver.findElement(By.xpath("//*[@class='top-cate']/a[" + i +"]")).getText();
                String _url = CommonFunctions.covertStringToURL(sUrl);
                _url = strURL + _url + strURL1;
                Assert.assertEquals(sURL, _url);
            }
            i++;
        }
    }

    public void checkCity(WebDriver driver){
        CommonFunctions.pause(1);
        driver.findElement(By.linkText("OK")).click(); //delete
        driver.navigate().refresh();
        CommonFunctions.pause(1);
        //CommonFunctions.selectedByIndex(driver, "id", "location-select", 2);
        driver.findElement(By.xpath("//*[@data-activates='location-select']")).click();
        driver.findElement(By.xpath("//ul[@id='location-select']/li[3]")).click();
        //select.selectByIndex(2);
    }
}

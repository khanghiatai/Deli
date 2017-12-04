package libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import support.CommonFunctions;

import java.util.List;

public class HomeUrlFunctions {
    public void checkMenuFood(WebDriver driver){
        List<WebElement> el1 = driver.findElements(By.cssSelector(".top-cate a"));
        String strURL = "danh-sach-dia-diem-phuc-vu-";
        String strURL1 = "-giao-tan-noi";
        int i = 1;
        for (WebElement el: el1) {
            driver.findElement(By.linkText("OK")).click();//
            CommonFunctions.explicitWait(driver, "//*[@class='top-cate']/a[1]", 3);
            driver.findElement(By.xpath("//*[@class='top-cate']/a[1]")).click();
            String sURL = driver.getCurrentUrl();
            int indexStr = sURL.indexOf("/d");
            sURL = sURL.substring(indexStr + 1, sURL.length());
//            if(i > 1){
//                String str = el.getText();
//                String s = CommonFunctions.covertStringToURL(str);
//                System.out.println(strURL + s + strURL1);
//            } else {
//                Assert.assertEquals("", "danh-sach-dia-diem-giao-tan-noi");
//            }

            i++;
        }
    }
}

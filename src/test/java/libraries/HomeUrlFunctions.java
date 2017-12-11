package libraries;

import configuration.ResourceHasMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import support.CommonFunctions;

import java.util.List;
import java.util.Random;

public class HomeUrlFunctions {
    ResourceHasMap strResource = new ResourceHasMap();

    private void checkMenuFood(WebDriver driver) {
        CommonFunctions.pause(1);
        List<WebElement> el1 = driver.findElements(By.cssSelector(".top-cate a"));
        String strURL = "danh-sach-dia-diem-phuc-vu-";
        String strURL1 = "-giao-tan-noi";
        int i = 1;
        for (WebElement el : el1) {
            boolean a = CommonFunctions.explicitWait(driver, "//*[@class='top-cate']/a[" + i + "]", 10);
            if (a == true) {
                driver.findElement(By.xpath("//*[@class='top-cate']/a[" + i + "]")).click();
                CommonFunctions.pause(1);
            } else {
                Assert.assertEquals(1, 0);
            }
            String sURL = driver.getCurrentUrl();
            int indexStr = sURL.indexOf("/danh");
            sURL = sURL.substring(indexStr + 1, sURL.length());
            if (i <= 1) {
                CommonFunctions.pause(1);
                Assert.assertEquals(sURL, "danh-sach-dia-diem-giao-tan-noi");
            }
            if (i > 1) {
                CommonFunctions.pause(1);
                String sUrl = driver.findElement(By.xpath("//*[@class='top-cate']/a[" + i + "]")).getText();
                String _url = CommonFunctions.covertStringToURL(sUrl);
                _url = strURL + _url + strURL1;
                Assert.assertEquals(sURL, _url);
            }
            CommonFunctions.pause(1);
            i++;
        }
    }

    public void checkCity(WebDriver driver) {
        int iSize = driver.findElements(By.xpath("//ul[@id='location-select']/li")).size();
        String newUrl = "";
        for (int i = 1; i <= iSize; i++) {
            driver.findElement(By.xpath("//*[@data-activates='location-select']")).click();
            CommonFunctions.pause(1);
            String cityName = driver.findElement(By.xpath("//a[@data-activates='location-select']")).getText();
            String strURL = driver.getCurrentUrl();
            String subStr = strURL.substring(0, 12);
            if (subStr.equals("http://sandb")) {
                int startIndex = strURL.indexOf("1/");
                int endIndex = strURL.indexOf("/danh");
                if (endIndex > 0) {
                    newUrl = strURL.substring(startIndex + 2, endIndex);
                    if (!newUrl.equals("ho-chi-minh")) {
                        cityName = CommonFunctions.covertStringToURL(cityName);
                        cityName = cityName.substring(0, cityName.length() - 2);
                        Assert.assertEquals(cityName, newUrl);
                    }
                } else {
                    newUrl = strURL.substring(startIndex + 2, strURL.length());
                    if (cityName.equals(strResource.getResource("tphcm"))) {
                        Assert.assertEquals("http://sandbox.deliverynow.vn/", strURL);
                    }
                }
                System.out.println(newUrl);
            } else if (strURL.equals("https://www.")) {
                int startIndex = strURL.indexOf("n/");
                int endIndex = strURL.indexOf("/danh");
                if (endIndex > 0) {
                    newUrl = strURL.substring(startIndex + 2, endIndex);
                } else {
                    newUrl = strURL.substring(startIndex + 2, strURL.length());
                }
                System.out.println(newUrl);
            }
            driver.findElement(By.xpath("//ul[@id='location-select']/li[" + i + "]")).click();
            checkMenuFood(driver);
            CommonFunctions.pause(1);
        }
    }

    public int getRandomRestaurant(WebDriver driver) {
        List<WebElement> eImg = driver.findElements(By.xpath("//div[@class='img-hot-restaurant']/img"));
        int iSize = eImg.size();
        int indexItem = CommonFunctions.random(0, iSize);
        return indexItem;
        //WebElement eImgItem = driver.findElement(By.xpath("//div[@class='list-widget-hot-restaurant']/div[" + indexItem + "]//img"));
    }

    private String getResNameOnHome(WebDriver driver, int indexItem) {
        String sResName = driver.findElement(By.xpath("//div[@class='list-widget-hot-restaurant']/div[" + indexItem + "]//h4")).getText();
        return sResName;
    }

    private String getAdressOnHome(WebDriver driver, int indexItem) {
        String sAddress = driver.findElement(By.xpath("//div[@class='list-widget-hot-restaurant']/div[" + indexItem + "]//div[@class='home-res-address']")).getText();
        return sAddress;
    }

    private String getResNameOnMicrosite(WebDriver driver){
        String sName = driver.findElement(By.className("")).getText();
        sName = CommonFunctions.chuanHoa(sName);
        return sName;
    }

    private String getAddressOnMicrosite(WebDriver driver){
        String sAddress = driver.findElement(By.xpath("//p[@itemprop='description']")).getText();
        sAddress = CommonFunctions.chuanHoa(sAddress);
        return sAddress;
    }

}

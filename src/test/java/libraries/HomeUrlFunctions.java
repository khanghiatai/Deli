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

    public void checkInfoRestaurant(WebDriver driver){
        int indexItems = getRandomRestaurant(driver);
        String resName = getResNameOnHome(driver, indexItems);
        String address = getAdressOnHome(driver, indexItems);
        // move to microsite
        driver.findElement(By.xpath("//*[@class='top-res-order']/div[@class='widget-hot-restaurant']["+ indexItems +"]/div/div/a")).click();
        CommonFunctions.switchToTab(driver, 1);
        String resNameMsite = getResNameOnMicrosite(driver);
        String addressMsite = getAddressOnMicrosite(driver);
        String cityName = getCityOnHome(driver);
        Assert.assertEquals(resName,resNameMsite);
        //address = address + ", " + cityName;
        Assert.assertEquals(address, addressMsite);
    }

    public void checkBreadcrum(WebDriver driver){
        String sBreadCrumb = driver.findElement(By.xpath("//*[@class='breadcrum-microsite']")).getText();
        String cityName = getCityOnHome(driver);
        String sResName = getResNameOnMicrosite(driver);
        String _breadCrumb = strResource.getResource("trangchu") + " » " + cityName+ " » " + sResName;
        Assert.assertEquals(_breadCrumb.toLowerCase(), sBreadCrumb.toLowerCase());
    }

    public void checkMenuLogin(WebDriver driver){
        List<WebElement> listNav = driver.findElements(By.xpath("//div[@id='login-status']/div/a/span[@class='text']"));
        Boolean isNav = false;
        for (WebElement el:listNav) {
            String sNav = el.getText();
            String _nav = "";
            for (int j=1; j<=5; j++){ // total rows int nav = 5 in resource
                String sIndex = Integer.toString(j);
                _nav = strResource.getResource(sIndex);
                if(sNav.equals(_nav)){
                    Assert.assertEquals(sNav, _nav);
                    isNav = true;
                    j = 5;
                }
            }
            Assert.assertEquals(java.util.Optional.of(true), isNav);
        }
    }

    /*** private functions **********************************/
    private int getRandomRestaurant(WebDriver driver) {
        List<WebElement> eImg = driver.findElements(By.xpath("//div[@class='widget-hot-restaurant']//img"));
        int iSize = eImg.size();
        int indexItem = CommonFunctions.random(1, iSize);
        return indexItem;
    }

    private String getResNameOnHome(WebDriver driver, int indexItem) {
        String sResName = driver.findElement(By.xpath("//div[@class='widget-hot-restaurant'][" + indexItem + "]//h4/a")).getText();
        return sResName;
    }

    private String getAdressOnHome(WebDriver driver, int indexItem) {
        String sAddress = driver.findElement(By.xpath("//div[@class='widget-hot-restaurant'][" + indexItem + "]//div[@class='home-res-address']")).getText();
        return sAddress;
    }

    private String getResNameOnMicrosite(WebDriver driver){
        String sName = driver.findElement(By.xpath("//*[@class='name-hot-restaurant']")).getText();
        sName = CommonFunctions.chuanHoa(sName);
        return sName;
    }

    private String getAddressOnMicrosite(WebDriver driver){
        String sAddress = driver.findElement(By.xpath("//*[@itemprop='description']")).getText();
        sAddress = CommonFunctions.chuanHoa(sAddress);
        return sAddress;
    }

    private String getCityOnHome(WebDriver driver){
        String cityName = driver.findElement(By.xpath("//*[@data-activates='location-select']")).getText();
        cityName = CommonFunctions.chuanHoa(cityName);
        cityName = CommonFunctions.chuanHoa(cityName.substring(0, cityName.length()-1));
        return cityName;
    }
}

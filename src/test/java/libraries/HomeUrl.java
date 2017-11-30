package libraries;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeUrl {
    public String isLinkBroken(URL url) throws Exception {
        // url = "http://facebook.com";
        String response = "";
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            connection.connect();
            response = connection.getResponseMessage();
            connection.disconnect();
            return response;
        } catch (Exception exp){
            return exp.getMessage();
        }
    }

    public List findAllLinks(WebDriver driver){
        List<WebElement> elementList = new ArrayList<>();
        elementList  = driver.findElements(By.tagName("a"));
        elementList.addAll(driver.findElements(By.tagName("img")));

        List<WebElement> finalList = new ArrayList<>();
        for (WebElement element: elementList) {
            if(!element.getAttribute("href").equals(null)){
                finalList.add(element);
            }
        }
        return finalList;
    }

    public static void main(String[] agrs){
        System.setProperty("webdriver.chrome.driver", "F:\\Project\\project\\libs\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("sandbox.deliverynow.vn:8081");

    }
}

package configuration;


import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import support.CommonFunctions;

public class TestBase {

	public static WebDriver driver;

    @Parameters({"browserName"})
    @BeforeTest
    public static void init(String browserName) throws MalformedURLException{
        driver = CommonFunctions.getBrowser(browserName);
    }

    @Parameters({"url"})
    @BeforeMethod
    public static void setUp(String url){
        //CommonFunctions.visit(driver, url);      
    }
    
    @DataProvider(name = "ssodata")
    public Object[][] ssoData(){
    	return new Object[][]{
    		new Object[] {"tai"," ","No Pass"},
    		new Object[] {" ","123","No User"},
    		new Object[] {"tai","123", "Email hoặc Mật khẩu không chính xác!"},
    		new Object[] {"tai.kha","123123", " "}
    	};
    }    
    
    @DataProvider(name = "ssodataxss")
    public Object[][] ssoDataXSS(){
    	return new Object[][]{    		
    		new Object[] {"<script>alert('hello Dev')</script>"," ", "Username hoặc Email không hợp lệ!"},
    		new Object[] {" ","<script>alert('hello Dev')</script>", "Mật khẩu không hợp lệ!"},
    		new Object[] {"<script>alert('hello Dev')</script>","<script>alert('hello Dev')</script>", "Username hoặc Email không hợp lệ!"},
    	};
    }

    @AfterMethod
    public void takeScreenShotIfFailure(ITestResult testResult) throws IOException {
        String screenShotFile;
        //Date date = new Date();

        //Create format date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        //Create the file name with date time format then grant to "screenShotFile"
        screenShotFile =System.getProperty("user.dir")+"/images/"+ "TestScreenShot"+ dateFormat + ".png";

        if (testResult.getStatus() == ITestResult.FAILURE){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenShotFile));
        }
    }

    @AfterTest
    public void tearDown(){   
    	CommonFunctions.pause(2);
        driver.quit();
    }
}

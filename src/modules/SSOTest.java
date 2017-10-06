package modules;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
//import org.sikuli.script.Screen;
//import org.openqa.selenium.By;
//import org.sikuli.script.Screen;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import libraries.SSOFunctions;
import support.CommonFunctions;

public class SSOTest{
	SSOFunctions sso;
	public static WebDriver driver;
	
	@Parameters({"url", "browserType"})
    @BeforeTest
    public static void setUp(String url, String browserType) throws MalformedURLException{ 
	
		driver = CommonFunctions.getBrowser(browserType);

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        CommonFunctions.visit(driver, url); 
    }
	
	
	@Test(priority = 1)
	public void VisitWeb() throws InterruptedException{		
		sso = new SSOFunctions(driver);		
		sso.btn_OK.click();
		
		Thread.sleep(3000);		
		sso.href_OK.click(); 		
		
		Thread.sleep(2000);
		sso.btn_login.click();
		
		/*Screen g = new Screen();
		try {			
			g.click("D:\\click.png");	
		} catch (Exception e) {
			System.out.println("Khong tim thay hinh");
		}		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
				
	}	
	
	@Test(priority = 2, dataProvider = "ssodataxss")
	public void TC001_Login(String user, String pass, String lblNofification){
		sso.loginSSO(user, pass);	
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		alert.accept();
		AssertJUnit.assertEquals(message, lblNofification);	
	}
	
	@Test(priority = 3, dataProvider = "ssodata")
	public void TC002_Login(String user, String pass, String lblNofification) throws InterruptedException{
		sso = new SSOFunctions(driver);	
		sso.loginSSO(user, pass);
	
		if(lblNofification.isEmpty() || lblNofification.equalsIgnoreCase(" ")){			
			System.out.println(driver.getTitle());
		}
	}
	
	/*
	@Test(priority = 4)
	public void getUserName(){
		sso.getUserName();
		driver.navigate().back();
		driver.findElement(By.id("delivery-header-logo")).click();
	}*/
	
	@Test(priority = 5)
	public void logout(){
		sso.logout();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void teardow(){
		driver.quit();
	}
	
}

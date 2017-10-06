package modules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import libraries.OrderFunctions;
import libraries.OrderGroupFunctions;
import libraries.SSOFunctions;
import libraries.SearchFunctions;
import objects.SSOPage;
import objects.SearchPage;
import support.CommonFunctions;


public class OrderGroupTest {
	public static WebDriver driver; 
	SSOFunctions sso = new SSOFunctions(driver);
	OrderGroupFunctions group = new OrderGroupFunctions(driver);
	SearchFunctions search;
	
	@Parameters({ "url", "browserName" })
	@BeforeClass
	public static void setUp(String url, String browserName) throws Exception { 
		try {
			driver = CommonFunctions.getBrowser(browserName);		
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			CommonFunctions.visit(driver, url);
		} catch (Exception e) {
			driver.navigate().refresh();
		}		
	}
	
	@Test
	public void group001_checkGroupBlock(){
		loginBeforeTest(driver);
		WebElement img_Result = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('.box-photo-restaurant');");
		img_Result.click();
		
		CommonFunctions.switchToTab(driver, 1);
		group.checkBlockOrderGroup(driver);
	}
	
	@Test
	public void group002_checkPopupOrder(){
		group.checkPopupShareLink(driver);		
		OrderFunctions order = new OrderFunctions(driver);
		order.checkCartInfo(driver, "0", "0"); 
	}
		
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
	private void loginBeforeTest(WebDriver driver){
		sso = new SSOFunctions(driver);		
		CommonFunctions.pause(1);
		//sso.btn_OK.click();		
		//CommonFunctions.pause(1);		
		
		WebElement link_OK = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('.modal-action');");
		link_OK.click();	
		
		CommonFunctions.pause(1);
		sso.btn_login.click();
		sso.loginSSO(SSOPage.USERNAME, SSOPage.PASSWORD);
		search = new SearchFunctions(driver);
		search.search(SearchPage.keySearch_Default);
	} 
	
}
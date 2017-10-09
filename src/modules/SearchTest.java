package modules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
//import org.sikuli.script.Screen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configuration.TestBase;
import libraries.SSOFunctions;
import libraries.SearchFunctions;
import objects.SearchPage;
import support.CommonFunctions;

public class SearchTest extends TestBase {
	SearchFunctions search;
	SSOFunctions sso;

	@Parameters({ "url" })
	@BeforeClass
	public static void setUp(String url) {
		try {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			CommonFunctions.visit(driver, url);		
		} catch (Exception e) {
			driver.navigate().refresh();
		}		
	}
	
	@Test
	public void getWeb() {
		CommonFunctions.pause(1);
		WebElement link_OK = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.modal-action');");
		link_OK.click();
	}

	@Test
	public void search(){
		try {
			search = new SearchFunctions(driver);
			search.search(SearchPage.keySearch_Default);
		} catch (Exception e) {
			driver.navigate().refresh();
		}		
		CommonFunctions.pause(1);			
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("return document.querySelector('.box-photo-restaurant').click();");
		// cho vong lap, kiem tra dung ten quan thi click
		js.executeScript("document.querySelector('.box-photo-restaurant img').click()");		
	}	

}

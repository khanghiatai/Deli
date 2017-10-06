package libraries;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import objects.SearchPage;
import support.CommonFunctions;

public class SearchFunctions extends SearchPage {

	WebDriver dirver;
	public SearchFunctions(WebDriver driver) {
		super(driver);
	}

	public void search(String srtingQuery) {
		txt_Search.clear();
		txt_Search.sendKeys(srtingQuery);

		CommonFunctions.pause(1);
		btn_Search.click();
	}

	public void getWeb(WebDriver driver) {
		// CommonFunctions.pause(1);
		// sso = new SSOFunctions(driver);
		// sso.btn_OK.click();
		CommonFunctions.pause(1);
		WebElement link_OK = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.modal-action');");
		link_OK.click();
	}	

	public void autoCompleteSeach(WebDriver driver, String srtingQuery){
		txt_Search.clear();
		txt_Search.sendKeys(srtingQuery);

		CommonFunctions.pause(1);
		WebElement autoSearch = (WebElement)((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.info-restaurant-search span.capitalize');");
		AssertJUnit.assertEquals(autoSearch.getText(), srtingQuery);
		autoSearch.click();	
		CommonFunctions.pause(1);
	}
	
}

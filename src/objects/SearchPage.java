package objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchPage {
	protected static WebDriver driver;
	
	public SearchPage(WebDriver driver){
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,100);
		PageFactory.initElements(factory, this);
	}
	
	public static String keySearch_Default = "High";    // High - 186 - Chả Hoa & Bánh Tét Trà Cuôn
	public static String keySearch_CheckOrderPrice = "Bánh Canh Siêu Ngon";  //Bánh Canh Siêu Ngon - Bánh Su Que - Nguyễn Trãi
	public static String keySearch_Service = "Sherry Cake & Snack"; 
	public static String keySeach_NoDeli  = "QA Cafe - Thành Thái";
	// Bánh Su Que - Nguyễn Trãi
	// Bánh Tét Ngon - Shop Online
	// Sài Gòn Ơi Cafe - Nguyễn Huệ  -- Quán tự giao
	
	public static String priceRequire = "25,000";
	
	@FindBy(id = "hs")
	public WebElement txt_Search;
	
	@FindBy(xpath = ".//form[@id='searchFormTop']/div/a")
	public WebElement btn_Search; 		   

}

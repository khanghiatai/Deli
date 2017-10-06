package objects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrderFoodPage {
	public static WebDriver driver;
	public OrderFoodPage( WebDriver driver){
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,100);
		PageFactory.initElements(factory, this); 
	}	
	
	public WebDriver get(WebDriver driver){
		return driver;
	}
	
	
}

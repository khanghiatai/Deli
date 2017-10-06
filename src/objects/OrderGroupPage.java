package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class OrderGroupPage {

	public WebDriver driver;
	public OrderGroupPage(WebDriver driver){
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,100);
		PageFactory.initElements(factory, this);
	}
	
	@FindBy(xpath = ".//span[@class='close']")
	public WebElement span_Close;
			
	@FindBy(css = ".btn-order-group")
	public WebElement btn_OrderGroup;
	// .btn-order-group
	
}

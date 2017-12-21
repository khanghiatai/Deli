package objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrderPage {
	
	public WebDriver driver;
	public OrderPage (WebDriver driver){
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        PageFactory.initElements(factory,this);
	}
	
	@FindBy(xpath = ".//div[@class='scrollspy']/div[1]/div[2]/a/h3")
	public WebElement btn_Adding;
	
	// so phan
	@FindBy(id = "cart-qty")
	public WebElement lbl_Cart;
	
	// text Phan
	@FindBy(xpath = ".//a[@class='cart-stats']/span[2]")
	public WebElement lbl_Order;	
	
	// so Nguoi
	@FindBy(xpath = ".//a[@class='cart-stats']/span[4]")
	public WebElement lbl_CountPerson;
	
	// text Nguoi
	@FindBy(xpath = ".//a[@class='cart-stats']/span[5]")
	public WebElement lbl_Person;
	
	@FindBy(xpath = ".//div[@id='alert-container']/div/p")
	public WebElement lbl_PopupTitle;
	
	@FindBy(id = "alert-msg")
	public WebElement lbl_PopupMessage;
	
	@FindBy(xpath = ".//div[@id='alert-modal']/div/a")
	public WebElement btn_Oke;
	

	@FindBy(id = "delivery-header-logo")
	public WebElement img_Logo;
	
	@FindBy(xpath = ".//div[contains(@class, 'row-bill-grey')]/span[.='Cộng']")
	public WebElement lbl_Cong; 
	
	@FindBys(@FindBy(xpath = ".//*[@class='scrollspy']//a[@class='title-name-food']"))
	public List<WebElement> btn_Order;
	
}

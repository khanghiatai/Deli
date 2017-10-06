package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
	
	WebDriver driver;
	public OrderPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	
}

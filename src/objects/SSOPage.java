package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SSOPage {
	
	public WebDriver driver;
	
	public SSOPage(WebDriver driver){
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,100);
		PageFactory.initElements(factory, this);		
	}
	
	public static final String USERNAME = "khanghiatai@gmail.com";
	public static final String PASSWORD = "123123";
	public static String USERPROFILE;
	
	@FindBy(id = "Email")
	public WebElement txtUser; 
	
	@FindBy(id = "Password")
	public WebElement txtPass;
	
	@FindBy(id = "bt_submit")
	public WebElement btn_DangNhap;
	
	@FindBy(id = "Email-error")
	public WebElement lbl_EmailNull;
	
	@FindBy(id = "Password-error")
	public WebElement lbl_PassNull;
	
	@FindBy(xpath = ".//button[@class='close']")
	public WebElement lbl_EmailOrPass;	
	
	@FindBy(id = "okIsee")
	public WebElement btn_OK;
	
	@FindBy(className = ".white")
	public WebElement web_Click;
	
	@FindBy(linkText = "OK")
	public WebElement href_OK;
	
	@FindBy(xpath = "//*[@id='login-status']/a")
	public WebElement btn_login;
	
	@FindBy(css = ".name-user")
	public WebElement user;
	
	@FindBy(css = ".profile-usertitle-name")
	public WebElement lbl_UserProfile; 
	
	@FindBy(id = "delivery-header-logo")
	public WebElement img_logo; 
}

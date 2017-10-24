package libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import objects.SSOPage;
import support.CommonFunctions;

public class SSOFunctions extends SSOPage{
	public SSOFunctions(WebDriver driver) {
		super(driver);
	}
	
	public void loginSSO(String user, String pass){
		txtUser.clear();
		txtUser.sendKeys(user);
		
		txtPass.clear();
		txtPass.sendKeys(pass);
		
		btn_DangNhap.click();		
	}
	
	/*public void logout(){
		user.click();
		btn_logout.click();		
		AssertJUnit.assertEquals("", btn_login.getText());
	}
	
	public void getUserName(WebDriver driver){
		try {
			user.click();
			btn_UpdateAccount.click();
		} catch (Exception e) {
			driver.navigate().refresh();
		} finally {
			CommonFunctions.pause(1);
			lnk_UpdateAccount.click();	
			CommonFunctions.pause(1);		
			USERPROFILE = lbl_UserProfile.getText();
			driver.navigate().back();
			driver.navigate().back();
		}
	}*/
	
	/***
	 * Check full name of user
	 * @param how: javarscript or id, name, xpath, css
	 * @param locator: css or id, name, xpath, css
	 */
	public void checkUserProfile(WebDriver driver, String how, String locator){
		CommonFunctions.pause(1);
		try {
			String UserName = null; 
			if(how.equalsIgnoreCase("id")) {
				UserName = driver.findElement(By.id("locator")).getText();
			} else if(how.equalsIgnoreCase("name")) {
				UserName = driver.findElement(By.name("locator")).getText();
			} else if(how.equalsIgnoreCase("xpath")) {
				UserName = driver.findElement(By.name("locator")).getText();
			} else if(how.equalsIgnoreCase("css")) {
				UserName = driver.findElement(By.name("locator")).getText();
			}  else if(how.equalsIgnoreCase("javarscript")) {
				UserName = (String)((JavascriptExecutor)driver)
						.executeScript("return document.querySelector('" + locator + "');");
			}
			AssertJUnit.assertEquals(USERPROFILE, UserName);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}

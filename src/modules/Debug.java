package modules;

import java.net.MalformedURLException;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import libraries.LoginFunctions;
import support.CommonFunctions;

public class Debug {

	public static String URL = "sandbox.tablenow.vn:9000";
	public static String BROWSER = "firefox";
	public static int TIMEOUT = 60;

	public static void main(String[] args) throws MalformedURLException {		
		WebDriver driver = CommonFunctions.getBrowser(BROWSER);
		CommonFunctions.visit(driver, URL); 
		

    	
//		CommonFunctions.waitForElementPresent(driver, "xpath", ".validation-summary-errors>ul>li", 10);
//		String show = driver.findElement(By.cssSelector(".validation-summary-errors>ul>li")).getText();
//		System.out.println(show);
	}
}


////Code to take Screenshot
//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//// Code to save screenshot at desired location
//FileUtils.copyFile(scrFile, new File("c:\\screenshot.png"));
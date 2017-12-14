package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.CommonFunctions;

import java.net.MalformedURLException;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
//		int a = CommonFunctions.getNumberOfString("djhsdj  232133 99");
//		System.out.println(a);
		WebDriver driver = CommonFunctions.getBrowser("chrome");
		CommonFunctions.visit(driver, "http://store.demoqa.com/");
		driver.findElement(By.xpath("//div[@id='account']/a")).click();
		driver.findElement(By.xpath("//div[@id='login_form']//label/input")).sendKeys("tuancn");
		driver.findElement(By.xpath("//div[@id='login_form']//label//*[@id= 'pwd']")).sendKeys("w$LK$CeZ5bYzNiDV");
		driver.findElement(By.id("login")).click();
		try {Thread.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
		driver.findElement(By.xpath("//*[@id='menu-item-15']/a")).click();
		System.out.print("clicked");

	}
}

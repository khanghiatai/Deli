package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import support.CommonFunctions;

public class TestNG {
	WebDriver driver;
	@BeforeTest
	public void a() {
		System.setProperty("webdriver.chrome.driver", "F:\\Project\\project\\libs\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("http://store.demoqa.com/");
	}
	
	@Test
	public void f(){
		driver.findElement(By.xpath("//div[@id='account']/a")).click();
		driver.findElement(By.xpath("//div[@id='login_form']//label/input")).sendKeys("tuancn");
		driver.findElement(By.xpath("//div[@id='login_form']//label//*[@id= 'pwd']")).sendKeys("aaa");
		CommonFunctions.pause(3);
		driver.findElement(By.id("login")).click();
		CommonFunctions.pause(3);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sText = js.executeScript("return document.querySelectorAll('#ajax_loginform p strong')[0].innerText;").toString();
		System.out.println(sText);



		/*List<WebElement> el = (List<WebElement>) ((JavascriptExecutor)driver)
				.executeScript("return document.querySelectorAll('.more-info .adding-food-cart .btn-adding');");
				
		for (WebElement webElement : el) {
			System.out.println("c1");
		}*/

	}
	
	@AfterTest
	public void c() {
		System.out.println("c");
	}
}

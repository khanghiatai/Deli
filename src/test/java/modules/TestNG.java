package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {
	WebDriver driver;
	@BeforeTest
	public void a() {
		System.setProperty("webdriver.chrome.driver", "F:\\Project\\project\\libs\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("http://sandbox.deliverynow.vn:8081/ho-chi-minh/highland-coffee-tran-hung-dao-26728");
	}
	
	@Test
	public void f(){
		driver.findElement(By.xpath(".//*[@ng-show='detailCtrl.isHost' and @class='btn-book-first']")).click();
		
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

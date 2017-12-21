package modules;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;


public class TestNG {
	WebDriver driver;
	@BeforeClass
	public void a(){
		// code
		String exePath = "F:\\NowServices\\NowDelivery\\Driver\\chromedriver.exe";// duong dan file chromedriver
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver( options );
		driver.get("http://store.demoqa.com/");
	}

//	@Test
//	public void d() throws InterruptedException {
//		driver.findElement(By.xpath("//div[@id='account']/a")).click();
//		Thread.sleep(3);
//		driver.findElement(By.xpath("//div[@id='login_form']//label/input")).sendKeys("tuancn");
//		driver.findElement(By.xpath("//div[@id='login_form']//label//*[@id= 'pwd']")).sendKeys("w$LK$CeZ5bYzNiDV");
//		Thread.sleep(5);
//		driver.findElement(By.id("login")).click();
//		Thread.sleep(3);
//		driver.navigate().refresh();
//		driver.findElement(By.xpath("//*[@id='menu-item-15']/a")).click();
//		System.out.println("login successful, pass case 1");
//
//	}
//	@Test
//	public void e() throws InterruptedException {
//		driver.findElement(By.xpath("//div[@id='account']/a")).click();
//		Thread.sleep(3);
//		driver.findElement(By.linkText("Log out")).click();
//		Thread.sleep(2);
//		driver.get("http://store.demoqa.com/");
//	}

	@Test
	public void f() throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='account']/a")).click();
		driver.findElement(By.xpath("//div[@id='login_form']//label/input")).clear();
		driver.findElement(By.xpath("//div[@id='login_form']//label/input")).sendKeys("tuancn");
		driver.findElement(By.xpath("//div[@id='login_form']//label//*[@id= 'pwd']")).clear();
		driver.findElement(By.xpath("//div[@id='login_form']//label//*[@id= 'pwd']")).sendKeys("aaa");
		pause(5);
		driver.findElement(By.id("login")).click();
		pause(5);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sText = js.executeScript("return document.querySelectorAll('#ajax_loginform p strong')[0].innerText;").toString();
		System.out.println(sText);
		if(sText.equals("ERROR")){
			System.out.print("Loi! thong tin dang nhap khong chinh xac");	}	// show message
		else { System.out.println("login successful"); }
		System.out.println("login fail, pass case 2");
	}

	public static void pause(int seconds) {
		Date start = new Date();
		Date end = new Date();
		while (end.getTime() - start.getTime() < seconds * 1000) {
			end = new Date();
		}
	}

	@AfterClass
	public void b(){
		//driver.close();
	}


}

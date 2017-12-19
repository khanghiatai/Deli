package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.CommonFunctions;

import java.net.MalformedURLException;

public class Main {

	public static void main(String[] args){
//		int a = CommonFunctions.getNumberOfString("djhsdj  232133 99");
//		System.out.println(a);
//		WebDriver driver = CommonFunctions.getBrowser("chrome");
//		CommonFunctions.visit(driver, "http://store.demoqa.com/");
//		driver.findElement(By.xpath("//div[@id='account']/a")).click();
//		driver.findElement(By.xpath("//div[@id='login_form']//label/input")).sendKeys("tuancn");
//		driver.findElement(By.xpath("//div[@id='login_form']//label//*[@id= 'pwd']")).sendKeys("w$LK$CeZ5bYzNiDV");
//		driver.findElement(By.id("login")).click();
//		try {Thread.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
//		driver.findElement(By.xpath("//*[@id='menu-item-15']/a")).click();
//		System.out.print("clicked");
		float von = 1020;
		float tiendu = 0;
		float reinvest = 0;
		float loinhuan = 0;
		float ti_le = (float) 1.0;
		for(int i = 1; i <= 300 ; i++){
			if (i == 300) {
				von += 1010;
			}
			float temp = (von * ti_le)/100;
			tiendu = tiendu + temp;
			loinhuan = tiendu;
			temp = tiendu %10;
			if (tiendu %10 > 0){
				reinvest = tiendu - temp;
				tiendu = tiendu - reinvest;
			}
			von = von + reinvest;
			System.out.print("- Day: " + i);
			System.out.print(" - von: " + von);
			System.out.print(" - loi nhuan: " +loinhuan);
			System.out.print(" - reinvest: " + reinvest);
			System.out.println(" - tiendu: " + tiendu);
		}
	}
}

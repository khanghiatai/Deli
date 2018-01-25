package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.CommonFunctions;

import java.net.MalformedURLException;
import java.text.DecimalFormat;

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
		float von = 2200; //
		float tiendu = 0;
		float reinvest = 0;
		float loinhuan = 0;
		float ti_le = (float) 1.0;
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		for(int i = 1; i <= 300 ; i++){
			// run if i = n
//			if (i == 60) {
//				von += 1100;
//			} if (i == 120) {
//				von += 1100;
//			}

			float temp = (von * ti_le)/100;
			tiendu = tiendu + temp;
			loinhuan = tiendu;
			temp = tiendu %10;
			if (tiendu %10 > 0){
				reinvest = tiendu - temp;
				tiendu = tiendu - reinvest;
			}
			von = von + reinvest;

			System.out.print("Day: " + i);
			System.out.print(" - Von: " + von);
			System.out.print(" - Loi nhuan: " + formatter.format(loinhuan));
			System.out.print(" - Reinvest: " + reinvest);
			System.out.println(" - Tien du: " + formatter.format(tiendu));
		}
	}
}

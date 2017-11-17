package modules;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import configuration.ResourceHasMap;
import configuration.TestBase;
import libraries.OrderFunctions;
import libraries.SSOFunctions;
import support.CommonFunctions;

@Test
public class OrderTest_1 {
	ResourceHasMap resource = new ResourceHasMap();
	OrderFunctions order = new OrderFunctions(TestBase.driver);		
	SSOFunctions sso = new SSOFunctions(TestBase.driver);
	int defaultPrice = 100000;
	String strResName;
	String strAddress;

	//@Test
	public void order001_CheckCartInfoNoLogin(){
		// Select tab browser present
		//CommonFunctions.switchToTab(TestBase.driver, 1);
		
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "0";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}

	//@Test
	public void order002_OrderNoLogin(){		
		order.clickButtonOrder(TestBase.driver);		
		order.checkPopup(TestBase.driver, resource.getResource("titleMesg"), resource.getResource("contentMesg"), resource.getResource("buttonName")); 
	}
	
	//@Test 
	public void order003_AddMenuNoLogin(){		
		order.addMenuNoLogin(TestBase.driver);
	}

	@SuppressWarnings("static-access")
	//@Test
	public void order004_OrderNoMenu(){		
		try {
			if (order.isLoginPage(TestBase.driver) == true){			
				sso = new SSOFunctions(TestBase.driver);
				sso.loginSSO(sso.USERNAME, sso.PASSWORD);											
			} 
			 
		} catch (Exception e) {
			TestBase.driver.navigate().refresh();
		} finally {
			CommonFunctions.pause(1);
			if (order.isLoginPage(TestBase.driver) == true){			
				sso = new SSOFunctions(TestBase.driver);
				sso.loginSSO(sso.USERNAME, sso.PASSWORD);			
				//sso.getUserName(TestBase.driver); 								
			} 
			// temp //
			CommonFunctions.pause(5);
			order.clickButtonOrder(TestBase.driver);	
			order.checkPopup(TestBase.driver, resource.getResource("titleMesg"), resource.getResource("contentMesg"), resource.getResource("buttonName"));
		}		
	}

	
	
	// viet tu ham nay chuyen qua ordertest.java
	//@Test
	public void order005_AddMenuAfterLogin(){	
		// add food		
		order.clickNameFood1(TestBase.driver);		
		
		// check price		
		order.checkPrice(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);		
	}		

	
	//@Test 
	public void order016_CancelOrderBelowPrice() {
		//TestBase.driver.navigate().back();
		// click 1 food, return price of food
		int ibelowPrice = order.addOneOrderBelowPrice(TestBase.driver, defaultPrice);
		order.clickButtonOrder(TestBase.driver); 
		order.checkPopupBelowPrice(TestBase.driver, defaultPrice, ibelowPrice);	
		order.clickCancelOrderBelowPrice(TestBase.driver);		
		TestBase.driver.navigate().refresh();
	}
	
	public void order017_ContinueOrderBelowPrice() {
		order.clickButtonOrder(TestBase.driver); 
		if(order.countCarItems(TestBase.driver) > 0) {
			order.clickButtonOrder(TestBase.driver); 
			TestBase.driver.findElement(By.xpath("//label[@for='continute-rad']")).click();	
			TestBase.driver.findElement(By.xpath("//*[@id='alert-modal']/div[2]/a[2]")).click();
		} else Assert.assertEquals(0, 1); // miss order
	}
	
	
	public void order018_AFC() {
		TestBase.driver.findElement(By.xpath("//input[@ng-model='detailCtrl.deliveredDate']")).click();
		
	}
	
	/*public void order018_OrderBelowPrice() {
		order.clickButtonOrder(TestBase.driver); 
		if(order.countCarItems(TestBase.driver) > 0) {
			order.clickButtonOrder(TestBase.driver); 
			TestBase.driver.findElement(By.xpath("//label[@for='accept-rad']")).click();	
			TestBase.driver.findElement(By.xpath("//*[@id='alert-modal']/div[2]/a[2]")).click();
		} else Assert.assertEquals(0, 1); // miss order
	}	*/
	
}

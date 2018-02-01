package modules;

import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import configuration.ResourceHasMap;
import configuration.TestBase;
import libraries.OrderFunctions;
import libraries.SSOFunctions;
import support.CommonFunctions;

public class OrderTest {
	ResourceHasMap resource = new ResourceHasMap();
	OrderFunctions order = new OrderFunctions(TestBase.driver);		
	SSOFunctions sso = new SSOFunctions(TestBase.driver);
	int defaultPrice = 50000;
	String strResName;
	String strAddress;

	@Test
	public void order001_CheckCartInfoNoLogin(){
		// Select tab browser present
		//CommonFunctions.switchToTab(TestBase.driver, 1);
		
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "0";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}

	@Test
	public void order002_OrderNoLogin(){		
		order.clickButtonOrder(TestBase.driver);		
		order.checkPopup(TestBase.driver, resource.getResource("titleMesg"), resource.getResource("contentMesg"), resource.getResource("buttonName")); 
	}
	
	@Test 
	public void order003_AddMenuNoLogin(){		
		order.addMenuNoLogin(TestBase.driver);
	}

	@SuppressWarnings("static-access")
	@Test
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

	@Test
	public void order005_AddMenuAfterLogin(){	
		// add food		
		order.clickNameFood(TestBase.driver);
		// check price		
		order.checkPrice(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}		

	@Test
	public void order006_CheckNote(){
		order.checkNote(TestBase.driver, resource.getResource("orderNoted"));
	}			


	@Test
	public void order007_AddCart(){
		order.addCart(TestBase.driver);		
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}	
	
	@Test
	public void order008_RemoveCart(){
		order.removeCart(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}		

	@Test
	public void order009_ResetAndCheckInfo(){
		order.resetOrder(TestBase.driver); 				
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "0";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}		


	@Test 
	public void order010_AddMenuAfterReset(){		
		order.clickNameFood(TestBase.driver); 
		order.checkNote(TestBase.driver, resource.getResource("orderNoted"));
		order.checkPrice(TestBase.driver);				
	}	
	
	@Test 
	public void order011_CheckPopupOrderPrice(){	
		strResName = order.getRestaurantName(TestBase.driver);
		strAddress = order.getRestaurantAddress(TestBase.driver);
		order.clickButtonOrder(TestBase.driver); 
		order.checkPopupConfirmOrder(TestBase.driver, strResName, strAddress);
	}


	@Test
	public void order012_ConfirmOrder() {
		boolean bAddress = order.isHasAddressUser(TestBase.driver);
		if(bAddress == false) {
			order.insertAddress(TestBase.driver, resource.getResource("username"),  
								resource.getResource("useraddress"),  resource.getResource("userphone"),  resource.getResource("usernote"));
			order.clickContinueOrder(TestBase.driver);	
			order.checkPopupOrderInfo(TestBase.driver);
		} else {
			order.clickContinueOrder(TestBase.driver);	
			order.checkPopupOrderInfo(TestBase.driver);
		}
	}

	@Test
	public void order013_checkBackPrevious() { 
		order.clickBackPreviosPopup(TestBase.driver);
		CommonFunctions.selectedByIndex(TestBase.driver, "xpath", "//*[@ng-model='detailCtrl.deliveryHour']", 0); 
		order.clickContinueOrder(TestBase.driver);			
		order.checkPopupOrderInfo(TestBase.driver);
	}	

	@Test
	public void order014_OrderSuccess() {
		order.clickFinishOrder(TestBase.driver); 
		order.checkPopupFinishOrder(TestBase.driver);
		order.clickWaitConfirm(TestBase.driver);
		order.checkInfoAfterOrder(TestBase.driver, strResName, strAddress);
	}

	@Test
	public void order015_CheckIncomming() {
		TestBase.driver.findElement(By.xpath("//*[@title='deliveryNow.vn']")).click();
		order.checkIncomming(TestBase.driver, strResName, strAddress); 
		// ******* Chua xong 
	}


	/*
	@Test
	public void order016_CancelOrderBelowPrice() {
		//TestBase.driver.navigate().back();
		// click 1 food, return price of food
		int ibelowPrice = order.addOneOrderBelowPrice(TestBase.driver, defaultPrice);
		//order.clickIconClose(TestBase.driver);
		//order.checkPopupBelowPrice(TestBase.driver, defaultPrice, ibelowPrice);
		//order.clickCancelOrderBelowPrice(TestBase.driver);
		TestBase.driver.navigate().refresh();
	}

	@Test
	public void order017_ContinueOrderBelowPrice() {
		order.clickButtonOrder(TestBase.driver); 
		if(order.countCarItems(TestBase.driver) > 0) {
			order.clickButtonOrder(TestBase.driver); 
			TestBase.driver.findElement(By.xpath("//label[@for='continute-rad']")).click();	
			TestBase.driver.findElement(By.xpath("//*[@id='alert-modal']/div[2]/a[2]")).click();
		} else AssertJUnit.assertEquals(0, 1); // miss order
	}
	
	@Test
	public void order018_OrderBelowPrice() {
		order.clickButtonOrder(TestBase.driver); 
		if(order.countCarItems(TestBase.driver) > 0) {
			order.clickButtonOrder(TestBase.driver); 
			TestBase.driver.findElement(By.xpath("//label[@for='accept-rad']")).click();	
			TestBase.driver.findElement(By.xpath("//*[@id='alert-modal']/div[2]/a[2]")).click();
		} else AssertJUnit.assertEquals(0, 1); // miss order
	}
	
	@Test
	public void order019_Confirm() {
		CommonFunctions.pause(1);
		order012_ConfirmOrder();	
	}
	
	@Test
	public void order020_BackPrevious() {
		order013_checkBackPrevious();
	}
	
	@Test
	public void order021_Success() {
		order014_OrderSuccess();
	}
	
	@Test
	public void order022_Incomming() {
		order015_CheckIncomming();
	}
	*/
}

package modules;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import configuration.ResourceHasMap;
import configuration.TestBase;
import libraries.OrderFunctions;
import libraries.SSOFunctions;
import support.CommonFunctions;

@Test
public class OrderTest {
	ResourceHasMap resource = new ResourceHasMap();
	OrderFunctions order = new OrderFunctions(TestBase.driver);		
	SSOFunctions sso = new SSOFunctions(TestBase.driver);
	int defaultPrice = 100000;

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
				sso.getUserName(TestBase.driver); 								
			} 			
			order.clickButtonOrder(TestBase.driver);	
			order.checkPopup(TestBase.driver, resource.getResource("titleMesg"), resource.getResource("contentMesg"), resource.getResource("buttonName"));
		}		
	}
	

	//@Test
	public void order005_AddMenuAfterLogin(){	
		// add food		
		order.clickNameFood(TestBase.driver);
		// check price		
		order.checkPrice(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);		
	}		

	//@Test
	public void order006_CheckNote(){
		order.checkNote(TestBase.driver, resource.getResource("orderNoted"));
	}			

	//@Test
	public void order007_AddCart(){
		order.addCart(TestBase.driver);		
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}	
	
	//@Test
	public void order008_RemoveCart(){
		order.removeCart(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}		

	//@Test
	public void order009_ResetAndCheckInfo(){
		order.resetOrder(TestBase.driver); 				
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "0";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}	
	
	//@Test 
	/*public void order010_OrderBelowPrice() {
		// click 1 food, return price of food
		int ibelowPrice = order.addOneOrderBelowPrice(TestBase.driver, defaultPrice);
		order.clickButtonOrder(TestBase.driver); 
		order.checkPopupBelowPrice(TestBase.driver, defaultPrice, ibelowPrice);	
		
		TestBase.driver.findElement(By.id("confirm-modal-cancel-btn-1")).click();
		TestBase.driver.navigate().refresh();
		CommonFunctions.pause(2);
		order.resetOrder(TestBase.driver); 		
	}*/

	//@Test 
	public void order011_AddMenuAfterReset(){		
		order.clickAddFood(TestBase.driver); 
		order.checkNote(TestBase.driver, resource.getResource("orderNoted"));
		order.checkPrice(TestBase.driver);				
	}	
	
	//@Test 
	public void order012_CheckPopupOrderPrice(){	
		String strResName = order.getRestaurantName(TestBase.driver);
		String strAddress = order.getRestaurantAddress(TestBase.driver);
		order.clickButtonOrder(TestBase.driver); 
		order.checkPopupConfirmOrder(TestBase.driver, strResName, strAddress);		
	}	
	
	public void order013_ConfirmOrder() {
		boolean bAddress = order.isHasAddressUser(TestBase.driver);
		if(bAddress == false) {
			order.insertAddress(TestBase.driver, resource.getResource("username"),  
								resource.getResource("useraddress"),  resource.getResource("userphone"),  resource.getResource("usernote"));
			order.clickContinueOrder(TestBase.driver);			
		}		
	}
	
	@AfterClass
	public void tearDown(){
		TestBase.driver.quit();
	}
}

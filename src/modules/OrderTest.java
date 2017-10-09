package modules;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.sikuli.script.Screen;

import configuration.ResourceHasMap;
import configuration.TestBase;
import libraries.OrderFunctions;
import libraries.SSOFunctions;
import libraries.SearchFunctions;
import objects.SearchPage;
import support.CommonFunctions;

public class OrderTest {
	ResourceHasMap resource = new ResourceHasMap();
	static OrderFunctions order = new OrderFunctions(TestBase.driver);		
	SSOFunctions sso = new SSOFunctions(TestBase.driver);

	@Test
	public void order001_CheckCartInfoNoLogin(){
		// Select tab browser present
		CommonFunctions.switchToTab(TestBase.driver, 1);

		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "0";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}

	@Test
	public void order002_OrderNoLogin(){
		order.clickButtonOrderFirst(TestBase.driver);		
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
				//sso.getUserName(TestBase.driver); 								
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
			order.clickButtonOrderFirst(TestBase.driver);	
			order.checkPopup(TestBase.driver, resource.getResource("titleMesg"), resource.getResource("contentMesg"), resource.getResource("buttonName"));
		}		
	}
	

	@Test
	public void order005_AddMenuAfterLogin(){	
		// add food		
		order.addMenu(TestBase.driver);
		// check price		
		order.checkPrice(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);		
	}	
	

	@Test
	public void order006_CheckFullName(){	
		order.checkUserName(TestBase.driver, "javarscript", "#login-status .name-user");
	}

	@Test
	public void order007_CheckNote(){
		order.checkNote(TestBase.driver, resource.getResource("orderNoted"));
	}		
	
	/*
	@Test
	public void order008_AddCart(){
		order.addCart(TestBase.driver);		
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}
	
	@Test
	public void order009_RemoveCart(){
		order.removeCart(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}
	
	@Test 
	public void order010_OrderAfterLogin(){
		try {
			order = new OrderFunctions(TestBase.driver);		
			//search(TestBase.driver, SearchPage.keySearch_CheckOrderPrice, ".box-photo-restaurant", 2); 
		} catch (Exception e) {
			TestBase.driver.navigate().refresh();
			CommonFunctions.pause(2);
		}
		// add food
		order = new OrderFunctions(TestBase.driver);
		order.addMenu(TestBase.driver);
		// check price
		order.checkPrice(TestBase.driver);
	}	
	
	@Test
	public void order011_ResetAndCheckInfo(){
		order = new OrderFunctions(TestBase.driver);
		order.resetOrder(TestBase.driver); 
		
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "0";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);
	}
	
	@Test 
	public void order012_CheckOrder(){	
		//search(TestBase.driver, SearchPage.keySearch_Default, ".box-photo-restaurant", 3); 
		order = new OrderFunctions(TestBase.driver);
		String countOrder = order.countNumberOrder(TestBase.driver);
		String countPerson = "1";
		order.checkCartInfo(TestBase.driver, countOrder, countPerson);	
		order.checkOrderAfterAddMenu(TestBase.driver, countOrder);
		// temp *************************
		order.resetOrder(TestBase.driver); 
	}
	
	@Test 
	public void order013_CheckPopupOrderPrice(){
		//.box-photo-restaurant
		//search(TestBase.driver, SearchPage.keySearch_CheckOrderPrice, ".box-photo-restaurant", 4); 
		// add food
		order = new OrderFunctions(TestBase.driver);
		order.checkPopupOrderPrice(TestBase.driver); 
	}	
	
	@Test 
	public void order014_ContinueAddMenu(){
		order.checkButtonPopupOrderPrice(TestBase.driver, "submit", 0); 		
		// check order info				
		AssertJUnit.assertEquals(false, order.checkPopupOrderInfo(TestBase.driver));
	}	
	
	@Test 
	public void order015_CancelPopupOrder(){		
		order.checkButtonPopupOrderPrice(TestBase.driver, "cancel", 1); 
		// check order info				
		AssertJUnit.assertEquals(false, order.checkPopupOrderInfo(TestBase.driver));
	}	
	
	@Test 
	public void order016_ContinueOrder(){
		order.checkButtonPopupOrderPrice(TestBase.driver, "submit", 1); 
		// check order info		
		AssertJUnit.assertEquals(true, order.checkPopupOrderInfo(TestBase.driver));
		// close popup
		WebElement _closePopup = (WebElement)((JavascriptExecutor)TestBase.driver)
				.executeScript("return document.querySelector('#confirminfo .btn-close-modal');");
		_closePopup.click();
		// reset order
		order.resetOrder(TestBase.driver); 
	}	
	
	@Test 
	public void order017_OrderHasFeeService(){	
		// .box-photo-restaurant
		//search(TestBase.driver, SearchPage.keySearch_Service, ".box-photo-restaurant", 5); 
		// add food
		order = new OrderFunctions(TestBase.driver);
		order.addMenu(TestBase.driver);
		
		order.checkTotalPrice(TestBase.driver, order.isService(TestBase.driver), order.isDelivery(TestBase.driver));
		order.resetOrder(TestBase.driver); 
	}	*/
	
//	@Test 
//	public void order018_OrderNoDeli(){		
//
//		//'.info-list-restaurant a');
//		search(TestBase.driver, SearchPage.keySeach_NoDeli,".info-list-restaurant a", 6); 
//		order = new OrderFunctions(TestBase.driver);
//		order.addMenu(TestBase.driver);
//		
//		order.checkTotalPrice(TestBase.driver, order.isService(TestBase.driver), order.isDelivery(TestBase.driver));
//		
//		order.checkPopupOrderNotDeli(TestBase.driver); 
//		order.resetOrder(TestBase.driver); 
//	}	
		
	/*private void search(WebDriver driver ,String keySearch,String className, int numTab){
		try {
			order.img_Logo.click();	
		} catch (Exception e) {
			driver.navigate().refresh();
		} finally {
			CommonFunctions.pause(1);
			SearchFunctions search = new SearchFunctions(driver);
			search.search(keySearch);
			CommonFunctions.pause(1);
			try {
				WebElement img_Result = (WebElement)((JavascriptExecutor)driver)
						.executeScript("return document.querySelector('"+ className +"');");
				img_Result.click();
				
				Screen g = new Screen();
				try {			
					g.click("D:\\click.png");	
				} catch (Exception e) {
					System.out.println("Khong tim thay hinh");
				}		
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			} catch (Exception e2) {
				driver.navigate().refresh();
			}			
			CommonFunctions.pause(1);
			CommonFunctions.switchToTab(driver, numTab);
		}	
	}	*/
	
	@AfterClass
	public void tearDown(){
		TestBase.driver.quit();
	}
}

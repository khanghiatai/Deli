package modules;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import libraries.OrderFunctions;
import libraries.SSOFunctions;
import libraries.SearchFunctions;
import objects.UserInfoEntity;
import support.CommonFunctions;

public class OrderTest_1 {

	public static WebDriver driver; 
	static OrderFunctions order;
	static SearchFunctions search; 
	static SSOFunctions sso; 
	
	@SuppressWarnings("static-access")
	@Test
	public void order019_OrderFood() {
		search.autoCompleteSeach(driver, search.keySearch_Service);		
		CommonFunctions.switchToTab(driver, 1);
		order.addMenu(driver);
		order.clickButtonOrderFirst(driver); 
		String name = order.getRestaurantInfo(driver);
		if (order.isInsertOrderReceive(driver) == false){
			order.checkOrderInfo(driver, name);
		}		
	}
	
	@Test
	public void order020_InsertDeliveryInfo(){
		if (order.isInsertOrderReceive(driver) == true){
			order.addOrderReceive(driver);
		} else if (order.isInsertOrderReceive(driver) == false){
			order.insertOrderReceive(driver); 			
		} else {
			Assert.assertEquals(true, false); 
		}		
	}
	
	@Test
	public void order021_EditDeliveryInfo(){
//		if(order.isInsertOrderReceive(driver) == true){
//			order.editOrderReceive(driver);
//		} else {
//			Assert.assertEquals(true, false); 
//		}		
	}
	
//	private void pub() {
//		// TODO Auto-generated method stub
//		UserInfoEntity userEnt = new UserInfoEntity();
//		userEnt.setFullName("kHA t¿I");
//		userEnt.setPhoneNumber("123465");
//		
//	}

	@SuppressWarnings("static-access")
	@Parameters({"URL", "BROWSER"})
	@BeforeClass
	public void setUp(String URL, String BROWSER ) throws MalformedURLException {
		try {
			driver = CommonFunctions.getBrowser(BROWSER);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			CommonFunctions.visit(driver, URL); 		
		} catch (Exception e) {
			driver.navigate().refresh();
		} finally {
			order = new OrderFunctions(driver);
			search = new SearchFunctions(driver);
			sso = new SSOFunctions(driver); 
			
			search.getWeb(driver); 		
			order.clickButtonLogin(driver); 
			sso.loginSSO(sso.USERNAME, sso.PASSWORD); 
		}		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}

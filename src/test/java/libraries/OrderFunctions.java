package libraries;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import configuration.ResourceHasMap;
import configuration.TestBase;
import objects.OrderPage;
import support.CommonFunctions;

public class OrderFunctions extends OrderPage {

	private ResourceHasMap resource = new ResourceHasMap();
	private String eCountCart = "cart-qty";
	private String eCountPer = ".cart-stats>span.ng-binding";
	private String ePhan_Nguoi = ".cart-stats>span";
	//String btn_Add = ".scrollspy .btn-adding";
	private String btn_order = ".btn-book-first";
	private String lbl_PopupTitle = ".//div[@id='alert-container']/div/p";
	private String lbl_PopupMessage = "alert-msg";
	private String btn_Oke = ".//div[@id='alert-modal']/div/a";
	private String box_Menu = ".scrollspy div.box-menu-detail";
	private String priceOnCart = ".add-minus-food span";

	private String btnAddCart = ".btn-add-cart";
	private String btnRemoveCart = ".btn-remove-cart";
	private static String _childNameFood = "";

	public OrderFunctions(WebDriver driver) {
		super(driver);
	}
	
	public void checkCartInfo(WebDriver driver, String numberOrder, String numberPerson) {
		CommonFunctions.pause(2);
		WebElement countCart = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('" + eCountCart + "');");

		WebElement countPer = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + eCountPer + "')[1];");

		WebElement phan = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + ePhan_Nguoi + "')[1];");

		WebElement nguoi = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + ePhan_Nguoi + "')[4];");
		
		// Check Cart Status
		if (phan.getText().equalsIgnoreCase(resource.getResource("setVi")))
			AssertJUnit.assertEquals(phan.getText(), resource.getResource("setVi"));
		else
			AssertJUnit.assertEquals(phan.getText(), resource.getResource("setVi"));

		if (nguoi.getText().equalsIgnoreCase(resource.getResource("peopleVi")))
			AssertJUnit.assertEquals(nguoi.getText(), resource.getResource("peopleVi"));
		else
			AssertJUnit.assertEquals(nguoi.getText(), resource.getResource("peopleVi"));
		
		AssertJUnit.assertEquals(countCart.getText(), numberOrder);
		AssertJUnit.assertEquals(countPer.getText(), numberPerson);
	}

	@SuppressWarnings("unchecked")
	public void checkOrderAfterAddMenu(WebDriver driver, String numberOrder) {
		CommonFunctions.pause(3);
		List<WebElement> countNumberOfFood = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('.row-bill p span.txt-red');");

		if (countNumberOfFood.size() > 0) {
			String size = Integer.toString(countNumberOfFood.size());
			AssertJUnit.assertEquals(size, numberOrder);
		} else
			AssertJUnit.assertEquals("0", numberOrder);
	}

	public void addMenuNoLogin(WebDriver driver) {
		try {
			CommonFunctions.pause(1);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.querySelector('.scrollspy .btn-adding').click();");
			
		} catch (Exception e) {
			driver.navigate().refresh();
			CommonFunctions.pause(1);
		}finally {
			CommonFunctions.pause(1);
			AssertJUnit.assertEquals(driver.getTitle(), "Login");
		}
	}

	public void clickButtonOrder(WebDriver driver) {
		CommonFunctions.pause(1);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("return document.querySelectorAll('.btn-book-first')[1].click();");
	}

	public void checkPopup(WebDriver driver, String title, String message, String nameButton) {
		CommonFunctions.pause(2);
		Assert.assertEquals(driver.findElement(By.xpath(lbl_PopupTitle)).getText(), title);
		Assert.assertEquals(driver.findElement(By.id(lbl_PopupMessage)).getText(), message);
		Assert.assertEquals(driver.findElement(By.xpath(btn_Oke)).getText(), nameButton.toUpperCase());
		driver.findElement(By.xpath(btn_Oke)).click();
	}

	public boolean isLoginPage(WebDriver driver) {
		CommonFunctions.pause(1);
		String titlePage = driver.getTitle();
		if (titlePage.equals("Login"))
			return true;
		return false;
	}
	
	/*public void clickNameFood(WebDriver driver) {		
		int i = 0;
		CommonFunctions.pause(2);
		List<WebElement> listFoods = driver.findElements(By.xpath(".//*[@class='scrollspy']//a[@class='title-name-food']"));

		for (WebElement webElement : listFoods) {
			String foodName = webElement.getText();
			List<WebElement> listMenuPrice = driver.findElements(By.xpath(".//div[@class='product-price']/a/p"));
			String foodPrice = listMenuPrice.get(i).getText().replace(" ", "");
			CommonFunctions.pause(1);
			webElement.click();	
			CommonFunctions.pause(2);	
			List<WebElement> listOrder = driver.findElements(By.xpath(".//*[@ng-show='item.group_by_CHANGED']/following-sibling::p/span[4]"));
			List<WebElement> listPrice = driver.findElements(By.xpath(".//input[@ng-model='item.Note']/following-sibling::span"));
			
			String name = listOrder.get(i).getText();
			String price = listPrice.get(i).getText();
			if(name.equalsIgnoreCase(foodName)) {
				Assert.assertEquals(name, foodName); 		
				Assert.assertEquals(price, foodPrice); 		
			}	
			i++;
		}
	}*/
	
	
	public void clickNameFood(WebDriver driver) {		
		int i = 0;
		CommonFunctions.pause(1);
		List<WebElement> listFoods = driver.findElements(By.xpath("//h2[@class='title-kind-food']/following-sibling::div/div[2]/span[2]/a/h3"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		for (int j = 0; j < listFoods.size(); j++) {
			float toppingPrice = 0; 
			String foodName = js.executeScript("return document.querySelectorAll('.name-food-detail.pull-left > span:nth-child(2) a>h3')[" + j + "].innerText").toString();
			foodName = CommonFunctions.chuanHoa(foodName);

			String foodPrice = js.executeScript("return document.querySelectorAll('.ng-hide p.current-price >span:first-child')[" + j + "].innerText").toString();


			js.executeScript("return document.querySelectorAll('.name-food-detail.pull-left > span:nth-child(2) a>h3')[" + j + "].click()");
						
			/******/
			boolean isPopup = isPopupTopping(driver);			
			if(isPopup == true) {
				CommonFunctions.pause(1);
				checkInfoPopupToping(driver, foodName, foodPrice);				
				toppingPrice = selectTopping(driver);				
				js.executeScript("document.querySelector('.topping-item-modal-footer a').click();");
			}					
			/*****/
			
			CommonFunctions.pause(1);	
			List<WebElement> listOrder = driver.findElements(By.xpath("//*[@ng-show='item.group_by_CHANGED']/following-sibling::p/span[4]"));
			List<WebElement> listPrice = driver.findElements(By.xpath("//input[@ng-model='item.Note']/following-sibling::span"));
			
			String name = listOrder.get(i).getText();
			String price = listPrice.get(i).getText();

			if(name.equalsIgnoreCase(foodName)) {				
				price = CommonFunctions.chuyenDoiKyTu(price.substring(0, price.length()-1), ",", "");
				foodPrice = CommonFunctions.chuyenDoiKyTu(foodPrice.substring(0, foodPrice.length()-1), ",", "");
				toppingPrice = toppingPrice + Float.parseFloat(foodPrice);	
				String strToppingPricr = Float.toString(toppingPrice);
				strToppingPricr = strToppingPricr.substring(0, strToppingPricr.length()-2);
				Assert.assertEquals(name, foodName); 		
				//Assert.assertEquals(strToppingPricr, price);  				
			}	
			i++;					
		}
	}
	
	public void clickAddFood(WebDriver driver) {		
		CommonFunctions.pause(2);
		List<WebElement> listFoods = driver
				.findElements(By.xpath("//div[@class='extra']/div[@class='adding-food-cart']/span[@class='btn-adding']"));		
		for (int i = 0; i < listFoods.size() ; i++) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("return document.querySelectorAll('.more-info .btn-adding')[" + i + "].click();");
			CommonFunctions.pause(1);
			String foodName = js.executeScript("return document.querySelectorAll('.title-name-food>h3')[" + i + "].innerText;").toString();

			List<WebElement> listMenuPrice = driver.findElements(By.xpath(".//div[@class='product-price']/a/p"));
			String foodPrice = listMenuPrice.get(i).getText().replace(" ", "");
			
			CommonFunctions.pause(2);	
			List<WebElement> listOrder = driver.findElements(By.xpath(".//*[@ng-show='item.group_by_CHANGED']/following-sibling::p/span[4]"));
			List<WebElement> listPrice = driver.findElements(By.xpath(".//input[@ng-model='item.Note']/following-sibling::span"));
			
			String name = listOrder.get(i).getText();
			String price = listPrice.get(i).getText();
			if(name.equalsIgnoreCase(foodName)) {
				Assert.assertEquals(name, foodName); 		
				Assert.assertEquals(price, foodPrice); 		
			}	
		} 
	}
	
	public void checkPrice(WebDriver driver) {
		List<WebElement> listPrice = driver.findElements(By.xpath(".//input[@ng-model='item.Note']/following-sibling::span"));
		float totalPrice = 0;
		float tempPrice = 0;
		for (WebElement webElement : listPrice) {
			String strPrice = webElement.getText();
			strPrice = strPrice.substring(0, strPrice.length()-1).replace(",", ""); 
			totalPrice += Float.parseFloat(strPrice);			
		}
		String strOrderPrice = driver.findElement(By.xpath("//div[@class='container-bill']/div[4]/span[2]")).getText().replace(",", "");//1
		strOrderPrice = strOrderPrice.substring(0, strOrderPrice.length() -1);
		
		//String strFeeShip = driver.findElement(By.xpath("//span[@ng-show='!detailCtrl.hasMilestoneFee']")).getText().replace(",", "");//4
		//strFeeShip = strFeeShip.substring(0, strFeeShip.length() -4);
		
		//String strTempPrice = driver.findElement(By.xpath("//div[@class='container-bill']/div[10]/span[2]")).getText().replace(",", "");//1
		//strTempPrice = strTempPrice.substring(0, strTempPrice.length() -1);
		
		Assert.assertEquals(totalPrice, Float.parseFloat(strOrderPrice));
		
		// add fee ship *******************
		//tempPrice = totalPrice + Float.parseFloat(strFeeShip);
		
		//Assert.assertEquals(tempPrice, Float.parseFloat(strTempPrice));
	}
	
	public int addOneOrderBelowPrice(WebDriver driver, int price) {
		List<WebElement> listPrice = driver.findElements(By.xpath(".//div[@class='product-price']/p[@class='current-price']/span[1]"));

		JavascriptExecutor js = (JavascriptExecutor)TestBase.driver;
		for (int i = 0; i < listPrice.size(); i++) {
			String strPrice = js.executeScript("return document.querySelectorAll('.product-price a .current-price')[" + i +  "].innerText;").toString();
			strPrice = CommonFunctions.chuanHoa(strPrice);
			strPrice = strPrice.replace(",", "").substring(0, strPrice.length()-3).toString();
			int iPrice = Integer.parseInt(strPrice);
			if(iPrice < price) {
				CommonFunctions.pause(1);
				js.executeScript("document.querySelectorAll('.detail-menu-kind .scrollspy .btn-adding')[" + i +  "].click();");
				//
				boolean isPopup = isPopupTopping(driver);			
				if(isPopup == true) {
					CommonFunctions.pause(1);			
					selectTopping(driver);				
					js.executeScript("document.querySelector('.topping-item-modal-footer a').click();");
				}	
				//
				return iPrice; 
			} else continue;
		}
		return 0;
	}
	

	public void checkPopupBelowPrice(WebDriver driver, int defaultPrice, int belowPrice) {
		CommonFunctions.pause(1);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String titlePopup = js.executeScript("return document.querySelector('#alert-modal >p').innerText").toString();
		Assert.assertEquals(titlePopup, resource.getResource("popupTitle"));
		List<WebElement> tempPrice = driver.findElements(By.xpath(".//p[@id='alert-msg']/i"));
		for (int i = 0; i < tempPrice.size(); i++) {
			if(i == 0) {
				String strBelowPrice = js.executeScript("return document.querySelectorAll('#alert-msg > i')[" + i +"].innerText").toString();
				strBelowPrice = CommonFunctions.chuanHoa(strBelowPrice);
				strBelowPrice = strBelowPrice.substring(0, strBelowPrice.length() -2).replace(",", "");
				int iBelowPrice = Integer.parseInt(strBelowPrice);
				Assert.assertEquals(iBelowPrice, belowPrice);
			} else if (i == 1) {
				String strDefaulPrice = js.executeScript("return document.querySelectorAll('#alert-msg > i')[" + i +"].innerText").toString();
				strDefaulPrice = CommonFunctions.chuanHoa(strDefaulPrice);
				strDefaulPrice = strDefaulPrice.substring(0, strDefaulPrice.length() -2).replace(",", "");
				int iDefaulPrice = Integer.parseInt(strDefaulPrice);
				Assert.assertEquals(iDefaulPrice, defaultPrice);
			}
		}
		String strContinueOrder = driver.findElement(By.xpath(".//label[@for='continute-rad']")).getText();
		strContinueOrder = CommonFunctions.chuanHoa(strContinueOrder);
		String strAceptFee = driver.findElement(By.xpath(".//label[@for='accept-rad']")).getText();
		strAceptFee = CommonFunctions.chuanHoa(strAceptFee);
		Assert.assertEquals(strContinueOrder, resource.getResource("continueorder"));
		Assert.assertEquals(strAceptFee, resource.getResource("aceptfee")); 
	}
	
	public int countCarItems(WebDriver driver) {
		return Integer.parseInt(driver.findElement(By.id("cart-qty")).getText());
	}
	
	public void clickCancelOrderBelowPrice(WebDriver driver) {
		CommonFunctions.pause(1);
		driver.findElement(By.xpath("//*[@id='alert-modal']/div[2]/a[1]")).click();
	}
	
	/*public void clickOKContinueOrder(WebDriver driver) {
		CommonFunctions.pause(3);
		driver.findElement(By.xpath("//*[@id='alert-modal']/div[2]/a[2]")).click();
	}*/
	
	public void checkPopupConfirmOrder(WebDriver driver, String resName, String address) {
		// check title popup
		List<WebElement> listTitleName = driver.findElements(By.xpath(".//*[@class='checkout-steps']/div"));
		for (int i = 1; i < listTitleName.size() + 1; i++) {	
			CommonFunctions.pause(2); 
			switch(i) {
			case 1: 				
				String strAddress = driver.findElement(By.xpath(".//*[@class='checkout-steps']/div/div["+ i +"]")).getText();
				strAddress = strAddress.substring(1, strAddress.length());
				strAddress = CommonFunctions.chuanHoa(strAddress);
				Assert.assertEquals(strAddress, resource.getResource("checkout_address"));
				break;
			case 2: 
				String strInfoOrder = driver.findElement(By.xpath(".//*[@class='checkout-steps']/div/div["+ i +"]")).getText();
				strInfoOrder = strInfoOrder.substring(2, strInfoOrder.length());
				Assert.assertEquals(strInfoOrder, resource.getResource("checkout_infoorder"));
				break;
			case 3: 
				String strFinish = driver.findElement(By.xpath(".//*[@class='checkout-steps']/div/div["+ i +"]")).getText();
				strFinish = strFinish.substring(2, strFinish.length());
				Assert.assertEquals(strFinish, resource.getResource("checkout_finish"));
				break;
			}			
		}
		//check res name
		String strResName = driver.findElement(By.xpath("//*[@class='delivery-pick-adress']/div[1]/span")).getText();
		Assert.assertEquals(strResName.toLowerCase(), resName.toLowerCase());
		// check address of res
		address = address + resource.getResource("checkout_city");
		String strAddress = driver.findElement(By.xpath("//*[@class='delivery-pick-adress']/div[2]/span")).getText();
		Assert.assertEquals(address.toLowerCase(), strAddress.toLowerCase()); 
		// check radio confirm
		String option = driver.findElement(By.xpath(".//*[@ng-switch-when='1']/div[2]/div[2]/div[1]")).getText();
		Assert.assertEquals(option.toLowerCase(), resource.getResource("checkout_optionconfirm").toLowerCase());
		// check option default
		String optionDefault = driver.findElement(By.xpath(".//label[@for='order-standard-1']")).getText();
		Assert.assertEquals(optionDefault.toLowerCase(), resource.getResource("checkout_optiondefault").toLowerCase());
		// check box time receive
		String timeReceive = driver.findElement(By.xpath(".//div[@ng-switch-when='1']/div[2]/div[2]/div[3]")).getText();
		Assert.assertEquals(timeReceive.toLowerCase(), resource.getResource("checkout_timereceive").toLowerCase());
		// check note order
		String noted = driver.findElement(By.xpath(".//label[@for='note1']")).getText();
		Assert.assertEquals(noted.toLowerCase(), resource.getResource("checkout_noted").toLowerCase());		
	}
	
	public boolean isHasAddressUser(WebDriver driver) {		
		String strText =  driver.findElement(By.xpath(".//div[@class='delivery-drop']/a")).getText();
		if(strText.equalsIgnoreCase(resource.getResource("checkout_addlabel"))) return true; 
		else return false;
	}
	
	public void insertAddress(WebDriver driver, String name, String address, String phone, String note) {
		CommonFunctions.pause(2);
		WebElement wName = driver.findElement(By.id("fullname"));
		wName.clear();
		wName.sendKeys(name);
		WebElement wPhone = driver.findElement(By.id("phone"));
		wPhone.clear();
		wPhone.sendKeys(phone);
		WebElement wAdress = driver.findElement(By.id("address"));
		wAdress.clear();
		wAdress.sendKeys(address);
		WebElement wNote = driver.findElement(By.xpath(".//textarea[@ng-model='detailCtrl.orderNote']"));
		wNote.clear();
		wNote.sendKeys(note);
		//
	}

	@SuppressWarnings("unchecked")
	public void checkPrice1(WebDriver driver) {
		CommonFunctions.pause(1);
		// .add-minus-food span
		ArrayList<WebElement> priceCart = (ArrayList<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + priceOnCart + "');");
		float totalPrice = 0;
		for (WebElement webElement : priceCart) {
			if (webElement.getText().equals(null) || webElement.getText().equals("")
					|| webElement.getText().equals(" ")) {
				continue;
			}
			String str1 = webElement.getText();
			// xoa ky tu cuoi ra khoi chuoi

			str1 = CommonFunctions.chuyenDoiKyTu(CommonFunctions.chuanHoa(str1.substring(0, str1.length() - 1)), ",",
					"");
			// total price
			totalPrice += Integer.parseInt(str1);
		}
		CommonFunctions.pause(2);
		// .container-bill div span.bold.pull-right
		WebElement price = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.container-bill div span.bold.pull-right');");

		// .row-bill-grey span.pull-right.txt-blue
		WebElement temp = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.container-bill div span.bold.pull-right');");

		AssertJUnit.assertEquals(price.getText(), temp.getText());
		String str = CommonFunctions.chuyenDoiKyTu(
				CommonFunctions.chuanHoa(price.getText().substring(0, price.getText().length() - 1)), ",", "");
		AssertJUnit.assertEquals(Integer.toString((int) totalPrice), str);
	}

	@SuppressWarnings("unchecked")
	public boolean isService(WebDriver driver) {
		try {
			List<WebElement> element = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>p>span');");

			for (int i = 0; i < element.size(); i++) {
				String sService = (String) ((JavascriptExecutor) driver).executeScript(
						"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>p>span')[" + i
								+ "].innerText;");
				if (sService.equals("PHÃ� Dá»ŠCH Vá»¤"))
					return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isDelivery(WebDriver driver) {
		try {
			List<WebElement> element = (List<WebElement>) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('.column-info-deli>span')");

			for (int i = 0; i < element.size(); i++) {
				String sService = (String) ((JavascriptExecutor) driver).executeScript(
						"return document.querySelectorAll('.column-info-deli>span')[" + i + "].innerText");
				if (sService.equals("deliveryNow"))
					return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}	

	@SuppressWarnings("unchecked")
	public String countNumberOrder(WebDriver driver) {
		CommonFunctions.pause(3);
		int _count = 0;
		// .row-bill p span.txt-red // number of food
		List<WebElement> countNumberOfFood = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('.row-bill p span.txt-red');");

		if (countNumberOfFood.size() > 0) {
			// _count = countNumberOfFood.size();
			for (WebElement webElement : countNumberOfFood) {
				_count += Integer.parseInt(webElement.getText());
			}
			return Integer.toString(_count);
		} else
			return "0";
	}

	public void resetOrder(WebDriver driver) {
		driver.findElement(By.xpath(".//a[@ng-click='detailCtrl.clearCart();']")).click();
		CommonFunctions.pause(1);
	}

	public void addCart(WebDriver driver) {
		CommonFunctions.pause(2);
		// .btn-add-cart
		List<WebElement> _add = driver.findElements(By.xpath(".//span[@ng-click='detailCtrl.updateQtyExists(item, 1)']"));
		for (int i = 0; i < _add.size(); i++) {
			CommonFunctions.pause(1);
			WebElement add = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('" + btnAddCart + "')[" + i + "];");
			add.click();
		}
	}

	public void removeCart(WebDriver driver) {
		CommonFunctions.pause(2);
		// .btn-remove-cart
		List<WebElement> _remove = driver.findElements(By.xpath(".//span[@ng-click='detailCtrl.updateQtyExists(item ,-1)']"));
		for (int i = 0; i < _remove.size(); i++) {
			CommonFunctions.pause(1);
			WebElement remove = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('" + btnRemoveCart + "')[" + i + "];");
			remove.click();
		}
		CommonFunctions.pause(2);
	}

	public void checkNote(WebDriver driver, String str) {
		WebElement element;
		WebElement eChildPrice;
		String str1;
		CommonFunctions.pause(2);
		for (int i = 0; i < 3; i++) {
			element = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('.add-minus-food input')[0];");
			eChildPrice = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('.row-bill span.bold.font13.ng-binding')[0];");

			if (i > 0) {
				element.clear();
				element.sendKeys(str, "\b");
				str = str.substring(0, str.length() - 1);
			} else if (i == 0) {
				element.clear();
				element.sendKeys(str);
			}
			CommonFunctions.pause(1);
			str1 = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementsByTagName('input')[2].value;");
			eChildPrice.click();
			try {
				driver.navigate().refresh();
			} catch (Exception e) {
				driver.navigate().refresh();
			}
			CommonFunctions.pause(2);
			AssertJUnit.assertEquals(str, str1);
		}
	}

	public void clickCancelOrder(WebDriver driver){
		CommonFunctions.pause(1);
		String _titlePopup = driver.findElement(By.cssSelector(".right-popup-order p")).getText();
		Assert.assertEquals(_titlePopup, resource.getResource("titlePopupConfirm"));
		//System.out.print(_titlePopup);
		driver.findElement(By.xpath("//div[@id='confirminfo']/div[1]/div[1]/a")).click();
	}
	
	public void clickFinishOrder(WebDriver driver) {
		driver.findElement(By.xpath("//*[@class='modal-footer']/a[2][@ng-click='detailCtrl.incrementStep()']")).click();		
	}
	
	public void checkPopupFinishOrder(WebDriver driver) {
		// title 
		String strTitle = driver.findElement(By.xpath("//*[@class='header-vat']")).getText();
		Assert.assertEquals(strTitle, resource.getResource("titlesuccess").toUpperCase());
	
		// content
		String strContent = driver.findElement(By.xpath("//*[@class='msg-order-success']")).getText();
		Assert.assertEquals(strContent, resource.getResource("contentsuccess"));

		// link text
		String strHistory = driver.findElement(By.xpath("//*[@class='ng-scope']/div[4]/a")).getText();
		Assert.assertEquals(strHistory, resource.getResource("historyorder").toUpperCase());
		
		// link text wait confirm
		String strWait = driver.findElement(By.xpath("//*[@ng-show='!detailCtrl.hasNextStep()' or @ng-click='detailCtrl.reload()']")).getText();
		Assert.assertEquals(strWait, resource.getResource("waitconfirm").toUpperCase());
	}
	
	public void checkInfoAfterOrder(WebDriver driver, String resName, String address) {
		// get name
		String strResName = driver.findElement(By.xpath(".//*[@class='name-hot-restaurant']")).getText();
		// get Address
		String strAddress = driver.findElement(By.xpath(".//*[@itemprop='description']")).getText();
		Assert.assertEquals(strResName, resName);
		Assert.assertEquals(strAddress, address);
	}
	
	public void checkIncomming(WebDriver driver, String resName, String address) {
		String strName = driver.findElement(By.xpath(".//*[@class='order-summary']/a/div[1]")).getText();
		Assert.assertEquals(strName, resName);
		// code here
	}
	
	public void clickWaitConfirm(WebDriver driver) {
		driver.findElement(By.xpath("//*[@ng-show='!detailCtrl.hasNextStep()' or @ng-click='detailCtrl.reload()']")).click();
	}
	
	public void clickBackPreviosPopup(WebDriver driver) {
		driver.findElement(By.id("backPreviousStep")).click();
	}
	
	public void checkUserName(WebDriver driver, String how, String locator) {
		SSOFunctions sso = new SSOFunctions(driver);
		sso.checkUserProfile(driver, how, locator);
	}
	
	public void clickButtonLogin(WebDriver driver){
		CommonFunctions.pause(1);
		WebElement hpl_Login = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('#login-status a');");
		hpl_Login.click();
	}
	

	public String getRestaurantName(WebDriver driver){
		String _name = ((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('.name-hot-restaurant').innerText;").toString();
		_name = CommonFunctions.chuanHoa(_name);
		return _name;
	}	

	public String getRestaurantAddress(WebDriver driver){			
		String _addRess = ((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('.info-basic-hot-restaurant > p').innerText;").toString();		
		return _addRess;
	}
	
	public boolean isInsertOrderReceive(WebDriver driver){		
		try {
			String hplAddJE = "return document.querySelector('.delivery-drop a.add-new-addres');";
			String hplEditJE = "return document.querySelector('#editAddress');";
			boolean isHplAdd = checkControl(driver, hplAddJE);
			boolean isHplEdit = checkControl(driver, hplEditJE);
			if(isHplAdd == true && isHplEdit == true){	
				return true;
			} else return false;
		} catch (Exception e) {
			return false;
		}
	}
	
//	public void clickContinueOrder(WebDriver driver) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("document.querySelectorAll('#confirminfo .modal-footer > a')[0].click();");
//	}
	
	private void checkInfoPopupToping(WebDriver driver, String foodName, String foodPrice) {		
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String strName = js.executeScript("return document.querySelector('.topping-item-modal-summary-right h3').innerText;").toString();
			Assert.assertEquals(strName, foodName);
			String strPrice = js.executeScript("return document.querySelector('.topping-item-modal-summary-price span').innerText;").toString();
			strPrice = CommonFunctions.chuyenDoiKyTu(strPrice, " ", "").toLowerCase(); 
			Assert.assertEquals(strPrice, foodPrice);
		} catch (Exception e) {

		}		
	}
	
	private boolean isPopupTopping(WebDriver driver) {
		try {
			List<WebElement> listTopping = driver.findElements(By.xpath("//*[@class='topping-item-modal-list-item-container-name']"));
			if(listTopping.size() > 0) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				String strString = js.executeScript("return document.querySelectorAll('.topping-item-modal-list-item-container-name')[0].innerText").toString();
				int num = CommonFunctions.getNumberOfString(strString);				
				if (num == 0) {
					// here
					// here
					js.executeScript("document.querySelector('.topping-item-modal-footer a').click();");
					return false;
				} else return true;
			} else return false;			
		} catch (Exception e) {
			return false;
		}
	}
	
	private float selectTopping(WebDriver driver) {
		List<WebElement> listTopping = driver.findElements(By.xpath("//*[@class='topping-item-modal-list-item-container-name']"));
		float fPrice = 0; 
		if(listTopping.size() > 0) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			for (int i = 1; i <= listTopping.size(); i++) {
				String strString = js.executeScript("return document.querySelectorAll('.topping-item-modal-list-item-container-name')[0].innerText").toString();
				int num = CommonFunctions.getNumberOfString(strString);
				List<WebElement> listItem = driver.findElements(By.xpath("//*[@class='topping-item-modal-list']/div[1]/div[2]/div/div"));
				String sPrice; 
				if(num > 0 && listItem.size() < num) {	
					
					for (int j = 1; j <= listItem.size(); j++) {
						driver.findElement(By.xpath("//*[@class='topping-item-modal-list']/div[" + i +"]/div[2]/div/div["+ j +"]/div[1]/div/label")).click();					
						
						sPrice = driver.findElement(By.xpath("//*[@class='topping-item-modal-list']/div[" + i +"]/div[2]/div/div["+ j +"]/div[1]/div[2]")).getText();
						if(sPrice != null || sPrice != " " || sPrice != "") {
							sPrice = CommonFunctions.chuyenDoiKyTu(sPrice.substring(1, sPrice.length()-1), ",", "");  //;
							fPrice += Float.parseFloat(sPrice);
						}
					}				
				} else if(num > 0 && listItem.size() >= num) {				
					for (int j = 1; j <= num; j++) {
						driver.findElement(By.xpath("//*[@class='topping-item-modal-list']/div[" + i +"]/div[2]/div/div["+ j +"]/div[1]/div/label")).click();	
						
						sPrice = driver.findElement(By.xpath("//*[@class='topping-item-modal-list']/div[" + i +"]/div[2]/div/div["+ j +"]/div[1]/div[2]")).getText();
						if(!sPrice.isEmpty()) {
							sPrice = CommonFunctions.chuyenDoiKyTu(sPrice.substring(1, sPrice.length()-1), ",", "");  //;
							fPrice += Float.parseFloat(sPrice);
						}
					}				
				}
			}
		} 	
		return fPrice;
	}


		
	/**************** Private ****************/
	private void checkOrderDeatail(WebDriver driver) {
		List<WebElement> countFoodOnBox = driver.findElements(By.xpath(".//*[@ng-show='item.group_by_CHANGED']/following-sibling::p"));
		List<WebElement> countFoodOnPopup = driver.findElements(By.xpath(".//div[@class='pull-left']"));
		// get length & check length
		Assert.assertEquals(countFoodOnBox.size(), countFoodOnPopup.size());
		
		List<WebElement> listOrder = driver.findElements(By.xpath(".//*[@ng-show='item.group_by_CHANGED']/following-sibling::p/span[4]"));
		List<WebElement> listNumFood = driver.findElements(By.xpath(".//*[@ng-show='item.group_by_CHANGED']/following-sibling::p/span[2]"));
		List<WebElement> listPrice = driver.findElements(By.xpath(".//input[@ng-model='item.Note']/following-sibling::span"));	
		List<WebElement> strNumFood = driver.findElements(By.xpath(".//div[@class='pull-left']/p/span[1]"));
		List<WebElement> strOrder = driver.findElements(By.xpath(".//div[@class='pull-left']/p/span[2]"));
		List<WebElement> strPrice = driver.findElements(By.xpath(".//div[@class='pull-left']/following-sibling::div"));
		// check order
		for (int index = 0; index < countFoodOnBox.size(); index++) {
			Assert.assertEquals(listOrder.get(index).getText(), strOrder.get(index).getText());
			Assert.assertEquals(listNumFood.get(index).getText(), strNumFood.get(index).getText());
			
			String _listPrice = CommonFunctions.chuanHoa(listPrice.get(index).getText());
			_listPrice = _listPrice.substring(0, _listPrice.length()-1);
			
			String _strPrice = CommonFunctions.chuanHoa(strPrice.get(index).getText());
			_strPrice = _strPrice.substring(0, _strPrice.length()-1);
					
			Assert.assertEquals(_listPrice, _strPrice);						
		}
	}

	private void checkOrderFee(WebDriver driver) {
		//Sub Total 
		String totalPrice = driver.findElement(By.xpath(".//div[@class='container-bill']/div[4]/span[2]")).getText();
		totalPrice = CommonFunctions.chuanHoa(totalPrice);
		totalPrice = totalPrice.substring(0, totalPrice.length()-1);

		String subTotal = driver.findElement(By.xpath(".//*[@ng-switch-when='2' and @class='ng-scope']/div/div[1]/div[2]/p[1]/span")).getText();
		subTotal = CommonFunctions.chuanHoa(subTotal);
		subTotal = subTotal.substring(0, subTotal.length()-1);
		//Delivery fee
		String c = driver.findElement(By.xpath(".//*[@ng-switch-when='2' and @class='ng-scope']/div/div[1]/div[2]/p[3]/span[2]")).getText();
		//Shipping fee discount
		String d = driver.findElement(By.xpath(".//*[@ng-switch-when='2' and @class='ng-scope']/div/div[1]/div[2]/p[4]/span")).getText();
		//Total
		String e = driver.findElement(By.xpath(".//*[@ng-switch-when='2' and @class='ng-scope']/div/div[1]/div[2]/p[7]/span")).getText();
		// Confirm Fee:
		//String f = driver.findElement(By.xpath(".//*[@ng-switch-when='2' and @class='ng-scope']/div/div[1]/div[2]/p[8]/span")).getText();

		System.out.println("C:" + c);
		System.out.println("D:" + d);
		System.out.println("E:" + e);
		

		// fee ship
		String g = driver.findElement(By.xpath("//span[@ng-show='!detailCtrl.hasMilestoneFee']")).getText();
		System.out.println("G:" + g);
	}
	
	/*private boolean checkCombobox(WebDriver driver, String locatorDOM){
		try {
			WebElement combobox = (WebElement)((JavascriptExecutor)driver)
					.executeScript(locatorDOM);
			
			Select oSelect = new Select(combobox);
			List<WebElement> elementSelect = oSelect.getOptions();
			if(elementSelect.size() > 1){
				return true;
			}
		} catch (Exception e) { 
			return false;
		}
		return false;
	}*/
	
	private boolean checkControl(WebDriver driver, String locatorDOM){
		try {
			WebElement element = (WebElement)((JavascriptExecutor)driver)
					.executeScript(locatorDOM);
			if(element.isEnabled()== true) return true;
			else return false;
		} catch (Exception e) {
			return false;
		}		
	}
}

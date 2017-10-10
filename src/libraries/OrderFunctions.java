package libraries;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import configuration.ResourceHasMap;
import objects.OrderPage;
import objects.SearchPage;
import support.CommonFunctions;

public class OrderFunctions extends OrderPage {

	ResourceHasMap resource = new ResourceHasMap();
	String eCountCart = "cart-qty";
	String eCountPer = ".cart-stats>span.ng-binding";
	String ePhan_Nguoi = ".cart-stats>span";
	String btn_Add = ".scrollspy .btn-adding";
	String btn_order = ".btn-book-first";
	String lbl_PopupTitle = ".//div[@id='alert-container']/div/p";
	String lbl_PopupMessage = "alert-msg";
	String btn_Oke = ".//div[@id='alert-modal']/div/a";
	String box_Menu = ".scrollspy div.box-menu-detail";
	String priceOnCart = ".add-minus-food span";
	String eReset = ".btn-reset";
	String btnAddCart = ".btn-add-cart";
	String btnRemoveCart = ".btn-remove-cart";
	static String _childNameFood = "";

	public OrderFunctions(WebDriver driver) {
		super(driver);
		//InitHasMapResource();
	}

	
	public void InitHasMapResource(){
		// text
		//mMap.put("TimeAddressReciver", "Thá»�i gian & Ä�á»‹a chá»‰ nháº­n hÃ ng");
	}
	
	public void checkCartInfo(WebDriver driver, String numberOrder, String numberPerson) {
		CommonFunctions.pause(3);
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
			WebElement add = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelector('" + btn_Add + "');");
			add.click();
		} catch (Exception e) {
			driver.navigate().refresh();
			CommonFunctions.pause(1);
		}finally {
			CommonFunctions.pause(1);
			AssertJUnit.assertEquals(driver.getTitle(), "Login");
		}
	}

	public void clickButtonOrderFirst(WebDriver driver) {
		CommonFunctions.pause(2);
		WebElement btn_DatTruoc = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + btn_order + "')[1];");

		btn_DatTruoc.click();
	}

	public void checkPopup(WebDriver driver, String title, String message, String nameButton) {
		CommonFunctions.pause(1);
		AssertJUnit.assertEquals(driver.findElement(By.xpath(lbl_PopupTitle)).getText(), title);
		AssertJUnit.assertEquals(driver.findElement(By.id(lbl_PopupMessage)).getText(), message);
		AssertJUnit.assertEquals(driver.findElement(By.xpath(btn_Oke)).getText(), nameButton.toUpperCase());
		driver.findElement(By.xpath(btn_Oke)).click();
	}

	public boolean isLoginPage(WebDriver driver) {
		CommonFunctions.pause(1);
		String titlePage = driver.getTitle();
		if (titlePage.equals("Login"))
			return true;
		return false;
	}
	
	public void addMenu(WebDriver driver) {		
		int i = 0;
		CommonFunctions.pause(2);
		List<WebElement> listFoods = driver.findElements(By.xpath(".//*[@class='scrollspy']//a[@class='title-name-food']"));

		for (WebElement webElement : listFoods) {
			String foodName = webElement.getText();
			List<WebElement> listMenuPrice = driver.findElements(By.xpath(".//div[@class='product-price']/a/p"));
			String foodPrice = listMenuPrice.get(i).getText().replace(" ", "");
			
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
	}

	@SuppressWarnings("unchecked")
	public void checkPrice(WebDriver driver) {
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
	public void checkTotalPrice(WebDriver driver, Boolean eSevice, Boolean eDelivery) {
		CommonFunctions.pause(1);
		// has fee service and delivery service
		if (eSevice == true && eDelivery == true) {
			List<WebElement> element = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>p>span');");
			int i;
			for (i = 0; i < element.size(); i++) {
				String sService = (String) ((JavascriptExecutor) driver).executeScript(
						"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>p>span')[" + i
								+ "].innerText;");
				if (sService.equals("PHÃ� Dá»ŠCH Vá»¤")) {
					i--;
					break;
				}
			}
			CommonFunctions.pause(2);
			String feeService = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>span')[" + i
							+ "].innerText;");
			feeService = CommonFunctions.chuyenDoiKyTu(feeService, "% phá»¥c vá»¥", "");
			String boxFeeService = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.container-bill div span span.bold')[0].innerText;");
			boxFeeService = CommonFunctions.chuyenDoiKyTu(boxFeeService, "%", "");
			String totalPrice = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.container-bill div span.bold.pull-right')[0].innerText;");
			totalPrice = CommonFunctions.chuyenDoiKyTu(totalPrice, ",", "");
			totalPrice = CommonFunctions.chuyenDoiKyTu(totalPrice, "Ä‘", "");
			String priceService = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('.container-bill span.pull-right')[2].innerText;");
			priceService = CommonFunctions.chuyenDoiKyTu(priceService, ",", "");
			priceService = CommonFunctions.chuyenDoiKyTu(priceService, "Ä‘", "");
			String tempTotalPrice = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.container-bill div span.pull-right.txt-blue')[0].innerText;");
			tempTotalPrice = CommonFunctions.chuyenDoiKyTu(tempTotalPrice, ",", "");
			tempTotalPrice = CommonFunctions.chuyenDoiKyTu(tempTotalPrice, "Ä‘", "");
			int _priceService = Integer.parseInt(totalPrice) * Integer.parseInt(feeService) / 100;
			int _tempTotalPrice = _priceService + Integer.parseInt(totalPrice);
			AssertJUnit.assertEquals(feeService, boxFeeService);
			AssertJUnit.assertEquals(Integer.toString(_priceService), priceService);
			AssertJUnit.assertEquals(Integer.toString(_tempTotalPrice), tempTotalPrice);
		} // has fee service and not delivery service
		else if (eSevice == true && eDelivery == false) {
			List<WebElement> element = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>p>span');");
			int i;
			for (i = 0; i < element.size(); i++) {
				String sService = (String) ((JavascriptExecutor) driver).executeScript(
						"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>p>span')[" + i
								+ "].innerText;");
				if (sService.equals("PHÃ� Dá»ŠCH Vá»¤")) {
					i--;
					break;
				}
			}
			CommonFunctions.pause(2);
			String feeService = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.info-delivery-restaurant .column-info-deli>span')[" + i
							+ "].innerText;");
			feeService = CommonFunctions.chuyenDoiKyTu(feeService, "% phá»¥c vá»¥", "");
			String boxFeeService = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.container-bill div span span.bold')[0].innerText;");
			boxFeeService = CommonFunctions.chuyenDoiKyTu(boxFeeService, "%", "");
			String totalPrice = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.container-bill div span.bold.pull-right')[0].innerText;");
			totalPrice = CommonFunctions.chuyenDoiKyTu(totalPrice, ",", "");
			totalPrice = CommonFunctions.chuyenDoiKyTu(totalPrice, "Ä‘", "");
			String priceService = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('.container-bill span.pull-right')[2].innerText;");
			priceService = CommonFunctions.chuyenDoiKyTu(priceService, ",", "");
			priceService = CommonFunctions.chuyenDoiKyTu(priceService, "Ä‘", "");
			String tempTotalPrice = (String) ((JavascriptExecutor) driver).executeScript(
					"return document.querySelectorAll('.container-bill div span.pull-right.txt-blue')[0].innerText;");
			tempTotalPrice = CommonFunctions.chuyenDoiKyTu(tempTotalPrice, ",", "");
			tempTotalPrice = CommonFunctions.chuyenDoiKyTu(tempTotalPrice, "Ä‘", "");
			int _priceService = Integer.parseInt(totalPrice) * Integer.parseInt(feeService) / 100;
			int _tempTotalPrice = _priceService + Integer.parseInt(totalPrice);
			AssertJUnit.assertEquals(feeService, boxFeeService);
			AssertJUnit.assertEquals(Integer.toString(_priceService), priceService);
			AssertJUnit.assertEquals(Integer.toString(_tempTotalPrice), tempTotalPrice);
		} else {
			System.out.println("No service");
		}
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
		CommonFunctions.pause(2);
		WebElement reset = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('" + eReset + "');");
		reset.click();
		CommonFunctions.pause(1);
	}

	@SuppressWarnings("unchecked")
	public void addCart(WebDriver driver) {
		CommonFunctions.pause(2);
		// .btn-add-cart
		List<WebElement> _add = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + btnAddCart + "');");
		for (int i = 0; i < _add.size(); i++) {
			CommonFunctions.pause(1);
			WebElement add = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('" + btnAddCart + "')[" + i + "];");
			add.click();
		}
	}

	@SuppressWarnings("unchecked")
	public void removeCart(WebDriver driver) {
		CommonFunctions.pause(2);
		// .btn-remove-cart
		List<WebElement> _remove = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + btnRemoveCart + "');");
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

	@SuppressWarnings("unchecked")
	public void checkPopupOrderPrice(WebDriver driver) {
		CommonFunctions.pause(1);
		WebElement add = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('" + btn_Add + "');");
		add.click();

		CommonFunctions.pause(1);
		WebElement btn_DatTruoc = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + btn_order + "')[1];");
		WebElement getPrice = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.add-minus-food span');");

		String _getPrice = getPrice.getText();
		btn_DatTruoc.click();

		CommonFunctions.pause(2);
		List<WebElement> message = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-modal p span');");

		String titlePopup = "";
		int i = 0;
		for (WebElement element : message) {
			if (i != 0 && i % 2 == 0)
				titlePopup += " " + element.getText();
			else
				titlePopup += element.getText();
			i++;
		}
		CommonFunctions.pause(1);
		WebElement getPricePopup = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-msg i');");
		WebElement defaultPrice = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg i')[1];");

		String _getPricePopup = CommonFunctions.chuyenDoiKyTu(getPricePopup.getText(), " ", "");
		_getPrice = CommonFunctions.chuyenDoiKyTu(_getPrice, "", "");

		String _option1 = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg label')[0].innerText;");
		String _option2 = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg label')[1].innerText;");
		// check button name
		WebElement _cancel = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-modal a.btn-cancel');");
		String _submit = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-modal a.btn-ok').innerText;");


		AssertJUnit.assertEquals("DeliveryNow thÃ´ng bÃ¡o", titlePopup);
		AssertJUnit.assertEquals(_getPrice, _getPricePopup);
		AssertJUnit.assertEquals("100,000 Ä‘", defaultPrice.getText()); // default
																		// price
		AssertJUnit.assertEquals("Tiáº¿p tá»¥c chá»�n thÃªm mÃ³n", _option1);
		AssertJUnit.assertEquals("Cháº¥p nháº­n phÃ­ dá»‹ch vá»¥ lÃ  " + SearchPage.priceRequire + " Ä‘ Â vÃ  tiáº¿n hÃ nh thanh toÃ¡n",
				_option2); // input
		AssertJUnit.assertEquals("Há»¦Y", _cancel.getText());
		AssertJUnit.assertEquals("Ä�á»’NG Ã�", _submit);
		// check button cancel
		_cancel.click();
	}
	
	/*@SuppressWarnings("unchecked")
	public void checkPopupOrderNotDeli(WebDriver driver) {
		WebElement add = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('" + btn_Add + "');");
		add.click();

		CommonFunctions.pause(1);
		WebElement btn_DatTruoc = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + btn_order + "')[1];");
		WebElement getPrice = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.add-minus-food span');");

		//String _getPrice = getPrice.getText();
		btn_DatTruoc.click();
		//
		CommonFunctions.pause(1);
		List<WebElement> message = (List<WebElement>) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-modal p span');");

		String titlePopup = "";
		int i = 0;
		String str;
		for (i = 0; i< message.size()-2; i++) {
			if (i != 0 && i % 2 == 0){
				str = (String)((JavascriptExecutor) driver)
					.executeScript("return document.querySelectorAll('#alert-modal p span')["+ i +"].innerText;");
				titlePopup += " " + str;
			} else {
				str = (String)((JavascriptExecutor) driver)
						.executeScript("return document.querySelectorAll('#alert-modal p span')["+ i +"].innerText;");
				titlePopup += str;
			}	
		}
		
		String getPricePopup = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-msg').innerText;");
		getPricePopup = CommonFunctions.spilitString(getPricePopup);
		
		
		WebElement defaultPrice = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg i')[1];");

		String _getPricePopup = CommonFunctions.chuyenDoiKyTu(getPricePopup.getText(), " ", "");
		_getPrice = CommonFunctions.chuyenDoiKyTu(_getPrice, "", "");

		String _option1 = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg label')[0].innerText;");
		String _option2 = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg label')[1].innerText;");
		// check button name
		WebElement _cancel = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-modal a.btn-cancel');");
		String _submit = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-modal a.btn-ok').innerText;");

		CommonFunctions.pause(2);
		AssertJUnit.assertEquals("DeliveryNow thÃ´ng bÃ¡o", titlePopup);
		AssertJUnit.assertEquals(_getPrice, _getPricePopup);
		AssertJUnit.assertEquals("100,000 Ä‘", defaultPrice.getText()); // default
																		// price
		AssertJUnit.assertEquals("Tiáº¿p tá»¥c chá»�n thÃªm mÃ³n", _option1);
		AssertJUnit.assertEquals("Cháº¥p nháº­n phÃ­ dá»‹ch vá»¥ lÃ  " + SearchPage.priceRequire + " Ä‘ Â vÃ  tiáº¿n hÃ nh thanh toÃ¡n",
				_option2); // input
		AssertJUnit.assertEquals("Há»¦Y", _cancel.getText());
		AssertJUnit.assertEquals("Ä�á»’NG Ã�", _submit);
		// check button cancel
		_cancel.click();
	}*/

	public void checkButtonPopupOrderPrice(WebDriver driver, String how, int index) {
		CommonFunctions.pause(1);
		WebElement btn_DatTruoc = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('" + btn_order + "')[1];");
		btn_DatTruoc.click();
		CommonFunctions.pause(2);
		WebElement _cancel = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-modal a.btn-cancel');");
		WebElement _submit = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('#alert-modal a.btn-ok');");

		WebElement oRadio = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('#alert-msg label')[" + index + "];");
		oRadio.click();
		CommonFunctions.pause(1);
		if (how.equalsIgnoreCase("cancel")) {
			_cancel.click();
			CommonFunctions.pause(1);
		} else if (how.equalsIgnoreCase("submit")) {
			_submit.click();
			CommonFunctions.pause(1);
		}
	}

	public boolean checkPopupOrderInfo(WebDriver driver) {
		CommonFunctions.pause(2);
		WebElement element = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.step.active')");
		if (element.isDisplayed())
			return true;
		return false;
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
	
	public String getRestaurantInfo(WebDriver driver){
		CommonFunctions.pause(1);
		String _name = (String)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('.name-hot-restaurant').innerText;");
		String _addRess = (String)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('.info-basic-hot-restaurant > p').innerText;");		
		return _name + "|" + _addRess;
	}
	
	public void checkOrderInfo(WebDriver driver, String restaurantName) {
		String[] str = restaurantName.split("\\|", restaurantName.indexOf("|"));
		String _name = str[0];
		String _addRess = str[1];
		CommonFunctions.pause(2);
		String name = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('.delivery-pick-adress >div>span')[0].innerText;");
		String addRess = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('.delivery-pick-adress >div>span')[1].innerText;");

		String _statusOrder_2 = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.checkout-steps div.step:nth-of-type(2)').innerText;");
		String _statusOrder_3 = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.checkout-steps div.step:nth-of-type(3)').innerText;");

		_statusOrder_2 = CommonFunctions.chuanHoa(_statusOrder_2);
		_statusOrder_2 = _statusOrder_2.substring(2, _statusOrder_2.length());

		_statusOrder_3 = CommonFunctions.chuanHoa(_statusOrder_3);
		_statusOrder_3 = _statusOrder_3.substring(2, _statusOrder_3.length());

		WebElement redDot = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.delivery-pick-dot span');");
		WebElement GreenDot = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.delivery-drop-dot span');");
		String lbl_Adress = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('.delivery-drop div div span')[1].innerText;");
		String lbl_Option = (String) ((JavascriptExecutor) driver).executeScript(
				"return document.querySelectorAll('.modal-content .ng-scope div.capitalize')[0].innerText;");
		String lbl_Time = (String) ((JavascriptExecutor) driver).executeScript(
				"return document.querySelectorAll('.modal-content .ng-scope div.capitalize')[1].innerText;");
		String lbl_Note = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('label.active').innerText;");
		WebElement txt_FullName = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('fullname');");
		WebElement txt_Phone = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('phone');");
		WebElement txt_Address = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('address');");
		WebElement txt_Note = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('note1');");
		// check test
		
		//Assert.assertEquals(mMap.get("TimeAddressReciver"), _statusOrder_1);
		Assert.assertEquals("ThÃ´ng tin Ä‘Æ¡n hÃ ng", _statusOrder_2);
		Assert.assertEquals("HoÃ n táº¥t", _statusOrder_3);
		Assert.assertEquals("rgba(204, 0, 0, 1)", redDot.getCssValue("color"));
		Assert.assertEquals("rgba(0, 128, 0, 1)", GreenDot.getCssValue("color"));
		Assert.assertEquals(_name, name);
		Assert.assertEquals(addRess, _addRess + ", Viet Nam");
		Assert.assertEquals("Ä�á»‹a Ä�iá»ƒm Nháº­n HÃ ng", lbl_Adress);
		Assert.assertEquals("Chá»�n HÃ¬nh Thá»©c Giao HÃ ng", lbl_Option);
		Assert.assertEquals("Thá»�i Gian Nháº­n HÃ ng", lbl_Time);
		Assert.assertEquals("Ghi chÃº cho Ä‘Æ¡n hÃ ng", lbl_Note);
		Assert.assertEquals("Há»� tÃªn", txt_FullName.getAttribute("placeholder"));
		Assert.assertEquals("Sá»‘ Ä‘iá»‡n thoáº¡i", txt_Phone.getAttribute("placeholder"));
		Assert.assertEquals("Vd: sá»‘ 10, NgÃµ 20, NgÃ¡ch 30, Háº»m 40, PhÆ°á»�ng 15, Ä�Æ°á»�ng Cá»‘ng Quá»³nh",
				txt_Address.getAttribute("placeholder"));
		Assert.assertEquals("VÃ­ dá»¥: TÃ²a nhÃ  ABC, láº§u 8, cho thÃªm 2 ly nhá»±a....", txt_Note.getAttribute("placeholder"));
		// check control
		Assert.assertEquals(true,
				checkCombobox(driver, "return document.querySelectorAll('.browser-default.ng-pristine')[0];"));
//		Assert.assertEquals(true,
//				checkCombobox(driver, "return document.querySelectorAll('.browser-default.ng-pristine')[1];"));
		Assert.assertEquals(true, checkControl(driver, "return document.querySelector('input.datepicker');"));
		Assert.assertEquals(true, checkControl(driver, "return document.querySelector('#confirminfo a');"));
		Assert.assertEquals(true, checkControl(driver, "return document.querySelector('#confirminfo a');"));
		Assert.assertEquals(true, checkControl(driver,
				"return document.querySelectorAll('#confirminfo .modal-footer>a.modal-action')[0];"));
	}	
	
	public void insertOrderReceive(WebDriver driver){
		CommonFunctions.pause(1);
		WebElement txt_FullName = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('fullname');");
		WebElement txt_Phone = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('phone');");
		WebElement txt_Address = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('address');");
		WebElement txt_Note = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('note1');");
		WebElement btn_Continue  = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('#confirminfo .modal-footer a.modal-action');");
		// input data
		txt_FullName.clear();
		txt_FullName.sendKeys("Kha NghÄ©a TÃ i");
		
		txt_Phone.clear();
		txt_Phone.sendKeys("0909959982");
		
		txt_Address.clear();
		txt_Address.sendKeys("244 Cá»‘ng Quá»³nh P. Pháº¡m NgÅ© LÃ£o, Q. 1");
		
		txt_Note.clear();
		txt_Note.sendKeys("Ä�áº¿n nÆ¡i thÃ¬ gá»�i, giao hÃ ng Ä‘Ãºng giá»�");
		
		btn_Continue.click();
		// 
		// check new popup
		//
		CommonFunctions.pause(1);
		WebElement btn_Finish  = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelectorAll('a.modal-action.waves-effect.waves-effect')[1]");
		btn_Finish.click();
		CommonFunctions.pause(1);
		WebElement btn_Confirm  = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelectorAll('a.modal-action.waves-effect.waves-effect')[3]");
		btn_Confirm.click();
	}
	
	public void addOrderReceive(WebDriver driver){
		CommonFunctions.pause(1);
		//check control 
		String hplAddJE = "return document.querySelector('.delivery-drop a.add-new-addres');";
		String hplEditJE = "return document.querySelector('#editAddress');";
		String selectOption = "return document.querySelector('.delivery-drop>div>div>a');";
		boolean isHplAdd = checkControl(driver, hplAddJE);
		boolean isHplEdit = checkControl(driver, hplEditJE);
		if(isHplAdd == true && isHplEdit == true){
			// code
			WebElement _add = (WebElement)((JavascriptExecutor) driver)
					.executeScript(hplAddJE);
			_add.click();
			CommonFunctions.pause(1);
			Assert.assertEquals(true, checkControl(driver, selectOption));
			insertOrderReceive(driver);			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void editOrderReceive(WebDriver driver){
		String hplAddJE = "return document.querySelector('.delivery-drop a.add-new-addres');";
		String hplEditJE = "return document.querySelectorAll('#editAddress');"; /////////////
		String selectOption = "return document.querySelector('.delivery-drop>div>div>a');";
		boolean isHplAdd = checkControl(driver, hplAddJE);
		boolean isHplEdit = checkControl(driver, hplEditJE);
		if(isHplAdd == true){			
			WebElement _edit = (WebElement)((JavascriptExecutor) driver)
					.executeScript(hplEditJE);
			CommonFunctions.pause(1);
			Assert.assertEquals(true, checkControl(driver, selectOption));
			insertOrderReceive(driver);			
		}
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
	
	private boolean checkCombobox(WebDriver driver, String locatorDOM){
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
	}
	
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

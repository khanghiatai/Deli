package libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import objects.OrderGroupPage;
import support.CommonFunctions;

public class OrderGroupFunctions extends OrderGroupPage{

	public OrderGroupFunctions(WebDriver driver) {
		super(driver); 
	}
	
	public void checkBlockOrderGroup(WebDriver driver){		 
		String str1 = "Nhiều người cùng đặt, tiện lợi và nhanh hơn";
		
		String str2 = (String)((JavascriptExecutor)driver)
				.executeScript("return document.querySelector('div.block-tooltip p').textContent");
		AssertJUnit.assertEquals(str1, str2);
		WebElement _spanClose = (WebElement)((JavascriptExecutor)driver)
		 	.executeScript("return document.querySelector('span.close')");
		_spanClose.click();					
	}
	
	public void checkPopupShareLink(WebDriver driver){
		CommonFunctions.pause(1);
		WebElement _btn_Close = (WebElement)((JavascriptExecutor)driver)
			 	.executeScript("return document.querySelector('.btn-order-group');");		
		_btn_Close.click();		

		String popupHerder = (String)((JavascriptExecutor)driver)
	 	.executeScript("return document.querySelector('.modal-header').innerHTML;");	
		
		String popupContent = (String)((JavascriptExecutor)driver)
			 	.executeScript("return document.querySelector('.group-share-title').innerHTML;");
		
		CommonFunctions.pause(1);
		String _strlinkShare = (String)((JavascriptExecutor)driver)
			 	.executeScript("return document.getElementById('shareGroupOrderLink').value;");
		
		WebElement shareLink = (WebElement)((JavascriptExecutor)driver)
			 	.executeScript("return document.getElementById('shareGroupOrderLink');");

		shareLink.click();
		String message = (String)((JavascriptExecutor)driver)
			 	.executeScript("return document.getElementById('shareGroupOrderLink').value;");

		CommonFunctions.pause(1);
		AssertJUnit.assertEquals("Chia sẻ cho nhóm", popupHerder); 
		AssertJUnit.assertEquals("Copy link và gửi cho nhóm", popupContent);		
		AssertJUnit.assertEquals("Copy thành công. Hãy gửi cho nhóm của bạn", message);
		AssertJUnit.assertEquals("Open new tab",driver.findElement(By.linkText("Open new tab")).getText());
		
		WebElement popupClose = (WebElement)((JavascriptExecutor)driver)
			 	.executeScript("return document.querySelector('#sharegroupinfo a.modal-close');");
		popupClose.click();
		// check url again  
		AssertJUnit.assertEquals(driver.getCurrentUrl(), _strlinkShare);
	}

}

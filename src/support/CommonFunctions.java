package support;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {

	/**
	 * Description: This method use to wait for element present
	 * 
	 * @param driver
	 * @param how
	 * @param locator
	 * @param Timeout
	 */
	public static void waitForElementPresent(WebDriver driver, String how, String locator, int Timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		try {
			if (how.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
			} else if (how.equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
			} else if (how.equalsIgnoreCase("css")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
			} else if (how.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			} else {
				// System.out.println("Cannot find element with: " + how + " = "
				// + locator);
			}
		} catch (Exception e) {
			System.out.println("try...catch: " + "\n- " + how + "\n- " + locator + "\n- " + Timeout);
		}
	}

	/**
	 * Description: This method use to wiat for element disappear
	 * 
	 * @param driver
	 * @param how
	 * @param locator
	 * @param Timeout
	 */
	public static void waitForElementDisappear(WebDriver driver, String how, String locator, int Timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		try {
			if (how.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
				System.out.println("id");
			} else if (how.equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(locator)));
				System.out.println("name");
			} else if (how.equalsIgnoreCase("css")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));
				System.out.println("css");
			} else if (how.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
				System.out.println("xpath");
			} else {
				System.out.println("Cannot find element with: " + how + " = " + locator);
			}
		} catch (Exception e) {
			System.out.println("try...catch: " + "\n- " + how + "\n- " + locator + "\n- " + Timeout);
		}
	}

	/**
	 * Description: this method use to type in text box
	 * 
	 * @param driver
	 * @param how
	 * @param locator
	 * @param key
	 */
	public static void sendKeys(WebDriver driver, String how, String locator, String key) {
		if (how.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).clear();
			driver.findElement(By.id(locator)).sendKeys(key);
		} else if (how.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).clear();
			driver.findElement(By.name(locator)).sendKeys(key);
		} else if (how.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(locator)).clear();
			driver.findElement(By.cssSelector(locator)).sendKeys(key);
		} else if (how.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).clear();
			driver.findElement(By.xpath(locator)).sendKeys(key);
		} else {
			System.out.println("Cannot find element: " + how + " = " + locator);
		}
	}

	/**
	 * Description: This method use to type in text box
	 * 
	 * @param el
	 * @param key
	 */
	public static void sendKey(WebElement el, String key) {
		el.clear();
		el.sendKeys(key);
	}

	public static void selectedByText(WebDriver driver, String how, String locator, String text) {
		Select select = null;
		if (how.equalsIgnoreCase("id")) {
			select = new Select(driver.findElement(By.id(locator)));
			select.selectByVisibleText(text);
		} else if (how.equalsIgnoreCase("name")) {
			select = new Select(driver.findElement(By.id(locator)));
			select.selectByVisibleText(text);
		} else if (how.equalsIgnoreCase("css")) {
			select = new Select(driver.findElement(By.id(locator)));
			select.selectByVisibleText(text);
		} else if (how.equalsIgnoreCase("xpath")) {
			select = new Select(driver.findElement(By.id(locator)));
			select.selectByVisibleText(text);
		} else {
			System.out.println("Cannot find element: " + how + " = " + locator);
		}
	}

	/**
	 * Description: This method use to select text
	 * 
	 * @param el
	 * @param text
	 */
	public static void selectedByText(WebElement el, String text) {
		Select select = new Select(el);
		select.selectByVisibleText(text);
	}

	/**
	 * Description: this method use to select text
	 * 
	 * @param driver
	 * @param how
	 * @param locator
	 * @param index
	 */
	public static void selectedByIndex(WebDriver driver, String how, String locator, int index) {
		Select select = null;
		if (how.equalsIgnoreCase("id")) {
			select = new Select(driver.findElement(By.id(locator)));
			select.selectByIndex(index);
		} else if (how.equalsIgnoreCase("name")) {
			select = new Select(driver.findElement(By.name(locator)));
			select.selectByIndex(index);
		} else if (how.equalsIgnoreCase("css")) {
			select = new Select(driver.findElement(By.cssSelector(locator)));
			select.selectByIndex(index);
		} else if (how.equalsIgnoreCase("xpath")) {
			select = new Select(driver.findElement(By.xpath(locator)));
			select.selectByIndex(index);
		} else {
			System.out.println("Cannot find element: " + how + " = " + locator);
		}
	}

	/**
	 * Description: this method use to select index
	 * 
	 * @param el
	 * @param index
	 */
	public static void selectedByIndex(WebElement el, int index) {
		Select select = new Select(el);
		select.selectByIndex(index);
	}

	/**
	 * Descriptions: this method use to click button, radio button, check box
	 * 
	 * @param el
	 */
	public static void click(WebElement el) {
		el.click();
	}

	/**
	 * Descriptions: this method use to set text into text box control
	 * 
	 * @param driver
	 * @param how
	 *            How to use find locator
	 * @param locator
	 * @param inputText
	 */
	public static void setText(WebDriver driver, String how, String locator, String inputText) {
		if (how.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).clear();
			driver.findElement(By.id(locator)).sendKeys(inputText);
		} else if (how.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).clear();
			driver.findElement(By.name(locator)).sendKeys(inputText);
		} else if (how.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).clear();
			driver.findElement(By.xpath(locator)).sendKeys(inputText);
		} else if (how.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(locator)).clear();
			driver.findElement(By.cssSelector(locator)).sendKeys(inputText);
		} else
			System.err.println("Cannot find element with: " + how + "=" + locator);
	}

	/**
	 * Descriptions: this method use to click button, radio button, check box,
	 * v..v...
	 * 
	 * @param driver
	 * @param how
	 *            How to use find locator
	 * @param locator
	 */
	public static void click(WebDriver driver, String how, String locator) {
		if (how.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).click();
		} else if (how.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).click();
		} else if (how.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).click();
		} else if (how.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(locator)).click();
		} else {
			System.err.println("Cannot find element with: " + how + "=" + locator);
		}
	}

	/**
	 * Descriptions: this method use to get text from web browser
	 * 
	 * @param driver
	 * @param how
	 *            How to use find locator
	 * @param locator
	 * @return
	 */
	public static String getText(WebDriver driver, String how, String locator) {
		if (how.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(locator)).getText();
		} else if (how.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(locator)).getText();
		} else if (how.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(locator)).getText();
		} else if (how.equalsIgnoreCase("css")) {
			return driver.findElement(By.cssSelector(locator)).getText();
		} else {
			return "Cannot find element with: " + how + "=" + locator;
		}
	}

	/**
	 * Descriptions: This method use to get Browser
	 * 
	 * @param browserType
	 *            FireFox, Chrome, Ie
	 * @return WebDriver
	 * @throws MalformedURLException
	 */
	public static WebDriver getBrowser(String browserType) throws MalformedURLException {
		WebDriver driver = null;
		if (browserType.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		} else if (browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
			// maximum window
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		} else if (browserType.equalsIgnoreCase("Ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
			driver = new InternetExplorerDriver();
		} else if (browserType.equalsIgnoreCase("Chrome_Android")) {
			// ChromeOptions options = new ChromeOptions();
			// options.setExperimentalOption("androidPackage",
			// "com.android.browser");
			// options.setExperimentalOption("androidActivity",
			// "com.android.browser.BrowserActivity");

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// capabilities.setCapability("deviceName", "03157df3e8313e06");
			capabilities.setCapability("deviceName", "192.168.232.102:5555");
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("platformVersion", "5.1");
			capabilities.setCapability("browserName", "Browser");
			// capabilities.setCapability(ChromeOptions.CAPABILITY,options) ;
			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		} else {
			System.out.println("Cannot find browser: " + browserType);
		}
		return driver;
	}

	/**
	 * Descriptions: This method use to get browser by RC
	 * 
	 * @param browserType
	 * @param urlRemote
	 * @return WebDriver
	 * @throws MalformedURLException
	 */
	public static WebDriver getBrowser(String browserType, String urlRemote) throws MalformedURLException {
		WebDriver driver = null;
		if (browserType.equalsIgnoreCase("FireFox")) {
			driver = new RemoteWebDriver(new URL(urlRemote), DesiredCapabilities.firefox());
		} else if (browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Project\\AK7\\libs\\chromedriver.exe");
			driver = new RemoteWebDriver(new URL(urlRemote), DesiredCapabilities.chrome());
		} else if (browserType.equalsIgnoreCase("Ie")) {
			System.setProperty("webdriver.ie.driver", "E:\\Project\\AK7\\libs\\IEDriverServer.exe");
			driver = new RemoteWebDriver(new URL(urlRemote), DesiredCapabilities.internetExplorer());
		} else {
			System.out.println("Cannot find: " + urlRemote + " or " + "browser: " + driver);
		}
		return driver;
	}

	/**
	 * Description: This method use to visit web site
	 * 
	 * @param driver
	 * @param url
	 */
	public static void visit(WebDriver driver, String url) {
		driver.get(url);
		// driver.manage().window().maximize();
	}

	public static boolean verifyElementEnabled(WebDriver driver, String how, String locator) {
		waitForElementPresent(driver, how, locator, 60);
		return getElement(driver, how, locator).isEnabled();
	}

	public static boolean verifyElementDisplayed(WebDriver driver, String how, String locator) {
		waitForElementPresent(driver, how, locator, 60);
		return getElement(driver, how, locator).isDisplayed();
	}

	public static WebElement getElement(WebDriver driver, String how, String locator) {
		WebElement el = null;
		if (how.equalsIgnoreCase("id")) {
			el = driver.findElement(By.id(locator));
		} else if (how.equalsIgnoreCase("name")) {
			el = driver.findElement(By.name(locator));
		} else if (how.equalsIgnoreCase("xpath")) {
			el = driver.findElement(By.xpath(locator));
		} else if (how.equalsIgnoreCase("css")) {
			el = driver.findElement(By.cssSelector(locator));
		} else {
			System.err.print("Wrong input arguments" + how + locator);
		}
		return el;
	}

	public static void switchToTab(WebDriver driver, int n) {
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(n));
		}
	}

	public static void pause(int seconds) {
		Date start = new Date();
		Date end = new Date();
		while (end.getTime() - start.getTime() < seconds * 1000) {
			end = new Date();
		}
	}

	// Cat khoang trang
	public static String chuanHoa(String str) {
		str = str.trim();
		str = str.replaceAll("\\s+", " ");
		return str;
	}

	public static String chuyenDoiKyTu(String str, String chuanHoa, String thayThe) {
		// str = chuanHoa(str);
		String temp[] = str.split(chuanHoa); // bo dau ","
		str = ""; // ? ^-^
		for (int i = 0; i < temp.length; i++) {
			str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
			if (i < temp.length - 1) // ? ^-^
				str += thayThe; // cong them ky tu thay the
		}
		return str;
	}

	// Chuyen tieng Viet co dau thanh tieng Viet khong dau
	public static String removeAccent(String s) { 
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}
	
	// s = s.substring(0, s.length()-1); // xoa ky tu
	
	public static void selectRadioButton(WebDriver driver, String css, int index){
		WebElement oRadio = (WebElement)((JavascriptExecutor)driver)
				.executeScript("return document.querySelectorAll('" + css + "[" + index + "]')");
		oRadio.click();
	}
	
//	public static String spilitFirstString(String str, char c){
//		int index =  str.indexOf(c);
//		str = str.substring(0, index + 1);
//		return str;
//	}
	
	public static String spilitLastString(String str, String c){
		int index =  str.indexOf(c);
		int lengh = str.length();
		str = str.substring(index, lengh);
		return str;
	}
	
	public static void waitForControl(WebDriver driver, WebElement controlName) {
	    new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(controlName));
	}	
	
	public static int getNumberOfString(String str) {
		char c1;
		String strNum = "";
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			c1 = str.charAt(i);			
			try {
				num = (int)c1 - '0';
				if(num > 0 && num <= 9 && c1 != ' ') {
					strNum += Integer.toString(num); 
				}
			} catch (Exception e) {
				continue;
			}			
		}	
		return Integer.parseInt(strNum);
	}
}

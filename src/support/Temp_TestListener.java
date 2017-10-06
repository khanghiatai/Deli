package support;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import objects.LoginTest;

public class Temp_TestListener implements ITestListener{
//	private static int coutFail = 0;
	//private static int coutPass = 0;
	
	private static String fileSeperator = System.getProperty("file.separator");

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getName());
		String testClassName = arg0.getName();
		System.out.println(testClassName);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	//Chua chup dc hinh nhieu case sai, 
	@Override
	public void onTestFailure(ITestResult result) {		
		
//		System.out.println("***** Error " + result.getName() + " test has failed *****");
//
//		String testClassName = getTestClassName(result.getInstanceName()).trim();
//
//		String testMethodName = result.getName().toString().trim();
//		String screenShotName = testMethodName + "_" + coutFail + ".png";
//
//		Object currentClass = result.getInstance(); // 
        //WebDriver driver = ((LoginTest) currentClass).getDriver();		// get current driver
		
		/*if (driver != null) {
			String imagePath = ".." + fileSeperator + "Screenshots"
					+ fileSeperator + "Results" + fileSeperator + testClassName
					+ fileSeperator
					+ takeScreenShot(driver, screenShotName, testClassName);
			System.out.println("Screenshot can be found : " + imagePath);
		}	
		coutFail++;*/
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/***
	 * Description: if use ITestResult, will return test Function Name, use function getTextClassName to get test Class Name
	 * @param testName
	 * getTestClassName(arg0.getInstanceName()).trim()
	 * @return
	 */
	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}
	
	public static String takeScreenShot(WebDriver driver, String screenShotName, String testName) {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testName, screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);

			return screenShotName;
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}
	
	
//	@Override
//	public void onTestFailure(ITestResult result) {
//		System.out.println("***** Error " + result.getName() + " test has failed *****");
//	}
//
//	@Override
//	public void onTestStart(ITestResult result) {
//		System.out.println("***** Start " + result.getName() + " test has Start *****");
//	}
	
}

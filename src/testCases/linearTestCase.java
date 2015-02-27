package testCases;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.IOSLaunch;

public class linearTestCase extends IOSLaunch
{
	long totalTime;
	@Before
	public void init()
	{
	}
	@Before
	public void endit()
	{
	}
	@Test
	public void doStuff() throws InterruptedException
	{

		//settings
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]")));
		driver.tap(1, element, 1);

		//sign in
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")));
		driver.tap(1, element, 1);

		//more providers
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]")));
		driver.tap(1, element, 1);

		//at&t
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.name("AT&T U-verse")));
		driver.tap(1, element, 1);
		
		//username
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")));
		element.click();
		element.sendKeys(mvpd.getAccount().getUsername());
		//password
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]")));
		element.click();
		element.sendKeys(mvpd.getAccount().getPassword());
		//put scroll here 
		//put scroll here
		
		
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]")));
		driver.tap(1, element, 1);
		//sign in 
		long startTime = System.currentTimeMillis();
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")));
		driver.tap(1, element, 1);
		
		//Thread.sleep(100000);
		
		//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]
	
		long endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("login time" + totalTime);
		
		//play video
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAStaticText[4]")));
		driver.tap(1, element, 1);
		
		//pause video 
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.name("btn play")));
		driver.tap(1, element, 1);
		
		// Get the screenshot
		driver = (AppiumDriver) new Augmenter().augment(driver);
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		System.out.println("Screenshot completed");
		try{

			File testScreenShot = new File("/Users/deang/Documents/workspace/AppThwackPilotProject/screenShots");
			// Copy the file to screenshot folder
			FileUtils.copyFile(scrFile, testScreenShot);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//back button 
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.name("iPhone video backarrow")));
		driver.tap(1, element, 1);
		
		//settings
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]")));
		driver.tap(1, element, 1);

		//sign out
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")));
		driver.tap(1, element, 1);
		
		Thread.sleep(50000);
	}

}

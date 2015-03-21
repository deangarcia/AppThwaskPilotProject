package testCases;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.IOSLaunch;

public class PlayVideo extends IOSLaunch {
	private static int NUMBER_OF_SCREENSHOTS = 3;
	
	@Test
	public void playVideo() throws InterruptedException {
		boolean notOnNow = false;
		//int count = 4;
	
		//Start of test without searching for element and location
		List<WebElement> elements;
		elements = driver.findElementsByName("ON NOW");
		System.out.println("first find");
		System.out.println(elements.size() + " initial element size");
		while (elements.size() == 0) {
			System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
			elements = driver.findElementsByName("ON NOW");
			System.out.println(elements.size() + " element size");
		}
		elements = driver.findElementsByName("ON NOW");
		System.out.println(elements.size() + " Second element size");
		int count = elements.size();
		// End of test without searching for elements first
		
		while(!notOnNow)
		{
			// notOnNow for element location search
			//System.out.println("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[" + count + "]/UIAStaticText");
			//notOnNow = AAA.playVideo("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[" + count + "]/UIAStaticText");
			
			//count++;
		for(int i = 0; i < count; i++)
		{
			AAA.playVideo(i);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				WebElement ele = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				reverseGenieEffect(ele);
				AAA.pauseVideo("btn play");
				takeScreenShots(ele, "Suddenlink", ("Chip" + count));
				reverseGenieEffect(ele);
				AAA.backbutton("iPhone video backarrow");
		}

		}
	}
	
	/*
	public void playVid() {
		boolean notOnNow = false;
		int count = 4;
		
		//Start of test without searching for element and location
		List<WebElement> elements;
		elements = driver.findElementsByName("ON NOW");
		System.out.println("first find");
		System.out.println(elements.size() + " initial element size");
		while (elements.size() == 0) {
			System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
			elements = driver.findElementsByName("ON NOW");
			System.out.println(elements.size() + " element size");
		}
		elements = driver.findElementsByName("ON NOW");
		System.out.println(elements.size() + " Second element size");
		int count = elements.size();
		// End of test without searching for elements first

		while(!notOnNow)
		{
			// notOnNow for element location search
			System.out.println("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[" + count + "]/UIAStaticText");
			notOnNow = AAA.playVideo("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[" + count + "]/UIAStaticText");
			//notOnNow = AAA.playVideo(count);
			//count++;
			if(!notOnNow)
			{
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				WebElement ele = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				reverseGenieEffect(ele);
				AAA.pauseVideo("btn play");
				takeScreenShots(ele, "Suddenlink", ("Chip" + count));
				reverseGenieEffect(ele);
				AAA.backbutton("iPhone video backarrow");
			}
		}
	}


	try
	{
		AAA.waitForElement_xpath("ssx");
	}
	catch(TimeoutException e)
	{
		e.printStackTrace();
		System.out.println("Im doing something");
		AAA.logout();
	}
	*/
	
/*
	public static boolean timeFrame(String t)
	{
		long MSEC_SINCE_EPOCH = System.currentTimeMillis();
		Date instant = new Date( MSEC_SINCE_EPOCH );
		int minutes = 0;
		if(instant.getMinutes() < 29)
			minutes = 0;
		else
			minutes = 29;
		
		if(!(t.contains("30")))
		{
			if(table.get(t) <= instant.getHours())
			{
				return true;
			}
		}
		else
		{
			if(table.get(t) <= instant.getHours() + minutes)
			{
				return true;
			}
		}
		return false;
	}
*/
	
	public static void reverseGenieEffect(WebElement e) {
		if (!(e.isDisplayed())) {
			driver.tap(1, 290, 210, 1);
		}
	}

	//also throw in video name
	public static void takeScreenShots(WebElement ele, String mvpdName, String videoId)
			throws InterruptedException {
		// Get the screenshot
		for (int i = 0; i < NUMBER_OF_SCREENSHOTS; i++) {
			driver = (IOSDriver) new Augmenter().augment(driver);
			try {
				File scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				File calsspathRoot = new File(System.getProperty("user.dir"));
				// workspace space is set in application folder
				File testScreenShot = new File(calsspathRoot + "/screenShots",
						"preRollAds" + mvpdName + videoId + i); // Copy the file to // screenshot folder
				FileUtils.copyFile(scrFile, testScreenShot);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//hit middle button to play
			reverseGenieEffect(ele);
			Thread.sleep(10000);
			reverseGenieEffect(ele);
			try{
				AAA.pauseVideo("btn play");
			}
			catch(TimeoutException t)
			{
				driver.resetApp();
				AAA.logout();
			}
		}
	}
}

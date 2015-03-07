package testCases;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;

import driver.IOSLaunch;

public class PlayVideo extends IOSLaunch {
	private static int NUMBER_OF_SCREENSHOTS = 3;
	//@Test
	//take out static to make a test
	public static void playVid(String mvpdName) throws InterruptedException {
		AAA.playVideo("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAStaticText");
		Thread.sleep(50000);
		WebElement ele = driver.findElement(By
				.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));
		reverseGenieEffect(ele);
		AAA.pauseVideo("btn play");
		takeScreenShots(ele,mvpdName);
		reverseGenieEffect(ele);
		AAA.backbutton("iPhone video backarrow");
		Thread.sleep(15000);
	}

	public static void reverseGenieEffect(WebElement e) {
		while (!(e.isDisplayed())) {
			driver.tap(1, 290, 210, 1);
		}
	}
	public static void takeScreenShots(WebElement ele, String mvpdName) throws InterruptedException
	{
		// Get the screenshot
		for (int i = 0; i < NUMBER_OF_SCREENSHOTS; i++) {
			driver = (IOSDriver) new Augmenter().augment(driver);
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			File calsspathRoot = new File(System.getProperty("user.dir"));
			try {
				// workspace space is set in application folder
				File testScreenShot = new File(calsspathRoot + "/screenShots",
						"preRollAds" + mvpdName + i); // Copy the file to screenshot folder
				FileUtils.copyFile(scrFile, testScreenShot);
			} catch (IOException e) {
				e.printStackTrace();
			}
			AAA.pauseVideo("play circle");
			Thread.sleep(10000);
			reverseGenieEffect(ele);
			AAA.pauseVideo("btn play");
		}
	}
}

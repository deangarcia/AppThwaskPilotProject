package testCases;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.IOSLaunch;

public class AAA extends IOSLaunch {

	private String video_xpath = "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/"
			+ "UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell";
	private WebElement ele_ = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));
	
	private WebElement waitForElementXpath(String xpath,int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(xpath)));
		return element;
		
	}
	
	private WebElement waitForElementName(String name,int waitTime) {
		
		wait = new WebDriverWait(driver, waitTime);
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.name(name)));
		return element;
	}
	public static void tap(WebElement element) {
		driver.tap(1, element, 1);
	}

	public static void click(WebElement element) {
		element.click();
	}
	private void  takescreenshots(String xpath) throws InterruptedException {
		
		//tap(waitForElementXpath(xpath,60));
		driver = (IOSDriver) new Augmenter().augment(driver);
		Thread.sleep(9000);
		// Get the screenshot
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		System.out.println("Screenshot completed");
		try{

			File calsspathRoot = new File(System.getProperty("user.dir")); 
			//workspace space is set in application folder
			
			File testScreenShot = new File(calsspathRoot + "screenShots", "preRollAds");
			// Copy the file to screenshot folder
			FileUtils.copyFile(scrFile, testScreenShot);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void pressButton(String name){
		while (!(ele_.isDisplayed())) {
			driver.tap(1, 290, 210, 1);
			tap( waitForElementName(name,15));	
			break;
		}
		
	}
	public void baby(String accountName) {
		
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIANavigationBar[1]/UIAButton[4]",60));
	}
	public void loginAccountSettings (String accountName) {
		
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIANavigationBar[1]/UIAButton[4]",60));
		//sign in
		tap (waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/"
				+ "UIATableCell[1]/UIAStaticText[1]",60));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.swipe(225,500,225,250,3000);
		driver.swipe(290, 420, 290, 234, 3000);
		//more providers
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/"
				+ "UIAButton[2]",60));
		//at&t
		WebElement e = driver.findElementByName(accountName);

		// Scroll to works when invisible elements are present in the DOM
		// We know that elements are present on the DOM if they have a y coordinate 
		// Location, 
		driver.scrollTo(accountName);
		tap(waitForElementName(accountName,60));
	}
	
	public boolean login(Accounts account) {
		//username
		WebElement element = waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIAScrollView[1]/UIAWebView[1]/UIATextField[1]",60);
		click(element);
		element.sendKeys(account.getUsername());
		
		//password
		element = waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/"
				+ "UIAWebView[1]/UIATextField[2]",60);
		click(element);
		element.sendKeys(account.getPassword());
		
		//done
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]",60));
		
		//click Sign-In	
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/"
				+ "UIAWebView[1]/UIAButton[1]",60));
		
		
		try{
			  Thread.sleep(1000);
			  //driver.swipe(225,500,225,250,3000);
			  driver.swipe(290, 420, 290, 234, 3000);
			  waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/"
			  		+ "UIAWebView[1]/UIAButton[1]",15).isEnabled();
		} catch (Exception e) {
			return true;
		}
		
		return false;
		
	}
	
	public void playVideos() throws InterruptedException {
		String xpath="";
		Thread.sleep(5000);
		int i=1;
		List<WebElement> labels= driver.findElementsByXPath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell");
		System.out.println("Size:" + labels.size());
		
		while(i>0 && i<= labels.size()) {
			Thread.sleep(5000);
			System.out.println("Iteration:" + i);
			xpath = video_xpath+"["+i+"]"+"/UIAStaticText[1]";
			System.out.println("XPATH: " +xpath);
			WebElement ele = driver.findElementByXPath(xpath);
			String name = ele.getText();
			
			if(!name.equalsIgnoreCase("ON NOW")) {
				break;
			
			}
			//xpath = video_xpath+"["+i+"]"+"/UIAStaticText[3]";
			//name = driver.findElementByXPath(xpath).getText();
			tap(ele);
			takescreenshots(xpath);
			Thread.sleep(9000);
			//pressButton("btn play");
			pressButton("iPhone video backarrow");	
			Thread.sleep(3000);	
			System.out.println("SWIPE");
			int j=0;
			while(j<i){
				//driver.swipe(225,440,225,250,1000);
				driver.swipe(290, 420, 290, 234, 1000);
				j++;
			}
			i++;
		}
		
		
	}
	
	public void logout(){
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]",60));
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/"
				+ "UIATableCell[1]/UIAStaticText[1]",60));
		tap(waitForElementName("OK",60));
	}
	
	/*
	// Same code as above but different approach in vid is the length of a list that contains elements that have 
	// The value ONNOW in them this means the amount of playable videos and the code manually scrolls through that 
	// Many times since each video container is 186 pixels from top to bottom swipe down that far then click in the middle 
	// Of that container to play the video
		public static void playVideo(int vid) throws NoSuchElementException, TimeoutException {
		
		for(int i = 0; i <= vid; i++)
		{
			// If we are on the first video we do not want to swipe at all
			if(i != 0)
				driver.swipe(290, 420, 290, 234, 3000);
		}
		driver.tap(1, 195, 290, 1);
		// Swipe to get more providers
	}
	*/
}

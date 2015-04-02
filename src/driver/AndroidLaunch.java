package driver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testCases.Accounts;

public class AndroidLaunch extends Driver {

	public AndroidLaunch(String deviceName, String platformVersion, String automationName, String application, String userKey, String projectId) throws MalformedURLException
	{	
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", deviceName); // name of the device as listed on the appthwack api
		capabilities.setCapability("platformVersion", platformVersion); // os of the device
		capabilities.setCapability("automationName", automationName); 
		capabilities.setCapability("app", application); // change this for every apk or ipa that you upload to appthwack
		capabilities.setCapability("apiKey", userKey); // this is a unique key tied to a specific user
		capabilities.setCapability("project", projectId); // project key is shown in the project setting in your account
		driver = new AndroidDriver(new URL("https://appthwack.com/wd/hub"), capabilities);
	}
	public AndroidLaunch(String pn, String pv, String dn, String ap, String app) throws MalformedURLException
	{	
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, pn);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pv);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dn);
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, ap);//appkey
		capabilities.setCapability(MobileCapabilityType.APP, app);//file id
		driver = new AndroidDriver(new URL("https://appthwack.com/wd/hub"), capabilities);
	}
	
	public void baby(String accountName) {
		System.out.println("Android Launch");
		//tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/"
			//	+ "UIANavigationBar[1]/UIAButton[4]",60));
	}
	
	public void loginAccountSettings (String accountName) {
		
		// Settings button
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIANavigationBar[1]/UIAButton[4]",60));
		// Sign in
		tap (waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/"
				+ "UIATableCell[1]/UIAStaticText[1]",60));
		
		// Could instead wait for one of the elements to be visible that way we know 
		// We can swipe now 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ios 5 swipe dimensions
		// driver.swipe(225,500,225,250,3000);
		// Swipe to more providers if it is not visible maybe should add in a if statement
		driver.swipe(290, 420, 290, 234, 3000);
		
		// More providers
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/"
				+ "UIAButton[2]",60));
		
		// Find provider
		// Scroll to works when invisible elements are present in the DOM we know 
		// That elements are present on the DOM if they have a y coordinate Location, 
		driver.scrollTo(accountName);
		tap(waitForElementName(accountName,60));
	}
	
	public boolean login(Accounts account) {
		//username
		// Some username are saved automatically after they are put in once this is a web view
		// Issue that I was having with suddenLink might need to throw in a if statement to 
		// Check if the fields are already filled with correct username or password
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
		
		// Driver will wait 5 seconds before searching for an element used to 
		// Replace the Thread.Sleep(5000)
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int i=1;
		List<WebElement> labels= driver.findElementsByXPath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell");
		System.out.println("Size:" + labels.size());
		// Setting driver implicit wait duration to default value which is zero revert 
		// Back to zero because if it is not reset every find element will wait 5 seconds
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		while(i>0 && i<= labels.size()) {
			System.out.println("Iteration:" + i);
			xpath = video_xpath+"["+i+"]"+"/UIAStaticText[1]";
			System.out.println("XPATH: " +xpath);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement ele = driver.findElementByXPath(xpath);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			String name = ele.getText();
			
			if(!name.equalsIgnoreCase("ON NOW")) {
				break;
			
			}
			tap(ele);
			takescreenshots();
			Thread.sleep(9000);
			//pressButton("btn play");
			reverseGenieEffect("iPhone video backarrow",ele);	
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
	public static void takescreenshots() throws InterruptedException {
		
		driver = (AndroidDriver) new Augmenter().augment(driver);
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
	public void logout(){
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]",60));
		tap(waitForElementXpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/"
				+ "UIATableCell[1]/UIAStaticText[1]",60));
		tap(waitForElementName("OK",60));
	}
	
	public WebElement waitForElementXpath(String xpath,int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(xpath)));
		return element;
		
	}

	
	public String video_xpath = "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/"
			+ "UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell";
	
	// This is one of the initial layers of the app, on ios 5 build it has dimension dimensions are
	// not on the ios 4 though if all builds of the apps initial layer had dimensions then it would have to 
	// be fit for the screen and we could use that to make our swipe methods dynamic
}

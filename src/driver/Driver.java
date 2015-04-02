package driver;

import io.appium.java_client.AppiumDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testCases.Accounts;
import testCases.MVPD;
import fileReaders.JsonRead;
import fileReaders.PropertiesReader;

public abstract class Driver {
	
	@BeforeClass
	public void androidAppthwackServer() throws Throwable 
	{
		JsonRead.readJsonFromFile("/Users/deang/Documents/workspace/AppThwackPilotProject/resources/read.json");
		Properties properties = PropertiesReader.getPropertiesFile("capabilities");
		String dn = properties.getProperty("deviceName");
		String pv = properties.getProperty("platformVersion");
		String an = properties.getProperty("automationName");
		String appId = properties.getProperty("app");
		String key = properties.getProperty("apiKey");
		String pid = properties.getProperty("project");
		Driver.setDriver(new AndroidLaunch(dn, pv, an, appId, key, pid));
		
		// AppthWack 
		// capabilities.setCapability("deviceName", "Apple iPhone 5"); // name of the device as listed on the appthwack api
		// capabilities.setCapability("platformVersion", "7.0"); // os of the device
		// capabilities.setCapability("automationName", "appium"); 
		// capabilities.setCapability("app", "206621"); // change this for every apk or ipa that you upload to appthwack
		// capabilities.setCapability("apiKey", "dbd3ee8964e351efc4017617921094461944a118"); // this is a unique key tied to a specific user
		// capabilities.setCapability("project", "40241"); // project key is shown in the project setting in your account
		
	}
	
	public void iosAppthwackServer() throws Throwable 
	{
		JsonRead.readJsonFromFile("/Users/deang/Documents/workspace/AppThwackPilotProject/resources/read.json");
		Properties properties = PropertiesReader.getPropertiesFile("capabilities");
		String dn = properties.getProperty("deviceName");
		String pv = properties.getProperty("platformVersion");
		String an = properties.getProperty("automationName");
		String appId = properties.getProperty("app");
		String key = properties.getProperty("apiKey");
		String pid = properties.getProperty("project");
		Driver.setDriver(new IOSLaunch(dn, pv, an, appId, key, pid));
		
		// AppthWack 
		// capabilities.setCapability("deviceName", "Apple iPhone 5"); // name of the device as listed on the appthwack api
		// capabilities.setCapability("platformVersion", "7.0"); // os of the device
		// capabilities.setCapability("automationName", "appium"); 
		// capabilities.setCapability("app", "206621"); // change this for every apk or ipa that you upload to appthwack
		// capabilities.setCapability("apiKey", "dbd3ee8964e351efc4017617921094461944a118"); // this is a unique key tied to a specific user
		// capabilities.setCapability("project", "40241"); // project key is shown in the project setting in your account
		
	}
	
	public void iosAppuimServer() throws Throwable 
	{
		System.out.println("comes in");
		JsonRead.readJsonFromFile("/Users/deang/Documents/workspace/AppThwackPilotProject/resources/read.json");
		Properties properties = PropertiesReader.getPropertiesFile("capabilities");
		String dn = properties.getProperty("deviceName");
		String pv = properties.getProperty("platformVersion");
		String udid = properties.getProperty("udid");
		String bid = properties.getProperty("bundleId");
		Driver.setDriver(new IOSLaunch(dn, pv, udid, bid));
		
		// AppthWack 
		// capabilities.setCapability("deviceName", "Apple iPhone 5"); // name of the device as listed on the appthwack api
		// capabilities.setCapability("platformVersion", "7.0"); // os of the device
		// capabilities.setCapability("automationName", "appium"); 
		// capabilities.setCapability("app", "206621"); // change this for every apk or ipa that you upload to appthwack
		// capabilities.setCapability("apiKey", "dbd3ee8964e351efc4017617921094461944a118"); // this is a unique key tied to a specific user
		// capabilities.setCapability("project", "40241"); // project key is shown in the project setting in your account
		
	}
	
	public void androidAppiumServer() throws Throwable 
	{
		JsonRead.readJsonFromFile("/Users/deang/Documents/workspace/AppThwackPilotProject/resources/read.json");
		Properties properties = PropertiesReader.getPropertiesFile("capabilities");
		String pn = properties.getProperty("platformName");
		String pv = properties.getProperty("platformVersion");
		String dv = properties.getProperty("deviceName");
		String ap = properties.getProperty("appPackage");
		String app = properties.getProperty("app");
		Driver.setDriver(new AndroidLaunch(pn, pv, dv, ap, app));
		
		// AppthWack 
		// capabilities.setCapability("deviceName", "Apple iPhone 5"); // name of the device as listed on the appthwack api
		// capabilities.setCapability("platformVersion", "7.0"); // os of the device
		// capabilities.setCapability("automationName", "appium"); 
		// capabilities.setCapability("app", "206621"); // change this for every apk or ipa that you upload to appthwack
		// capabilities.setCapability("apiKey", "dbd3ee8964e351efc4017617921094461944a118"); // this is a unique key tied to a specific user
		// capabilities.setCapability("project", "40241"); // project key is shown in the project setting in your account
		
	}
	public void reverseGenieEffect(String name, WebElement ele_){
		while (!(ele_.isDisplayed())) {
			driver.tap(1, 290, 210, 1);
			tap(waitForElementName(name,15));	
			break;
		}
		
	}

	public static void setDriver(Driver d)
	{
		start = d;
	}
	public static void tap(WebElement element) {
		driver.tap(1, element, 1);
	}

	public static void click(WebElement element) {
		element.click();
	}
    public WebElement waitForElementName(String name,int waitTime) {
		
		wait = new WebDriverWait(driver, waitTime);
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.name(name)));
		return element;
	}
    
    public abstract void logout();
    public abstract void loginAccountSettings(String s);
    public abstract boolean login(Accounts a);
    public abstract void playVideos() throws InterruptedException;
    public abstract void baby(String s);
// Everything in the method annotated with the AfterClass occurs at the very end of every junit test
// Important things to encapsulate here are proper shutdown procedures like logging out of an account 
// As well as taking a screen shot of user objects if error occured
@AfterClass
public void tearDown() throws Exception 
{ 	
	driver.quit();
}
	
	public static AppiumDriver driver;
	public static WebDriverWait wait;
	public static ArrayList<MVPD>mvpd = new ArrayList<MVPD>();
	public static Driver start;
		// TODO Auto-generated method stub
		
}

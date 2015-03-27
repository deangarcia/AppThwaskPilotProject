package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import testCases.MVPD;
import testCases.jsonRead;

public abstract class IOSLaunch {
	public static AppiumDriver driver;
	public static WebDriverWait wait;
	public static ArrayList<MVPD>mvpd = new ArrayList<MVPD>();
	public static jsonRead jsonReader =  new jsonRead("/Users/deang/Documents/workspace/AppThwackPilotProject/resources/read.json"); 
	
	@BeforeClass
	public static void launchDriver() throws Throwable 
	{
		jsonReader.readJsonFromFile();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Anrdoid
		/*
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.0.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S3");
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "");//appkey
		// capabilities.setCapability(MobileCapabilityType.APP, myApp);//file id
		*/
		
		
		// AppthWack 
		capabilities.setCapability("deviceName", "Apple iPhone 5"); // name of the device as listed on the appthwack api
		capabilities.setCapability("platformVersion", "7.0"); // os of the device
		capabilities.setCapability("automationName", "appium"); 
		capabilities.setCapability("app", "206621"); // change this for every apk or ipa that you upload to appthwack
		capabilities.setCapability("apiKey", "dbd3ee8964e351efc4017617921094461944a118"); // this is a unique key tied to a specific user
		capabilities.setCapability("project", "40241"); // project key is shown in the project setting in your account
		
		// IOS
		//iphone4s
		//capabilities.setCapability("platformVersion", "7.0");
		//capabilities.setCapability("udid", "aa18c45a49bcea5055be2e894748ccb25665e1c6");
		//capabilities.setCapability("bundleId", "com.uie.foxsports.foxsportsgo");
		//iphone5
		//apabilities.setCapability("udid", "ce7d4a568c96f9886ed561d6aad36007e13fe0ff");
		
		//appium server
		//driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		//appthwack server
		driver = new IOSDriver(new URL("https://appthwack.com/wd/hub"), capabilities);
	}
	
	// Everything in the method annotated with the AfterClass occurs at the very end of every junit test
	// Important things to encapsulate here are proper shutdown procedures like logging out of an account 
	// As well as taking a screen shot of user objects if error occured
	@AfterClass
	public static void tearDown() throws Exception 
	{ 	
		driver.quit();
	}
}

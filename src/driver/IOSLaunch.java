package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

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
		capabilities.setCapability("deviceName", "Raj Bangaru Samy’s iPhone");
		capabilities.setCapability("platformName", "ios"); 
		capabilities.setCapability("bundleId", "com.uie.foxsports.foxsportsgo");
		//iphone4s
		capabilities.setCapability("platformVersion", "7.0");
		capabilities.setCapability("udid", "aa18c45a49bcea5055be2e894748ccb25665e1c6");
		//iphone5
		//capabilities.setCapability("udid", "ce7d4a568c96f9886ed561d6aad36007e13fe0ff");
		//capabilities.setCapability("platformVersion", "7.1");
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait = new WebDriverWait(driver, 60);
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

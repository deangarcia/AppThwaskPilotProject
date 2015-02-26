package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.CapabilityType;
import testCases.MVPD;

/*
@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "json:target/cucumber.json"},
		features = {"src/cucumber/"},
		tags = {"MVPD, AccountLogin"}
		)
		*/

@SuppressWarnings("unused")
public abstract class IOSLaunch 
{
	public static AppiumDriver driver;
	public static WebDriverWait wait;
	public static MVPD mvpd = new MVPD();
	
	@BeforeClass
	public static void launchDriver() throws Throwable 
	{
		Properties prop = getPropertiesFile("mvpds");
		for(String key : prop.stringPropertyNames()) 
		{
			mvpd = new MVPD(key, prop.getProperty(key));
		}
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Raj Bangaru Samy’s iPhone");
		capabilities.setCapability("platformName", "ios"); 
		capabilities.setCapability("platformVersion", "7.1");
		//iphone4s
		//capabilities.setCapability("platformVersion", "7.0.3");
		//capabilities.setCapability("udid", "aa18c45a49bcea5055be2e894748ccb25665e1c6");
		//iphone5
		capabilities.setCapability("udid", "ce7d4a568c96f9886ed561d6aad36007e13fe0ff");
		capabilities.setCapability("bundleId", "com.uie.foxsports.foxsportsgo");
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wait = new WebDriverWait(driver, 60);
	}
	
	public static Properties getPropertiesFile(String propName)
	{
		try(FileReader reader = new FileReader("resources/" + propName + ".properties"))
		{
			Properties properties = new Properties();
			properties.load(reader);
			return properties;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	 
		/*
		   Properties properties = getProp("capabilities");
		   String test = properties.getProperty("deviceName");
	 	   System.out.println(test);
	 	 */
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
}

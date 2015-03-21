package testCases;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import driver.IOSLaunch;




public class linearTestCase extends IOSLaunch {
	long totalTime;
	boolean loginTest;

	// Before every test case all lines of code in the method annotated with
	// Before
	// Will be executed. This is different from BeforeClass because before class
	// happens
	// Once at the very start of our entire jUnit test and the Before method
	// will get executed
	// Before every method annotated with Test.
	@Before
	public void init() {
	}

	// After every test case all lines of code in the method annotated with
	// After
	// Will be executed. This is different from AfterClass because AfterClass
	// method happens
	// Once at the very end of our entire jUnit test and the After method will
	// get executed
	// After every method annotated with Test
	@After
	public void endit() {
	}

	@Test
	public void TestScenario() {

		// For loop does the same process for every MVPD this process is to go
		// through and
		// Play the videos on now for current MVPD and account
		for (MVPD mvpds : mvpd) {
			ArrayList<Accounts> acc = new ArrayList<Accounts>();
			acc = mvpds.getAccount();
			for (Accounts accounts : acc) {
				long startTime = System.currentTimeMillis();
				AAA.settings();
				loginTest = AAA.login(mvpds.getName(), accounts.getUsername(),
						accounts.getPassword());
				if (loginTest) {

					// Start of test without searching for element and location
					driver.manage().timeouts()
							.implicitlyWait(60, TimeUnit.SECONDS);
					List<WebElement> elements;
					elements = driver.findElementsByName("ON NOW");
					while (elements.size() == 0)
						elements = driver.findElementsByName("ON NOW");
					driver.manage().timeouts()
							.implicitlyWait(0, TimeUnit.SECONDS);

					int count = (elements.size()/2);

					for (int k = 0; k < count; k++) {
						// notOnNow for element location search
						try {
							AAA.playVideo(k);
						} catch (NoSuchElementException e) {
							e.printStackTrace();
							break;
						} catch (TimeoutException t) {
							t.printStackTrace();
							break;
						}

						// wait for the video to load
						driver.manage().timeouts()
								.implicitlyWait(30, TimeUnit.SECONDS);
						WebElement ele = driver
								.findElement(By
										.xpath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"));
						driver.manage().timeouts()
								.implicitlyWait(0, TimeUnit.SECONDS);
						PlayVideo.reverseGenieEffect(ele);
						try {
							AAA.pauseVideo("btn play");
						} catch (TimeoutException e) {
							e.printStackTrace();
							driver.resetApp();
							break;
						}

						try {
							PlayVideo.takeScreenShots(ele, mvpds.getName(),
									("Chip" + count));
						} catch (InterruptedException e) {
							e.printStackTrace();
							driver.resetApp();
							break;
						}
						PlayVideo.reverseGenieEffect(ele);
						try {
							AAA.backbutton("iPhone video backarrow");
						} catch (TimeoutException e) {
							e.printStackTrace();
							driver.resetApp();
							break;
						}
					}
					// End of test without searching for elements first

					AAA.logout();
				}
				long endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				System.out
						.println("Time for provider "
								+ mvpds.getName()
								+ " "
								+ ((int) Math.ceil((totalTime / 1000) / 60) + " Minutes"));
			}
		}
		// System.out.println(i + " MVPD");
	}

	public static void takeScreenShots() {
		// Get the screenshot

		driver = (IOSDriver) new Augmenter().augment(driver);
		try {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			File calsspathRoot = new File(System.getProperty("user.dir"));
			// workspace space is set in application folder
			File testScreenShot = new File(calsspathRoot + "/errors", "error"); // Copy
																				// the
																				// file
																				// to
																				// //
																				// screenshot
																				// folder
			FileUtils.copyFile(scrFile, testScreenShot);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


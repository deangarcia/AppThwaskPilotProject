package testCases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.IOSLaunch;

public class AAA extends IOSLaunch {

	public static void tap(WebElement element) {
		driver.tap(1, element, 1);
	}

	public static WebElement waitForElement_xpath(String xpath) throws TimeoutException {
		WebElement element = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(xpath)));

		return element;
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static WebElement waitForElement_name(String name) throws TimeoutException {
		WebElement element = wait.until(ExpectedConditions
				.elementToBeClickable(By.name(name)));
		return element;
	}
	
	public static void settings() {
		// settings
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]"));
		// sign in
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
	
		// implicit wait for 10 seconds driver.swip will wait 10 seconds before 
		// executing the swipe this ensure that the page is loaded and the 
		// swipe occurs at the right time
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElementByName("Sign in with your TV provider to watch now.");
		// Swipe to get more providers
		driver.swipe(290, 420, 290, 250, 3000);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]"));
	}

	public static boolean login(String prov, String username, String password) {
		WebElement e = driver.findElementByName(prov);

		// Scroll to works when invisible elements are present in the DOM
		// We know that elements are present on the DOM if they have a y coordinate 
		// Location, 
		driver.scrollTo(prov);
		
		/*
		while (y > 420) {
			// Swipe to the location of the MVPD
			driver.swipe(290, 420, 290, 0, 3000);
			y = y - 420;
		}
		*/
		tap(e);

		// xpaths are static right now and it works for these two providers but
		// it does need to be dynamic because they are not all the same
		// username
		WebElement element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]");
		if (!(element.getAttribute("value").equals(username))) {
			click(element);
			element.sendKeys(username);
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]"));
		}

		// password
		//element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]");
		element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]");
		click(element);
		element.sendKeys(password);

		// done
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]"));

		// click Sign-In
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]"));
		
		// possible sleep here bc elements are crashing
		return true;
	}
/*
// code for element location search
	public static boolean playVideo(String xpath) throws InterruptedException {
		List<WebElement> elements;
		elements = driver.findElementsByXPath(xpath);
		System.out.println("first find");
		System.out.println(elements.size() + " initial element size");
		while (elements.size() == 0) {
			System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
			elements = driver.findElementsByXPath(xpath);
			System.out.println(elements.size() + " element size");
		}
		List<String> cell_names = new ArrayList<String>();

        for (WebElement cell : elements) {
            cell_names.add(cell.getAttribute("name"));
            System.out.println(cell.getAttribute("name"));
        }
		// gives the div exRet UIAStatcText
		// System.out.println(elements.get(elements.size() - 1).getTagName());
		// System.out.println(elements.get(elements.size() -
		// 1).getAttribute("name"));
		// can use to see if WebElement or Native
		// System.out.println(elements.get(elements.size() - 1).getClass());
		// dimension could use for swipe left and right
		// System.out.println(elements.get(elements.size() - 1).getSize());
		// System.out.println(elements.get(elements.size() - 1).getText());
		// useless so far System.out.println(elements.get(elements.size() -
		// 1).isEnabled());
		// System.out.println(elements.get(elements.size() - 1).isDisplayed());
        
		//System.out.println(elements.get(elements.size() - 1).toString());
		
        int y = elements.get(elements.size() - 1).getLocation().getY();
		System.out.println(y + " y value");
		System.out.println("location find");

		while (!(elements.get(elements.size() - 1).isDisplayed())) {
			if (y > 420) {
				if ((y - 420) < 100) {
					driver.swipe(290, 420, 290, 100, 3000);
					y = y - 320;
				} else {
					driver.swipe(290, 420, 290, 0, 3000);
					y = y - 420;
				}
			} else {
				driver.swipe(290, 420, 290, y, 3000);
				elements.add(elements.size() - 1, driver.findElement(By.name(elements.get(elements.size() - 1).getAttribute(
						"name"))));
			}
			Thread.sleep(1000);
			System.out.println(!(elements.get(elements.size() - 1).isDisplayed()));
		}
	

		System.out.println("location find end");
		System.out.println("tap");
		System.out.println(elements.get(elements.size() - 1).getAttribute(
				"name"));
		tap(waitForElement_name(elements.get(elements.size() - 1).getAttribute(
				"name")));
		System.out.println("tap end");

		// if true is returned here that means that the video we tried to click
		// is not on now
		System.out.println("is vid displayed");
		if ((elements.get(elements.size() - 1).isDisplayed())) {
			System.out.println("unplayable video");
			return true;
		}
		System.out.println("is vid displayed end");

		return false;
	}
*/
	
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
	
	public static void backbutton(String name) throws TimeoutException {
		tap(waitForElement_name(name));
	}

	public static void logout() {
		// settings
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]"));
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
		tap(waitForElement_name("OK"));
	}
	public static void pauseVideo(String name) throws TimeoutException {
				tap(waitForElement_name(name));
	}

}

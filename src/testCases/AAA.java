package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.IOSLaunch;

public class AAA extends IOSLaunch {

	private static void tap(WebElement element) {
		driver.tap(1, element, 1);

	}

	private static WebElement waitForElement_xpath(String xpath) {

		WebElement element = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(xpath)));

		return element;

	}

	private static void click(WebElement element) {
		element.click();
	}

	private static WebElement waitForElement_name(String name) {
		WebElement element = wait.until(ExpectedConditions
				.elementToBeClickable(By.name(name)));
		return element;
	}

	public static void settings() {
		// settings
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]"));
		// sign in
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// WebElement e =
		// waitForElement_free("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]");

		driver.swipe(290, 420, 290, 250, 3000);
		// more Providers
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]"));
	}

	public static boolean login(MVPD prov) {
		WebElement e = driver.findElementByName(prov.getName());
		int y = e.getLocation().getY();
		while (y > 420) {
			driver.swipe(290, 420, 290, 0, 3000);
			e = driver.findElementByName(prov.getName());
			y = y - 420;
		}
		tap(e);

		// xpaths are static right now and it works for these two providers but
		// it does need to be dynamic because they are not all the same
		// username
		WebElement element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]");
		click(element);
		if (element.getAttribute("value").equals(
				prov.getAccount().getUsername())) {
		} else
			element.sendKeys(prov.getAccount().getUsername());
		
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]"));

		// password
		element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIASecureTextField[1]");
		click(element);
		element.sendKeys(prov.getAccount().getPassword());

		// done
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]"));

		// click Sign-In
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]"));
		return true;
	}
	

	public static void playVideo(String xpath) throws InterruptedException {
		// that 1 in the UIACOLLECTION cell are any index value can be put in a
		// for loop as wel and added in as a variable we can use substring to
		// get xpath minice the last 4
		List<WebElement> elements;
		elements = driver.findElementsByXPath(xpath);
		while (elements.size() == 0) {
			elements = driver.findElementsByXPath(xpath);
			System.out.println(elements.size());
		}
		
		int y = elements.get(elements.size() - 1).getLocation().getY();
		while (elements.get(elements.size() - 1).getLocation().getY() == 0) {
			elements = driver.findElementsByXPath(xpath);
			y = elements.get(elements.size() - 1).getLocation().getY();
			driver.swipe(290, 420, 290, y, 3000);
		}
		
		tap(waitForElement_xpath(xpath + "[" + elements.size() + "]"));
	}

	public static void pauseVideo(String name) throws InterruptedException {
		tap(waitForElement_name(name));
	}

	public static void backbutton(String name) {
		tap(waitForElement_name(name));
	}

	public static void logout() {
		// settings
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]"));
		tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
		tap(waitForElement_name("OK"));
	}
}

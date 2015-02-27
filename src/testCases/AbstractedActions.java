package testCases;

import driver.IOSLaunch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AbstractedActions  extends IOSLaunch {
	
		private void tap(WebElement element) {
			driver.tap(1, element, 1);
			
		}
		
		private WebElement waitForElement_xpath(String xpath) {
			
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By
							.xpath(xpath)));
			
			return element;
			
		}
		
		private void click(WebElement element) {
			element.click();
		}
		
		private WebElement waitForElement_name(String name) {
			
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By
							.name(name)));
			return element;
		}
		
		
		
		public void settings() {
			//settings
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]"));
			//sign in
			tap (waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
			//more providers
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]"));
			//at&t
			tap(waitForElement_name("AT&T U-verse"));
			
			
		}
		
		public boolean login() {
			
			//username
			WebElement element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]");
			click(element);
			element.sendKeys(mvpd.getAccount().getUsername());
			
			//password
			element = waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]");
			click(element);
			element.sendKeys(mvpd.getAccount().getPassword());
			
			//done
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]"));
			
			//click Sign-In	
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]"));
			return true;
		}
		
		
		public void playVideo(String xpath) {
			tap(waitForElement_xpath(xpath));
			
		}

		public void pauseVideo(String name) {
			tap(waitForElement_name(name));
		}
		
		public void backbutton(String name){
			tap(waitForElement_name(name));
		}
		
		public void logout(){
			//settings
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]"));
			tap(waitForElement_xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
		}
		
}

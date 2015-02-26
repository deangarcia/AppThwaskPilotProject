package testCases;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.IOSLaunch;

public class linearTestCase extends IOSLaunch
{
	long totalTime;
	@Before
	public void init()
	{
		System.out.println("doing this before");
	}
	@Before
	public void endit()
	{
		System.out.println("doing this After");
	}
	@Test
	public void doStuff() throws InterruptedException
	{

		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]")));
		driver.tap(1, element, 1);

		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")));
		driver.tap(1, element, 1);

		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]")));
		driver.tap(1, element, 1);

		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.name("AT&T U-verse")));
		driver.tap(1, element, 1);
		
		//username
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]")));
		element.click();
		element.sendKeys(mvpd.getAccount().getUsername());
		//password
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[2]")));
		element.click();
		element.sendKeys(mvpd.getAccount().getPassword());
		//put scroll here 
		
		//put scroll here
		
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]")));
		driver.tap(1, element, 1);
		//sign in 
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[1]")));
		driver.tap(1, element, 1);
		
		//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[3]

		/*
		long startTime = System.currentTimeMillis();
		
		long endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		driver.tap(1, element, 1);
		*/
		Thread.sleep(20000);
	}

}

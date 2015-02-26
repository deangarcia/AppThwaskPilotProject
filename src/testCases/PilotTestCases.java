package testCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import driver.IOSLaunch;

public class PilotTestCases extends IOSLaunch
{	 
	long totalTime;
	String e = "";
	WebElement element;
	static int counter = 0;
	
	@Before
	public void setUp() throws Exception 
	{
		if(counter == 0)
		{
			//settings xpath
			e = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]";
			++counter;
		}
		else if(counter == 1)
		{
			//signIn option xpath
			e = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
			++counter;
		}
		else if(counter == 2)
		{
			//select more providers xpath
			e = "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]";
			++counter;
		}
		else if(counter == 3)
		{
			//AT&T xpath
			e = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]​";
			++counter;
		}
		else if(counter == 4)
		{
			//usernamefield
			e = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIATextField[1]";
			++counter;
		}
		else if(counter == 5)
		{
			//password field
			e = "​";
			++counter;
		}
		else if(counter == 6)
		{
			//clickLogin
			e = "​";
			++counter;
		}
		else if(counter == 7)
		{
			//selectVideo
			e = "​";
			++counter;
		}
	}
	@After
	public void action() throws Exception 
	{
		// since before runs first we have to make sure the numbers in the after 
		//method are always one step ahead
		long startTime = 0;
		if(counter == 5)
		{
			element.sendKeys(mvpd.getAccount().getUsername());
		}
		else if(counter == 6)
		{
			element.sendKeys(mvpd.getAccount().getPassword());
		}
		else if(counter == 7)
		{
			startTime = System.currentTimeMillis();
			driver.tap(1, element, 1);
		}
		else if(counter == 8)
		{
			long endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			driver.tap(1, element, 1);
		}
		else
		{
			driver.tap(1, element, 1);
			Thread.sleep(20000);
		}
	}
	//1
	@Test
	public void selectSettings()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	//2
	@Test
	public void selecttvProviderSignIn()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	//3
	@Test
	public void selectMoreProviders()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	//4
	@Test
	public void selectAtt()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	/*
	//5
	@Test
	public void selectUserName()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	//6
	@Test
	public void selectPassword()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	//7
	@Test
	public void selectLogin()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	//8
	@Test
	public void selectVideo()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
							.xpath(e)));
	}
	*/
}

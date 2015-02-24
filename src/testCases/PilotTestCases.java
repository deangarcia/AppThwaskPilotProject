package testCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.PendingException;
import driver.IOSLaunch;

public class PilotTestCases extends IOSLaunch
{	 
	String e = "";
	WebElement element;
	static int counter = 0;
	@Before
	public void setUp() throws Exception 
	{
		
		if(counter == 0)
		{
			e = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[4]";
			++counter;
		}
		else if(counter == 1)
		{
			System.out.println(counter);
			e = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]";
			++counter;
		}
		else if(counter == 2)
		{
			e = "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIAButton[2]";
		}
	}
	@After
	public void tap() throws Exception 
	{
		driver.tap(1, element, 1);
	}
	@Test
	public void selectSettings()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	@Test
	public void selecttvProviderSignIn()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	@Test
	public void selectMoreProviders()
	{
		element = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(e)));
	}
	/*
	@Test
	public void tvProviderSignIn() 
	{
		WebElement signIn = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")));
		driver.tap(1, signIn, 1);
	}
	@Test
	public void getMoreProviders()
	{
		// Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	*/
}

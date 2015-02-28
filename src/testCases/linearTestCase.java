package testCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import driver.IOSLaunch;

public class linearTestCase extends IOSLaunch
{
	long totalTime;
	AbstractedActions aa = new AbstractedActions();
	boolean loginTest;
	@Before
	public void init()
	{
		aa.settings();
	}
	@After
	public void endit()
	{
		if(loginTest)
			aa.logout();
		
	}
	@Test
	public void TestScenario() throws InterruptedException
	{
		loginTest=aa.login();
		System.out.println(loginTest);
		if(loginTest){
			aa.playVideo("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[2]/UIACollectionCell[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAStaticText[3]");
			aa.pauseVideo("btn play");
			aa.backbutton("iPhone video backarrow");
		}
			
	}
}

package testCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.IOSLaunch;

public class linearTestCase extends IOSLaunch {
	long totalTime;
	boolean loginTest;

	@Before
	public void init() {
		System.out.println("start");
	}

	@After
	public void endit() {
		if (loginTest)
			// AbstractedActions.logout();
			System.out.println("done");
	}

	@Test
	public void TestScenario() throws InterruptedException {
		for (int i = 0; i < mvpd.size(); i++) {
			long startTime = System.currentTimeMillis();
			AAA.settings();
			loginTest = AAA.login(mvpd.get(i));
			if (loginTest) {
				PlayVideo.playVid(mvpd.get(i).getName());
				AAA.logout();
			}
			// System.out.println(i + " MVPD");
			long endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Time for provider " + mvpd.get(i).getName() + " " + ((int) Math.ceil((totalTime / 1000) / 60) + " Minutes"));
		}
	}
}

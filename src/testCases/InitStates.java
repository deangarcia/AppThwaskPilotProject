package testCases;

import java.util.ArrayList;

import org.junit.Test;

import driver.Driver;

public class InitStates extends Driver {
	long totalTime;
	boolean loginFlag = true;


	@Test
	public void TestScenario() throws InterruptedException {
		start.baby("dsfs");
		for (MVPD mvpds : mvpd) {
			System.out.println("Name : " + mvpds.getName());
			start.baby(mvpds.getName());
			//accountLooper(mvpds.getAccount(), mvpds.getName());
		}
	}

	private void accountLooper(ArrayList<Accounts> accounts, String accountName)
			throws InterruptedException {

		for (Accounts account : accounts) {
			if (loginFlag)
				start.loginAccountSettings(accountName);
			loginFlag = start.login(account);
			System.out.println(account.getUsername() + " Login: " + loginFlag);
			if (loginFlag) {
				start.playVideos();
				start.logout();
			}

		}

	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginAccountSettings(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login(Accounts a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void playVideos() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void baby(String s) {
		System.out.println("This baby");
		
	}
}
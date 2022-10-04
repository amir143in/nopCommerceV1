package com.nopcommerce.testCases;

import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;

public class TC_LogoutTest_Generic extends BaseClass {

	@Test(description = "This test validates logout functionality")
	public void logoutTest() {

	
		LoginPage lp = new LoginPage(driver);
		lp.clickLogout();
		logger.info("Logout Successfully");
		
	}
	
	
}

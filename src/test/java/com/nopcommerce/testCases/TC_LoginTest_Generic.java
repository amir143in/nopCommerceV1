package com.nopcommerce.testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;

public class TC_LoginTest_Generic extends BaseClass {

	@Test(description = "This test validates login functionality")
	public void loginTest() throws InterruptedException, IOException {

		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.setUserName(username);
		Thread.sleep(3000);
		logger.info("entered username");
		lp.setPassword(password);
		Thread.sleep(3000);
		logger.info("entered password");
		lp.clickLogin();

		System.out.println("The title of the page is : " + driver.getTitle());

		if (driver.getTitle().equalsIgnoreCase("Dashboard / nopCommerce administration")) {
			Thread.sleep(3000);
			System.out.println("Thread ID:" + Thread.currentThread().getId());
			Assert.assertTrue(true);
			logger.info("Test case is passed!!!");

		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Something went wrong.");
		}
		
	}

}

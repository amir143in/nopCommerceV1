package com.nopcommerce.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws IOException, InterruptedException {

		// Login is done
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("The username is entered");
		lp.setPassword(password);
		logger.info("The password is entered");
		lp.clickLogin();
		logger.info("Login button is clicked");

		// Add new customer
		logger.info("Providing customer details........!!!!!!!!!");
		AddcustomerPage addcust = new AddcustomerPage(driver);
		addcust.clickOnCustomerMenu();
		addcust.clickOnCustomersMenuItem();

		addcust.clickOnAddNew();

		String email = randomestring() + "@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("Test12234");
		addcust.setFirstName("Nikhil");
		addcust.setLastName("Khanna");
		addcust.setGender("male");
		addcust.setDob("10/15/2000");
		addcust.setCompanyName("ABC LTD");
		addcust.setTaxExempt("Yes");
		Thread.sleep(3000);
		addcust.setNewsLetter("Test store 2");
		Thread.sleep(3000);
		// addcust.setNewsLetter("Your store name");
		// Thread.sleep(3000);
		// addcust.deleteSelectedCustomerRole();
		// addcust.setCustomerRole("Guests");
		addcust.setCustomerRole("Vendors");
		Thread.sleep(3000);
		addcust.setManagerOfVendor("Vendor 1");
		Thread.sleep(3000);
		addcust.setActive("Yes");
		Thread.sleep(3000);
		addcust.setAdminComment("This is a new employee");

		addcust.clickOnSaveButton();

		logger.info("Validation started...........!");

		String msg = driver.findElement(By.tagName("Body")).getText();
		//System.out.println("The confirmation message is : " + msg);
		
		if (msg.contains("The new customer has been added successfully.")) {
			Assert.assertTrue(true);
			logger.info("The Test case passed....!!!!");
		} else {
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue("New Customer didnt get created", false);
		}

	}

}

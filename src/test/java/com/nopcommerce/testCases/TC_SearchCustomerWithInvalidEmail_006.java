package com.nopcommerce.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage_2;
import com.nopcommerce.pageObjects.CustomerEnquiry;
import com.nopcommerce.pageObjects.LoginPage;

public class TC_SearchCustomerWithInvalidEmail_006 extends BaseClass {

	@Test
	public void searchCustomer() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("The username is entered");
		lp.setPassword(password);
		logger.info("The password is entered");
		lp.clickLogin();
		logger.info("Login button is clicked");

		// Add new customer
		logger.info("Providing customer details........!!!!!!!!!");
		AddcustomerPage_2 addcust = new AddcustomerPage_2(driver);
		addcust.clickOnCustomerMenu();
		addcust.clickOnCustomersMenuItem();

		CustomerEnquiry custEnq = new CustomerEnquiry(driver);
		custEnq.setEmailValue("brenda_lindgren@nopComme1rce.com");
		Thread.sleep(3000);
		custEnq.clickButtonSearch();
		Thread.sleep(3000);

		Select select = new Select(driver.findElement(By.name("customers-grid_length")));
		select.selectByVisibleText("50");

		String displayMessage = driver.findElement(By.id("customers-grid_info")).getText();
		System.out.println("The value returned after search is : " + displayMessage);
		if(displayMessage.equalsIgnoreCase("No records")) {
			Assert.assertTrue(true);
			logger.info("There is no customer available with this email id");
		} else
			Assert.assertFalse(true);
		logger.info("This is a valid customer.........");
		
		driver.quit();
		// return flag;

	}
}

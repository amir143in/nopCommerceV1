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

public class TC_SearchCustomer_005 extends BaseClass {

	@Test
	public void searchCustomer() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("The username is entered");
		lp.setPassword(password);
		logger.info("The password is entered");
		lp.clickLogin();
		logger.info("Login button is clicked");

		// Click on customer menu
		logger.info("Providing customer details........!!!!!!!!!");
		AddcustomerPage_2 addcust = new AddcustomerPage_2(driver);
		addcust.clickOnCustomerMenu();
		addcust.clickOnCustomersMenuItem();

		CustomerEnquiry custEnq = new CustomerEnquiry(driver);
		custEnq.setEmailValue("admin@yourStore.com");
		Thread.sleep(3000);
		custEnq.clickButtonSearch();
		Thread.sleep(3000);

		// Dropdown values
		Select select = new Select(driver.findElement(By.name("customers-grid_length")));
		select.selectByVisibleText("50");

		// Count how many data are displayed
		String displayMessage = driver.findElement(By.id("customers-grid_info")).getText();
		// Splitting the message to get the total count
		String displayCount = displayMessage.split(" ")[2];

		System.out.println("Total number of displayed names counts :" + displayCount);
		// Identifyin the Next button
		WebElement nextButton = driver.findElement(By.id("customers-grid_next"));
		String nextButtonClass = nextButton.getAttribute("class");

		boolean flag = false;
		// adding do while loop in case all the data is displayed in the first page only.
		aa: do {
			int entriesCount = driver.findElements(By.xpath("//*[@id='customers-grid']/tbody/tr")).size();
			// Reading page wise
			for (int i = 1; i <= entriesCount; i++) {

				// Find email
				String nameValue = driver.findElement(By.xpath("//*[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
						.getText();
				if (nameValue.equalsIgnoreCase("admin@yourStore.com")) {

					flag = true;

					System.out.println("Test case is passed...!!");
					break aa;
				}

			}
			Thread.sleep(2000);
			logger.info("Before clicking the next button");
			nextButton.click();
			logger.info("After clicking the next button");
			Thread.sleep(2000);
			nextButton = driver.findElement(By.id("customers-grid_next"));
			nextButtonClass = nextButton.getAttribute("class");
		} while (!nextButtonClass.contains("disabled"));

		driver.quit();
		// return flag;

	}
}

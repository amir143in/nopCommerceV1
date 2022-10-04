package com.nopcommerce.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage_2;
import com.nopcommerce.pageObjects.CustomerEnquiry;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.utilities.XLUtils;

public class TC_SearchCustomerWithInvalidEmailDDT_007 extends BaseClass {

	@Test(dataProvider = "AddNewCustomerData2")
	public void searchCustomer(String UserEmail, String UserPassword, String CustomerEmail)
			throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(UserEmail);
		logger.info("The username is entered");
		lp.setPassword(UserPassword);
		logger.info("The password is entered");
		lp.clickLogin();
		logger.info("Login button is clicked");

		// Add new customer
		logger.info("Providing customer details........!!!!!!!!!");
		AddcustomerPage_2 addcust = new AddcustomerPage_2(driver);
		addcust.clickOnCustomerMenu();
		addcust.clickOnCustomersMenuItem();

		CustomerEnquiry custEnq = new CustomerEnquiry(driver);
		custEnq.setEmailValue(CustomerEmail);
		Thread.sleep(3000);
		custEnq.clickButtonSearch();
		Thread.sleep(3000);

		Select select = new Select(driver.findElement(By.name("customers-grid_length")));
		select.selectByVisibleText("50");

		String displayMessage = driver.findElement(By.id("customers-grid_info")).getText();
		System.out.println("The value returned after search is : " + displayMessage);
		if (displayMessage.equalsIgnoreCase("No records")) {
			Assert.assertTrue(true);
			logger.info("There is no customer available with this email id");
		} else
			Assert.assertFalse(true);
		logger.info("This is a valid customer.........");

		driver.quit();
		// return flag;

	}

	@DataProvider(name = "AddNewCustomerData2")
	String[][] getData() throws IOException {
		// String path = System.getProperty("user.dir") +
		// "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		String path = "C:/Users/AAnsari/eclipse-workspace2022/nopCommerceV1/src/test/java/com/nopcommerce/testData/AddCustomerNew.xlsx";
		// String path = logindataPath;
		int rownum = XLUtils.getRowCount(path, "Hoja2");
		System.out.println("The number of rows count : " + rownum);
		int colcount = XLUtils.getCellCount(path, "Hoja2", 1);
		System.out.println("The number of cell count : " + colcount);

		String logindata[][] = new String[rownum][colcount];
		System.out.println(logindata);

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Hoja2", i, j);// 1 0
			}

		}
		return logindata;
	}
}

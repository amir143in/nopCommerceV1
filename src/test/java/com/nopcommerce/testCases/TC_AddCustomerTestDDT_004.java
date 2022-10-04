package com.nopcommerce.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.utilities.XLUtils;

public class TC_AddCustomerTestDDT_004 extends BaseClass {

	@Test(dataProvider = "AddNewCustomerData")
	public void addNewCustomer(String uLoginEmail, String uLoginPassword, String uEmail, String uPass,
			String uFirstName, String uLastName, String uGender, String uDateOfBirth, String uCompanyName,
			String uTaxExempt, String uNewsLetter, String uCustomerRole, String uManagerVendor, String uSetActive,
			String uAdminComments) throws IOException, InterruptedException {

		// Login is done
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uLoginEmail);
		logger.info("The username is entered");
		lp.setPassword(uLoginPassword);
		logger.info("The password is entered");
		lp.clickLogin();
		logger.info("Login button is clicked");

		// Add new customer
		logger.info("Providing customer details........!!!!!!!!!");
		AddcustomerPage addcust = new AddcustomerPage(driver);
		addcust.clickOnCustomerMenu();
		addcust.clickOnCustomersMenuItem();

		addcust.clickOnAddNew();

		// String email = randomestring() + "@gmail.com";
		addcust.setEmail(uEmail);
		addcust.setPassword(uPass);
		addcust.setFirstName(uFirstName);
		addcust.setLastName(uLastName);
		addcust.setGender(uGender);
		addcust.setDob(uDateOfBirth);
		addcust.setCompanyName(uCompanyName);
		addcust.setTaxExempt(uTaxExempt);
		Thread.sleep(3000);
		addcust.setNewsLetter(uNewsLetter);
		Thread.sleep(3000);
		// addcust.setNewsLetter("Your store name");
		// Thread.sleep(3000);
		// addcust.deleteSelectedCustomerRole();
		// addcust.setCustomerRole("Guests");
		addcust.setCustomerRole(uCustomerRole);
		Thread.sleep(3000);
		addcust.setManagerOfVendor(uManagerVendor);
		Thread.sleep(3000);
		addcust.setActive(uSetActive);
		addcust.setAdminComment(uAdminComments);

		addcust.clickOnSaveButton();

		logger.info("Validation started...........!");

		String msg = driver.findElement(By.tagName("Body")).getText();
		// System.out.println("The confirmation message is : " + msg);

		if (msg.contains("The new customer has been added successfully.")) {
			Assert.assertTrue(true);
			logger.info("The Test case passed....!!!!");
		} else {
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue("New Customer didnt get created", false);
		}

	}

	@DataProvider(name = "AddNewCustomerData")
	String[][] getData() throws IOException {
		// String path = System.getProperty("user.dir") +
		// "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		String path = "C:/Users/AAnsari/eclipse-workspace2022/nopCommerceV1/src/test/java/com/nopcommerce/testData/AddCustomerNew.xlsx";
		// String path = logindataPath;
		int rownum = XLUtils.getRowCount(path, "Hoja1");
		System.out.println("The number of rows count : " + rownum);
		int colcount = XLUtils.getCellCount(path, "Hoja1", 1);
		System.out.println("The number of cell count : " + colcount);

		String logindata[][] = new String[rownum][colcount];
		System.out.println(logindata);

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Hoja1", i, j);// 1 0
			}

		}
		return logindata;
	}

}

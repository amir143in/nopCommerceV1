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

public class TC_EDITCustomerWithValidEmailDDT_009 extends BaseClass {

	@Test(dataProvider = "AddNewCustomerData2")
	public void searchCustomer(String UserEmail, String UserPassword, String CustomerEmail, String CustomerAdmComments)
			throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(UserEmail);
		logger.info("The username is entered");
		lp.setPassword(UserPassword);
		logger.info("The password is entered");
		lp.clickLogin();
		logger.info("Login button is clicked");

		// Add new customer details
		logger.info("Providing customer details........!!!!!!!!!");
		AddcustomerPage_2 addcust = new AddcustomerPage_2(driver);
		addcust.clickOnCustomerMenu();
		addcust.clickOnCustomersMenuItem();

		CustomerEnquiry custEnq = new CustomerEnquiry(driver);
		custEnq.setEmailValue(CustomerEmail);
		Thread.sleep(3000);
		custEnq.clickButtonSearch();
		Thread.sleep(3000);

		// Count how many data are displayed
		String displayMessage = driver.findElement(By.id("customers-grid_info")).getText();
		// Splitting the message to get the total count
		String displayCount = displayMessage.split(" ")[2];

		System.out.println("Total number of displayed names counts :" + displayCount);
		// Identifyin the Next button
		WebElement nextButton = driver.findElement(By.id("customers-grid_next"));
		String nextButtonClass = nextButton.getAttribute("class");

		boolean flag = false;
		// adding do while loop in case all the data is displayed in the first page
		// only.
		aa: do {
			int entriesCount = driver.findElements(By.xpath("//*[@id='customers-grid']/tbody/tr")).size();
			// Reaading page wise
			for (int i = 1; i <= entriesCount; i++) {

				// Find email
				String nameValue = driver.findElement(By.xpath("//*[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
						.getText();
				if (nameValue.equalsIgnoreCase(CustomerEmail)) {

					flag = true;
					// Click the edit button
					driver.findElement(By.xpath("//*[@id='customers-grid']/tbody/tr[" + i + "]/td[7]/a")).click();

					addcust.setAdminComment(CustomerAdmComments);
					addcust.clickOnSaveButton();

					String msg = driver.findElement(By.tagName("Body")).getText();
					// System.out.println("The confirmation message is : " + msg);

					if (msg.contains("The customer has been updated successfully.")) {
						Assert.assertTrue(true);
						logger.info("The Test case passed....!!!!");
					} else {
						captureScreen(driver, "AddNewCustomer");
						Assert.assertTrue(" ustomer didnt get modified", false);
					}

					break aa;
				}

			}
			Thread.sleep(2000);
			nextButton.click();
			Thread.sleep(2000);
			nextButton = driver.findElement(By.id("customers-grid_next"));
			nextButtonClass = nextButton.getAttribute("class");
		} while (!nextButtonClass.contains("disabled"));

		driver.quit();

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

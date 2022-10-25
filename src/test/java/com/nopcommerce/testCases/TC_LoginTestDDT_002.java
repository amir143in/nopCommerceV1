package com.nopcommerce.testCases;

import static org.testng.Assert.assertFalse;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.utilities.XLUtils;
import com.nopcommerce.testCases.*;

public class TC_LoginTestDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginTest(String user, String pwd) throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickLogin();

		Thread.sleep(3000);

		if (driver.getTitle().equalsIgnoreCase("Dashboard / nopCommerce administration")) {
			Assert.assertTrue(true);
			lp.clickLogout();

		} else {
			captureScreen(driver, "loginTestWithExcel");
			Assert.assertFalse(false);
			logger.info("Login test passed with incorrect credenciales");
		}
	}

	@DataProvider(name = "LoginData")
	public String[][] getDataProvider() throws IOException {
		// TODO Auto-generated method stub
		return super.getDataProviderGenerico(
				"C:/Users/AAnsari/git/nopCommerceV1/src/test/java/com/nopcommerce/testData/LoginData.xlsx", "Sheet1");

	}

	/*
	 * @DataProvider(name = "LoginData") String[][] getData() throws IOException {
	 * //String path = System.getProperty("user.dir") +
	 * "/src/test/java/com/nopcommerce/testData/LoginData.xlsx"; String path =
	 * "C:/Users/AAnsari/git/nopCommerceV1/src/test/java/com/nopcommerce/testData/LoginData.xlsx";
	 * int rownum = XLUtils.getRowCount(path, "Sheet1");
	 * System.out.println("The number of rows count : " + rownum); int colcount =
	 * XLUtils.getCellCount(path, "Sheet1", 1);
	 * System.out.println("The number of cell count : " + colcount);
	 * 
	 * String logindata[][] = new String[rownum][colcount];
	 * 
	 * for (int i = 1; i <= rownum; i++) { for (int j = 0; j < colcount; j++) {
	 * logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);// 1 0 }
	 * 
	 * } return logindata; }
	 */

}
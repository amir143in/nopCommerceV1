package com.nopcommerce.testCases;

import java.io.IOException;

import com.nopcommerce.utilities.XLUtils;

public class CommanMethod {

	
	
	//Dataprovodir

	 public String[][] getDataProvider(String path, String sheetName) throws IOException {
		// String path = System.getProperty("user.dir") +
		// "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		//String path = "C:/Users/AAnsari/eclipse-workspace2022/nopCommerceV1/src/test/java/com/nopcommerce/testData/AddCustomerNew.xlsx";
		// String path = logindataPath;
		int rownum = XLUtils.getRowCount(path, sheetName);
		System.out.println("The number of rows count : " + rownum);
		int colcount = XLUtils.getCellCount(path, sheetName, 1);
		System.out.println("The number of cell count : " + colcount);

		String logindata[][] = new String[rownum][colcount];
		System.out.println(logindata);

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, sheetName, i, j);// 1 0
			}

		}
		return logindata;
	}
}

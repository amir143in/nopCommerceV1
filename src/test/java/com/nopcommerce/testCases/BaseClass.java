package com.nopcommerce.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.utilities.ReadConfig;
import com.nopcommerce.utilities.XLUtils;


public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getApplicationURL();;
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public String logindataPath = readconfig.getLoginDataPath();
	public static WebDriver driver;
	public static Logger logger;

	
	//Passing the browser value from testng.xml files
	@Parameters("browser")
	@BeforeMethod
	public void setup(String br) {

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();

		} else if (br.equals("firefox")) {
			// System.setProperty("webdriver.gecko.driver", readconfig.getFireFoxPath());
			driver = new FirefoxDriver();

		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();

		}
		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// method to take the screen shot of the FAILED test cases
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public static String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return (generatedstring2);
	}
	
	public static void loginGeneric(String uLoginUser, String uLoginPassword) {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uLoginUser);
		logger.info("user name provided");
		lp.setPassword(uLoginPassword);
		logger.info("password provided");
		lp.clickLogin();
		
		
	}
	
	public static void logoutGeneric() {
		LoginPage lp = new LoginPage(driver);
		lp.clickLogout();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	
	//@DataProvider(name = "DataProviderGenerico")
	 public String[][] getDataProviderGenerico(String path, String sheetName) throws IOException {
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

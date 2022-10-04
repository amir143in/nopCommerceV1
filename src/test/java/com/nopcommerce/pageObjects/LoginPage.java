package com.nopcommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	// This driver is local driver and belongs to the class LoginPage
	WebDriver ldriver;

	// this rdriver we are getting from the remote o actual test class/method
	public LoginPage(WebDriver rdriver) {

		// Assigning the remote driver to the local driver.
		this.ldriver = rdriver;

		// initElements is a static method in Page Factory class. Using the initElements
		// method, one can initialize all the web elements located by @FindBy
		// annotation.
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name = "Email")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(name = "Password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(xpath = "//*[@type='submit']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	@CacheLookup
	WebElement lnkLogout;

	// For the above three elements lets create actions methods
	public void setUserName(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public void clickLogout() {
		lnkLogout.click();
	}
}

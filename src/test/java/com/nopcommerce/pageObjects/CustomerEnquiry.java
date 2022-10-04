package com.nopcommerce.pageObjects;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerEnquiry {
	
	public WebDriver ldriver;
	public JavascriptExecutor js = (JavascriptExecutor) ldriver;

	public CustomerEnquiry(WebDriver rdriver) {
		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		WebDriverWait wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
	}

	@FindBy(id = "SearchEmail")
	WebElement searchByEmail;
	
	@FindBy(id = "search-customers")
	WebElement btnSearch;
	
	@FindBy(xpath = "//*[@id='customers-grid']/tbody/tr")
	WebElement searchTableData;
	
	
	public void setEmailValue(String emailV) {
		searchByEmail.sendKeys(emailV);
	}
	
	public void clickButtonSearch() {
		btnSearch.click();
	}
	
	
}

package com.nopcommerce.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddcustomerPage_2 {

	public WebDriver ldriver;
	public JavascriptExecutor js = (JavascriptExecutor) ldriver;

	public AddcustomerPage_2(WebDriver rdriver) {
		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		WebDriverWait wait = new WebDriverWait(rdriver, Duration.ofSeconds(10));
	}

	// Now identify the elements
	@FindBy(xpath = "//a[@href='#']//*[contains(text(),'Customers')]")
	WebElement lnkCustomers_menu;

	@FindBy(xpath = "//a[@href='/Admin/Customer/List']/p")
	WebElement lnkCustomers_menuitem;

	@FindBy(xpath = "//a[@href='/Admin/Customer/Create']")
	WebElement btnAddnew;

	@FindBy(id = "Email")
	WebElement txtEmail;

	@FindBy(id = "Password")
	WebElement txtPassword;

	@FindBy(name = "FirstName")
	WebElement txtFirstName;

	@FindBy(name = "LastName")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='Gender_Male']")
	WebElement rdMaleGender;

	@FindBy(xpath = "//input[@id='Gender_Female']")
	WebElement rdFeMaleGender;

	@FindBy(xpath = "//input[@id='DateOfBirth']")
	WebElement txtDob;

	@FindBy(xpath = "//input[@id='Company']")
	WebElement txtCompanyName;

	@FindBy(xpath = "//input[@id='IsTaxExempt']")
	WebElement checkBoxTaxExempt;

	// @FindBy(id = "SelectedNewsletterSubscriptionStoreIds")
	@FindBy(xpath = "//input[@aria-labelledby='SelectedNewsletterSubscriptionStoreIds_label']")
	WebElement inputNewsLetterInitial;

	// This element is to clear the selected items.
	@FindBy(xpath = "//ul[@id='SelectedCustomerRoleIds_taglist']/li/span[2]")
	WebElement inputCustomerRoleDelete;

	@FindBy(xpath = "//input[@aria-labelledby='SelectedCustomerRoleIds_label']")
	WebElement selectCustomerRolesInitial;

	@FindBy(xpath = "//select[@id='VendorId']")
	WebElement selectVendors;

	@FindBy(xpath = "//input[@id='Active']")
	WebElement checkBoxActive;

	@FindBy(xpath = "//textarea[@id='AdminComment']")
	WebElement txtBoxAdminComment;

	@FindBy(name = "save")
	WebElement btnSave;

	@FindBy(name = "save-continue")
	WebElement btnSaveAndContinue;

	@FindBy(id = "customer-delete")
	WebElement btnDelete;
	
	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	WebElement btnConfirmDeleteAlert;
	
	// Now add methods for the above webelements
	public void clickOnCustomerMenu() {
		lnkCustomers_menu.click();
	}

	public void clickOnCustomersMenuItem() {
		lnkCustomers_menuitem.click();
	}

	public void clickOnAddNew() {
		btnAddnew.click();
	}

	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setGender(String gender) {
		if (gender.equalsIgnoreCase("Male")) {
			rdMaleGender.click();
		} else if (gender.equalsIgnoreCase("Female")) {
			rdFeMaleGender.click();
		} else {
			rdMaleGender.click();
		}
	}

	public void setDob(String dob) {
		txtDob.sendKeys(dob);
	}

	public void setCompanyName(String comname) {
		txtCompanyName.clear();
		txtCompanyName.sendKeys(comname);
	}

	public void setTaxExempt(String selectExempt) {

		if (selectExempt.equalsIgnoreCase("Yes")) {
			checkBoxTaxExempt.click();
		} else {
			System.out.println("Continue without selecting the Tax Exempt Check Box");
		}

	}

	public void setNewsLetter(String newsLetter) throws InterruptedException {
		inputNewsLetterInitial.click();
		Thread.sleep(1000);
		ldriver.findElement(
				By.xpath("//ul[@id='SelectedNewsletterSubscriptionStoreIds_listbox']/li[text()='" + newsLetter + "']"))
				.click();

	}


	public void setCustomerRole(String role) throws InterruptedException {

		inputCustomerRoleDelete.click();
		selectCustomerRolesInitial.click();

		Thread.sleep(1000);

		ldriver.findElement(By.xpath("//ul[@id='SelectedCustomerRoleIds_listbox']/li[text()='" + role + "']")).click();

	}

	public void setManagerOfVendor(String vendor) {
		Select selectVendor = new Select(selectVendors);
		if (selectVendor.getOptions().size() > 0) {
			selectVendor.selectByVisibleText(vendor);
		} else {
			System.out.println("The Vendor field is vacio..!!!");
		}
	}

	public void setActive(String active) {
		if (active.equalsIgnoreCase("Yes")) {
			checkBoxActive.click();
		} else {
			System.out.println("The active button is deselected..!!");
		}
	}

	public void setAdminComment(String comments) {
		txtBoxAdminComment.clear();
		txtBoxAdminComment.click();
		txtBoxAdminComment.sendKeys(comments);
	}

	public void clickOnSaveButton() {
		btnSave.click();
		// js.executeScript("arguments[0].click;", btnSave);
	}

	public void clickOnSaveAndContinueButton() {
		btnSaveAndContinue.click();
	}
	
	public void clickOnDeleteButton() {
		btnDelete.click();
	}
	
	public void clickOnConfrimDeleteAlert() {
		
		btnConfirmDeleteAlert.click();
	}
}

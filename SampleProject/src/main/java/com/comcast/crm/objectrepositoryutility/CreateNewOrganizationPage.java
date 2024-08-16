
package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	   public CreateNewOrganizationPage(WebDriver driver) {
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	   }
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDD;
	
	@FindBy(name = "accounttype")
	private WebElement typeDD;
	
	@FindBy(id = "phone")
	private WebElement phone;
	
	
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSvBtn() {
		return saveBtn;
	}
	
	
	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}
	
	

	public WebElement getPhone() {
		return phone;
	}

	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
		
	}
	
	public void createOrg(String orgName, WebDriverUtility wLib, String industry, String type) {
		orgNameEdt.sendKeys(orgName);
		wLib.select(industryDD, industry);		
		wLib.select(typeDD, type);		
		
		
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String phoneNumber) {
		orgNameEdt.sendKeys(orgName);
		phone.sendKeys(phoneNumber);
		saveBtn.click();
		
	}

	
}

	
	


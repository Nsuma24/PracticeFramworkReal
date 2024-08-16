package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	WebDriver driver;
	   public ContactInformationPage(WebDriver driver) {
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	   }
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	@FindBy (id = "mouseArea_Last Name")
	private WebElement headerLastNameMsg;
	
	@FindBy (id = "mouseArea_Organization Name")
	private WebElement headerOrgMsg;
	
	@FindBy (id = "dtlview_Support Start Date")
	private WebElement headerSupportStartDateMsg;
	
	@FindBy (id = "dtlview_Support End Date")
	private WebElement headerSupportEndDateMsg;
	
	public WebElement getHeaderLastNameMsg() {
		return headerLastNameMsg;
	}

	public WebElement getHeaderOrgMsg() {
		return headerOrgMsg;
	}

	public WebElement getHeaderSupportStartDateMsg() {
		return headerSupportStartDateMsg;
	}

	public WebElement getHeaderSupportEndDateMsg() {
		return headerSupportEndDateMsg;
	}
	
	

}

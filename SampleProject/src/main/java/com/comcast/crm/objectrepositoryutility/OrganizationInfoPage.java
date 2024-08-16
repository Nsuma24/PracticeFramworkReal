package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	   public OrganizationInfoPage(WebDriver driver) {
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	   }
	
	@FindBy(className= "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id= "dtlview_Industry")
	private WebElement headerMsgInd;
	
	@FindBy(id= "dtlview_Type")
	private WebElement headerMsgType;
	
	
	@FindBy(id = "dtlview_Phone")
	private WebElement headerMsgPhn;
	


	public WebElement getHeaderMsgInd() {
		return headerMsgInd;
	}


	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	


	public WebElement getHeaderMsgType() {
		return headerMsgType;
	}


	public WebElement getHeaderMsgPhn() {
		return headerMsgPhn;
	}
	
	
	

}

package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage {
	
	WebDriver driver;
	   public CreatingNewContactPage(WebDriver driver) {
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	   }
	   
	   @FindAll({@FindBy(name = "lastname"), @FindBy(xpath = "//input[@name='lastname']")})
	   private WebElement cntLastNameEdt;
	   
	   @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	   private WebElement saveBtn;
	   
	   @FindBy(xpath= "(//img[@alt='Select'])[1]")
	   private WebElement addOrg;
	   
	   @FindBy(name= "support_end_date")
	   private WebElement endDateEdt;
	   
	   @FindBy(name= "support_start_date")
	   private WebElement startDateEdt;
	   
	   @FindBy(id="search_txt")
		WebElement searchTF;
		
		@FindBy(name="search")
		WebElement searchBtn;
		
		@FindBy(name = "button")
		private WebElement buttonEle;
		
		

	public WebElement getEndDateEdt() {
			return endDateEdt;
		}

		public WebElement getStartDateEdt() {
			return startDateEdt;
		}

		public WebElement getSearchTF() {
			return searchTF;
		}

		public WebElement getSearchBtn() {
			return searchBtn;
		}

		public WebElement getButtonEle() {
			return buttonEle;
		}

	public WebElement getCntLNameEdt() {
		  return cntLastNameEdt;
	   }

	   public WebElement getSaveBtn() {
		  return saveBtn;
	  }
	   
	  public WebElement getAddOrg() {
			  return addOrg;
		   }
	  
	  
	   
	   public WebElement getEndDate() {
		return endDateEdt;
	}

	public WebElement getStartDate() {
		return startDateEdt;
	}

	public void createCont(String lastName) {
		   cntLastNameEdt.sendKeys(lastName);
			saveBtn.click();
	   }
	   
    public void createContactWithOrg(WebDriver driver, String lastName, WebDriverUtility wLib, String orgName) {
    	
    	cntLastNameEdt.sendKeys(lastName); // getting the data from test script (excel+random int)
    	addOrg.click(); 	
 
      // switch to child window/
 		wLib.switchToTabOnURL(driver, "module=Accounts");

 		searchTF.sendKeys(orgName);

 		searchBtn.click();
 		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
 		
 		// switch to parent window
 		wLib.switchToTabOnURL(driver, "Contacts&action");
 		
    	saveBtn.click();
    }
   
    
  public void  createContactWithSupportDate(String lastName, String startDate, String endDate) {
	  cntLastNameEdt.sendKeys(lastName);
	  
	  startDateEdt.clear();
	  startDateEdt.sendKeys(startDate);
	  endDateEdt.clear();
	  endDateEdt.sendKeys(endDate); 
	  saveBtn.click();
	  
  }
    

}

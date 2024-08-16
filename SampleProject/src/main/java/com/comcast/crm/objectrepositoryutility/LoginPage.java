package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author hp
 * Contains Login Page elements and business lib like login()
 * 
 */
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{   //Rule 1
	
	
	//  Rule 2
	
	WebDriver driver;
   public LoginPage(WebDriver driver) {
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	
	/**
	 * login into aplication based on username , password, url arguements
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	
   //Provide an action
	
	public void loginToApp(String url , String username, String password) {
		
		waitForPageLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginbtn.click();
	}
	
	
	

}

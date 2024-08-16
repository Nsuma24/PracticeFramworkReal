package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class Base {

	/*Create Object */
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
    public DataBaseUtility dbLib = new DataBaseUtility();
    public WebDriver driver ;
    public static WebDriver sdriver ; // if it is non static batch execution is not possible
   
    
			
	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("===Connect to DB , Report Config ====");
		dbLib.getConnection();
		
	}
	
	/**@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC() throws IOException {
		System.out.println("===Launch the browser===");	
		
		//String BROWSER =fLib.getDataFromPropertiesFile("browser");
		
		String BROWSER= System.getProperty("browser" , fLib.getDataFromPropertiesFile("browser"));
				
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if(BROWSER.equals("edge")) {
				driver= new EdgeDriver();
		} 
		else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setdriver(driver);
		
	}**/
	
	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC( String BROWSER) throws IOException {
		System.out.println("===Launch the browser===");	
		//String BROWSER = browser;
				//String BROWSER =System.getProperty("browser" ,fLib.getDataFromPropertiesFile("browser"));
				
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} 
		else if(BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		} 
		else if(BROWSER.equals("edge")) 
		{
				driver= new EdgeDriver();
		} 
		sdriver = driver;
		UtilityClassObject.setdriver(driver);
		
	}
	
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws IOException {
		System.out.println("Login");
		
	   // String URL = fLib.getDataFromPropertiesFile("url");
	   //String USERNAME = fLib.getDataFromPropertiesFile( "username");
	   //String PASSWORD = fLib.getDataFromPropertiesFile("password");
		String URL= System.getProperty("url" , fLib.getDataFromPropertiesFile("url"));
		String USERNAME= System.getProperty("username" , fLib.getDataFromPropertiesFile( "username"));
		String PASSWORD= System.getProperty("password" , fLib.getDataFromPropertiesFile("password"));
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();	
	}
	
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() {
		System.out.println("===Close the browser===");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() throws SQLException {
		System.out.println("===Close  DB , Report backup ====");
		dbLib.closeDbConnection();
		

	}

}
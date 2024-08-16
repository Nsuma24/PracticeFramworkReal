package com.comcast.crm.orgtest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.Base;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutility.ListenerImpClass.class)
public class CreateOrganizationTest extends Base {

	@Test(groups = "smokeTest")
	public void createOrg() throws EncryptedDocumentException, IOException {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		
		// read testscript data from excel
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step 2: naviagte to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3 : click on "create Organization" button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create Org Page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step 4 : enter all the details and create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new Org");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		
		 
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName +"====> Create a new Org");


		// verify Header msg Expected Result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		
		Assert.assertEquals(true, actOrgName.contains(orgName));

		/**if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified===PASS");
		} else {
			System.out.println(orgName + "name is not verified===FAIL");
		}**/

	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {
		// read testscript data from excel
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// step 2: naviagte to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3 : click on "create Organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step 4 : enter all the details and create new Organization

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, wLib, industry, type);

		// verify the industries and type info

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actIndustries = oip.getHeaderMsgInd().getText();

		if (actIndustries.equals(industry)) {
			System.out.println(industry + " information is verified==PASS");
		} else {
			System.out.println(industry + " information is not verified==FAIL");
		}

		// verify type
		String actType = oip.getHeaderMsgType().getText();

		// String actType = driver.findElement(By.id("dtlview_Type")).getText();

		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else {
			System.out.println(type + " information is not verified==FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNumber() throws EncryptedDocumentException, IOException {
		// read testscript data from excel
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3 : click on "create Organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step 4 : enter all the details and create new Organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, phoneNumber);

		// verify Header msg Expected Result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();

		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		// verify Header Phone number info Expected Result

		String actPhoneNumber = oip.getHeaderMsgPhn().getText();

		if (actPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is created==PASS");
		} else {
			System.out.println(phoneNumber + " is not created==FAIL");
		}

	}

}

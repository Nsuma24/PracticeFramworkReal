package com.comcast.crm.contacttest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.Base;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

/**
 * @author hp
 * 
 */

@Listeners(com.comcast.crm.listenerutility.ListenerImpClass.class)

public class CreateContactTest extends Base {

	@Test(groups = "smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");


		/* read testscript data from excel */
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		/* step 2: naviagte to Contact module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* Step 3 : click on "create contact" button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewCntctBtn().click();

		/* Step 4 : enter all the details and create new Contcat */
		CreatingNewContactPage cnc = new CreatingNewContactPage(driver);
		cnc.createCont(lastName);

		/* verify Header info Expected Result */
		ContactInformationPage cip = new ContactInformationPage(driver);
		String actHeader = cip.getHeaderMsg().getText();

		boolean status = actHeader.contains(lastName);

		Assert.assertEquals(status, true);

		String actLastName = cip.getHeaderLastNameMsg().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDate() throws InterruptedException, EncryptedDocumentException, IOException {
		// read testscript data from excel
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step 2: naviagte to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Step 3 : click on "create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewCntctBtn().click();

		// Step 4 : enter all the details and create new Contact

		CreatingNewContactPage cnc = new CreatingNewContactPage(driver);

		String startDate = jLib.getSystemDateyyyyMMdd();
		String endDate = jLib.getRequiredDateyyyyMMdd(30);

		Thread.sleep(2000);

		// Business logic
		cnc.createContactWithSupportDate(lastName, startDate, endDate);

		Thread.sleep(2000);

		// verify Start date
		ContactInformationPage cip = new ContactInformationPage(driver);
		String actStartDate = cip.getHeaderSupportStartDateMsg().getText();

		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + " information is verified ==PASS");
		} else {
			System.out.println(startDate + " information is not verified ==FAIL");
		}

		// verify end date

		String actEndDate = cip.getHeaderSupportEndDateMsg().getText();

		if (actEndDate.equals(endDate)) {
			System.out.println(endDate + " information is verified ==PASS");
		} else {
			System.out.println(endDate + " information is not verified ==FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {

		// read testscript data from excel
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// step 2: naviagte to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3 : click on "create Organization" button

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step 4 : enter all the details and create new Organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify Header msg Expected Result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();

		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "header is verified===PASS");
		} else {
			System.out.println(orgName + "header is not verified===FAIL");
		}

		// step 5: naviagte to Contact module

		hp.getContactLink().click();

		// Step 6 : click on "create contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewCntctBtn().click();

		// Step 7 : enter all the details and create new contact

		CreatingNewContactPage cnc = new CreatingNewContactPage(driver);

		Thread.sleep(2000);

		// business logic
		cnc.createContactWithOrg(driver, contactLastName, wLib, orgName);
		ContactInformationPage cip = new ContactInformationPage(driver);
		String headerLastNameMsg = cip.getHeaderLastNameMsg().getText();

		// String headerLastNameMsg = cip.getHeaderLastNameMsg().getText();

		Thread.sleep(2000);

		// String headerMsg= cip.getHeaderMsg().getText();

		if (headerLastNameMsg.contains(contactLastName)) {
			System.out.println(contactLastName + " header verified ==PASS");
		} else {
			System.out.println(contactLastName + " headeris not verified==FAIL");
		}

		// verify Header orgName ifo Expected result
		String actOrgName = cip.getHeaderOrgMsg().getText();

		// String actOrgName = cip.getHeaderOrgMsg().getTagName();

		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + " information is created  ==PASS");
		} else {
			System.out.println(orgName + " information is not created ==FAIL");
		}

	}

}

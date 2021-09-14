package pageObjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.webelementsUtil;
import automationTests.TestInitiator;
import pageLocators.HelpPageElements;
import pageLocators.HomePageElements;
import pageLocators.JobsPageElements;

public class JobsPageEvents {

	static webelementsUtil element = new webelementsUtil();
	
	/**
	 * verifies if the job page is loaded
	 */
	public static void verifyIfJobsPageIsLoaded()
	{
		TestInitiator.logger.info("verifying if the job page is loaded");
		String actualTitle = TestInitiator.driver.getTitle();
		TestInitiator.logger.info(actualTitle);
		Assert.assertEquals(actualTitle, JobsPageElements.jobPageTitle);
	}
	
	/**
	 * clicks to view open jobs in student programmes and verifies it
	 */
	public static void clickAndVerifyJobsinStudentProgrammes()
	{
		TestInitiator.logger.info("Clicking to view open jobs in student programmes");
		element.getWebElement("xpath", JobsPageElements.viewOpenJobs).click();
		TestInitiator.logger.info("Verifying if there are open jobs student programmes");
		WebElement ele =  element.getWebElement("xpath", JobsPageElements.verifyJobsInStudentProgrammes);
		element.verifyElementTextContains(ele, JobsPageElements.JobsCountPattern);
	}
	
}

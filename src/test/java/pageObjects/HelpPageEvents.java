package pageObjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.webelementsUtil;
import automationTests.TestInitiator;
import pageLocators.HelpPageElements;
import pageLocators.HomePageElements;

public class HelpPageEvents {

	static webelementsUtil element = new webelementsUtil();
	
	/**
	 * verifies if the help page is loaded correctly
	 */
	public static void verifyIfHelpPageIsLoaded()
	{
		TestInitiator.logger.info("verifying if the help page is loaded");
		element.getWebElement("xpath", HelpPageElements.verifyTextOnHelpPageLoad);
		Assert.assertEquals(element.getPageTitle(), HelpPageElements.helpPageTitle);
	}
	
	/** searches the solution on help page
	 * @param solutionSearchTerm
	 * 									search term to be queried on the search bar
	 */
	public static void searchSolutionOnHelpPage(String solutionSearchTerm)
	{
		TestInitiator.logger.info("searching soltion on help page");
		element.getWebElement("css", HelpPageElements.findSolutionsSearchMenu).sendKeys(solutionSearchTerm);
		element.getWebElement("css", HelpPageElements.goButton).click();
	}
	
	/**
	 * verifies the search results
	 */
	public static void verifySearchResults()
	{
		TestInitiator.logger.info("verifying results for searched soltion on help page");
		element.getWebElement("xpath", HelpPageElements.searchResultsHeading);
		WebElement ele = element.getWebElement("xpath", HelpPageElements.searchResultsParagraph);
		element.waitForElementToBeVisible(ele);
		element.verifyElementTextContains(ele, HelpPageElements.searchResultsPattern);
	}
}

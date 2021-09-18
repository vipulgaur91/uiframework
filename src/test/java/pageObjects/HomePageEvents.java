package pageObjects;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.webelementsUtil;
import automationTests.TestInitiator;
import pageLocators.HomePageElements;

public class HomePageEvents {
	static webelementsUtil element = new webelementsUtil();
	static int wait_time = 5;
	static int med_wait_time = 8;
	
	/**
	 * verifies if the accuweather home page is loaded correctly
	 */
	public static void verifyIfHomePageIsLoaded()
	{
		TestInitiator.logger.info("Verifying if the accuweather home page is loaded correctly");
		Assert.assertEquals(element.getPageTitle(), HomePageElements.homePageTitle);
	}
	
	/**
	 * Verifies if the search bar is displayed on accuweather home page
	 */
	public static void verifyIfSearchBarIsDisplayed()
	{
		TestInitiator.logger.info("Verifying if the search bar is displayed on accuweather home page");
		element.waitForElementToBeVisible(element.getWebElement("css", HomePageElements.searchBar));		
	}
	
	/**Enters the city name in search box on accuweather home page
	 * @param searchTerm
	 */
	public static void enterCityNameToSearchOnHomePage(String searchTerm)
	{
		TestInitiator.logger.info("Enter the search query on accuweather home page");
		element.getWebElement("css", HomePageElements.searchTextBox).sendKeys(searchTerm);
	}
	
	/**
	 * Clicks on the search Submit Button
	 */
	public static void clickSearchSubmitButton()
	{
		TestInitiator.logger.info("Clicking on the search Submit Button");
		element.getWebElement("css", HomePageElements.searchSubmitButton).click();
	}
}

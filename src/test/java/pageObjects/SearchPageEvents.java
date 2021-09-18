package pageObjects;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.webelementsUtil;
import automationTests.TestInitiator;
import pageLocators.HomePageElements;
import pageLocators.SearchPageElements;

public class SearchPageEvents {
	static webelementsUtil element = new webelementsUtil();
	static int wait_time = 5;
	static int med_wait_time = 8;
	
	/**
	 * verifies if the accuweather home page is loaded correctly
	 */
	public static void verifyIfSearchPageIsLoaded()
	{
		TestInitiator.logger.info("Verifying if the search page is loaded correctly");
		element.waitForElementToBeVisible(element.getWebElement("css", SearchPageElements.currentPanel));
		TestInitiator.logger.info(element.getPageTitle());
		Assert.assertTrue(SearchPageElements.searchPageTitle.contains(element.getPageTitle()));
	}
	
	/**
	 * Verifies if the search bar is displayed on accuweather home page
	 */
	public static void verifyCurrentWeatherIsDisplayed()
	{
		TestInitiator.logger.info("Verifying if current weather is displayed");
		WebElement ele = element.getWebElement("css", SearchPageElements.currentPanel);
		element.verifyElementTextContains(ele, SearchPageElements.currentWeather);
	}
	
	/**Retrieve the temperature
	 * @return temperature
	 */
	public static double getTemperature()
	{
		String temp = element.getWebElement("xpath", SearchPageElements.temp).getText().trim();
		temp = temp.replaceAll(".C", "");
		double tempui = Double.parseDouble(temp);
		tempui = tempui+273.15;
		TestInitiator.logger.info("Temperature is: "+tempui+"K");
		return tempui;
	}
}

package automationTests;

import java.io.IOException;
import org.testng.annotations.Test;
import utilities.configPropertyReader;
import pageObjects.HomePageEvents;
import pageObjects.SearchPageEvents;

public class HomePageTest extends TestInitiator{
	
	private static String testDataFilename = "Test-Data.properties";
	
	/**searches for a city and validates the search page
	 * @throws IOException
	 */
	@Test(priority=1, enabled=true, groups= "UI")
	public void validateSearchResultsForValidCitySearch() throws IOException
	{
		HomePageEvents.verifyIfHomePageIsLoaded();
		HomePageEvents.verifyIfSearchBarIsDisplayed();
		HomePageEvents.enterCityNameToSearchOnHomePage(configPropertyReader.readAndGetProperty("cityNameForSearch", testDataFilename).toString());
		HomePageEvents.clickSearchSubmitButton();
		SearchPageEvents.verifyIfSearchPageIsLoaded();
		SearchPageEvents.verifyCurrentWeatherIsDisplayed();
		double tempui = SearchPageEvents.getTemperature();
		TestInitiator.temp_ui = tempui;
	}

}

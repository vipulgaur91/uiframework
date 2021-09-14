package automationTests;

import java.io.IOException;
import org.testng.annotations.Test;
import utilities.configPropertyReader;
import pageObjects.HomePageEvents;

public class HomePageTest extends TestInitiator{
	
	private static String testDataFilename = "Test-Data.properties";
	
	/**verifies if the home page is loaded correctly or not
	 * 
	 */
	@Test
	public void verifyHomePage()
	{
		HomePageEvents.verifyIfHomePageIsLoaded();
	}
	
	/**searches for an item and validates the search results
	 * @throws IOException 
	 */
	@Test
	public void validateSearchResultsForValidSearchQuery() throws IOException
	{
		HomePageEvents.verifyIfSearchBarIsDisplayed();
		HomePageEvents.enterSearchQueryOnHomePage(configPropertyReader.readAndGetProperty("validSearchQuery", testDataFilename).toString());
		HomePageEvents.clickSearchSubmitButton();
		HomePageEvents.validateSearchResultsForValidSearchTerm(configPropertyReader.readAndGetProperty("resultsForSearchTermString", testDataFilename).toString(),
				configPropertyReader.readAndGetProperty("validSearchQuery", testDataFilename).toString());
	}
	
	/**
	 * sets and verifies the pincode functionality on home page
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@Test
	public void enterPincode() throws IOException, InterruptedException
	{
		HomePageEvents.clickOnSelectYourAddressBox();
		HomePageEvents.enterPincodeAndClickOnApply(configPropertyReader.readAndGetProperty("pincode", testDataFilename).toString());
		HomePageEvents.verifyIfEnteredPincodeIsPresent(configPropertyReader.readAndGetProperty("pincode", testDataFilename).toString());
	}
	
	/**
	 * sets and verifies the language translation functionality on home page
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@Test
	public void changeHomePageLanguage() throws IOException, InterruptedException
	{
		HomePageEvents.clickOnLanguageChangeButton();
		HomePageEvents.selectHindiLanguageRadioButtonAndClickOnSave();
		HomePageEvents.verifyIfLanguageChangesAppliedOnHomePage(configPropertyReader.readAndGetProperty("pageLanguage", testDataFilename).toString());
	}

}

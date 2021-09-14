package automationTests;

import java.io.IOException;

import org.testng.annotations.Test;

import utilities.configPropertyReader;
import pageObjects.HelpPageEvents;
import pageObjects.HomePageEvents;

public class HelpPageTest extends TestInitiator{
	
	private static String testDataFilename = "Test-Data.properties";

	
	/**navigates to the help page and verifiy the same
	 * 
	 */
	@Test
	public void clickOnCustomerServiceAndNavigateToHelpPage()
	{
		HomePageEvents.clickOnHambugerMenu();
		HomePageEvents.navigateToCustomerServiceInHelpAndSetting();
		HelpPageEvents.verifyIfHelpPageIsLoaded();
	}
	
	/**navigates to the help page,search a solution and verify the search results
	 * @throws IOException 
	 * 
	 */
	@Test
	public void searchSolutionAndVerifyResultsOnHelpPage() throws IOException {
		HomePageEvents.clickOnHambugerMenu();
		HomePageEvents.navigateToCustomerServiceInHelpAndSetting();
		HelpPageEvents.searchSolutionOnHelpPage(configPropertyReader.readAndGetProperty("searchTermForHelp", testDataFilename).toString());
		HelpPageEvents.verifySearchResults();
		
	}

}

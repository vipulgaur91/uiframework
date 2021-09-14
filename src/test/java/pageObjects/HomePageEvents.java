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
	 * verifies if the amazon home page is loaded correctly
	 */
	public static void verifyIfHomePageIsLoaded()
	{
		TestInitiator.logger.info("verifying if the amazon home page is loaded correctly");
		Assert.assertEquals(element.getPageTitle(), HomePageElements.homePageTitle);
	}
	
	/**
	 * Verifies if the search bar is displayed on Amazon Home Page
	 */
	public static void verifyIfSearchBarIsDisplayed()
	{
		TestInitiator.logger.info("Verifying if the search bar is displayed on Amazon Home Page");
		element.waitForElementToBeVisible(element.getWebElement("css", HomePageElements.searchBar));		
	}
	
	/**Enters the search query on Amazon Home Page
	 * @param searchTerm
	 * 							search query
	 */
	public static void enterSearchQueryOnHomePage(String searchTerm)
	{
		TestInitiator.logger.info("Enter the search query on Amazon Home Page");
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
	
	/**
	 *Verifies search results for the valid search term entered by user 
	 */
	public static void validateSearchResultsForValidSearchTerm(String firstXpathParam, String secondXpathParam)
	{
		TestInitiator.logger.info("Verifying search results for the valid search term entered by user");
		assertTrue(element.createDynamicXpath(HomePageElements.verifySearchResults, firstXpathParam, secondXpathParam).isDisplayed(),
				"expected search results not present on the web page");
	}
	
	/**
	 * Hovers over the Hello-Sign In Box
	 */
	public static void moveToHelloSignInBox()
	{
		TestInitiator.logger.info("Hovering over the Hello-Sign In Box");
		element.hoverOverWebElement(element.getWebElement("css", HomePageElements.helloSignInBox));
	}
	
	/**
	 * Clicks on the sign in button on home page
	 */
	public static void clickSignInButton()
	{
		TestInitiator.logger.info("Clicking on the sign in button");
		element.waitForElementToBeClickable(element.getWebElement("css", HomePageElements.signInButton));
		element.getWebElement("css", HomePageElements.signInButton).click();
	}
	
	/**
	 *	Clicks on select your address box 
	 */
	public static void clickOnSelectYourAddressBox() throws InterruptedException
	{
		TestInitiator.logger.info("Clicking on select your address box");
		element.waitForElement(med_wait_time);
		element.getWebElement("xpath", HomePageElements.selectYourAddressBox).click();
		element.waitForElementToBeVisible(element.getWebElement("css", HomePageElements.enterPincodeTextBox));
	}
	
	/**Enters the pincode and applies it
	 * @param pincode
	 *							Pincode to be entered
	 */
	public static void enterPincodeAndClickOnApply(String pincode) throws InterruptedException
	{
		TestInitiator.logger.info("Entering the pincode");
		element.waitForElementToBeClickable(element.getWebElement("css", HomePageElements.enterPincodeTextBox));
		element.getWebElement("css", HomePageElements.enterPincodeTextBox).sendKeys(pincode);
		element.waitForElementToBeClickable(element.getWebElement("xpath", HomePageElements.applyButton));
		element.getWebElement("xpath", HomePageElements.applyButton).click();
		element.waitForElement(wait_time);
	}
	
	/**Verifies if the entered pincode is present on the web page
	 * @param pincode
	 * 							pincode to be verified
	 */
	public static void verifyIfEnteredPincodeIsPresent(String pincode)
	{
		TestInitiator.logger.info("Verifying if the entered pincode is present on the page or not");
		element.waitForElementToBeVisible(element.getWebElement("xpath", HomePageElements.verifyPincode));
		String actualText = element.getWebElement("xpath", HomePageElements.verifyPincode).getText();
		Assert.assertTrue(actualText.trim().contains(pincode));
	}
	
	/**
	 * Clicks on the hamburger menu
	 */
	public static void clickOnHambugerMenu()
	{
		TestInitiator.logger.info("Clicking on the hamburger menu");
		element.waitForElement(wait_time);
		element.waitForElementToBeClickable(element.getWebElement("css", HomePageElements.hamburgerMenuAll));
		element.getWebElement("css", HomePageElements.hamburgerMenuAll).click();
	}
	
	/**
	 * Navigates to the customer service section
	 */
	public static void navigateToCustomerServiceInHelpAndSetting()
	{
		TestInitiator.logger.info("Navigating to the customer service");
		WebElement ele = element.getWebElement("xpath", HomePageElements.customerService);
		element.waitForElementToBeVisible(ele);
		element.waitForElement(wait_time);
		element.executeJavascript("arguments[0].scrollIntoView(true);", ele);
		element.getWebElement("xpath", HomePageElements.customerService).click();
		element.waitForElement(wait_time);
	}
	
	/**
	 * Click on the language change button on web page
	 */
	public static void clickOnLanguageChangeButton()
	{
		TestInitiator.logger.info("Changing the language of home page");
		element.getWebElement("css", HomePageElements.chooseLanguageIcon).click();
	}
	
	/**
	 * Selects hindi language from the list
	 */
	public static void selectHindiLanguageRadioButtonAndClickOnSave()
	{
		TestInitiator.logger.info("Selecting hindi language");
		element.waitForElementToBeClickable(element.getWebElement("css", HomePageElements.hindiLanguageRadioButton));
		element.getWebElement("css", HomePageElements.hindiLanguageRadioButton).click();
		element.getWebElement("css", HomePageElements.saveChangesButton).click();
	}
	
	/**Verifies if Language changes are there on home page
	 * @param Language
	 * 							Language to be verified
	 */
	public static void verifyIfLanguageChangesAppliedOnHomePage(String Language) throws InterruptedException
	{
		TestInitiator.logger.info("Verifying Language changes on home page");
		element.waitForElement(wait_time);
		WebElement ele = element.getWebElement("css", HomePageElements.chooseLanguageIcon);
		element.hoverOverWebElement(ele);
		assertTrue(element.createDynamicXpath(HomePageElements.verifyLanguageRadioButtonSelected, Language).isDisplayed(), "Language is not correctly set on the web page");
		TestInitiator.logger.info("Successfully verified the Language Changes");
	}
	
	/**
	 * 	Navigates to the Careers/jobs Page
	 */
	public static void navigateToJobsPage()
	{
		TestInitiator.logger.info("Navigating to the Careers/jobs Page");
		element.waitForElementToBeVisible(element.getWebElement("xpath", HomePageElements.careersLink));
		element.getWebElement("xpath", HomePageElements.careersLink).click();
	}
}

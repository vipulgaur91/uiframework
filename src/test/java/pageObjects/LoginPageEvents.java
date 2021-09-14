package pageObjects;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import utilities.webelementsUtil;
import automationTests.TestInitiator;
import pageLocators.LoginPageElements;

public class LoginPageEvents {
	
	static webelementsUtil element = new webelementsUtil();
	
	/**
	 * Verifies if the amazon login page is displayed correctly
	 */
	public static void verifySignInPage()
	{
		TestInitiator.logger.info("Verifying if the amazon login page is displayed correctly");
		element.waitForElementToBeVisible(element.getWebElement("xpath", LoginPageElements.verifySignInText));
	}
	
	/**Enters the email address
	 * @param email
	 * 					email to be entered
	 */
	public static void enterEmailAddress(String email)
	{
		TestInitiator.logger.info("Entering the email address");
		element.getWebElement("css", LoginPageElements.enterEmail).sendKeys(email);
	}
	
	/**
	 * Clicks on continue button
	 */
	public static void clickOnContinueButton()
	{
		TestInitiator.logger.info("Clicking on continue button");
		element.getWebElement("css", LoginPageElements.continueButton).click();
	}
	
	/**Enters the invalid password
	 * @param password
	 * 					 password to be entered
	 */
	public static void enterPassword(String password)
	{
		TestInitiator.logger.info("Entering password");
		element.getWebElement("css", LoginPageElements.enterPassword).sendKeys(password);
	}
	
	/**
	 * Clicks on signIn Submit button
	 */
	public static void clickSignInSubmitButton()
	{
		TestInitiator.logger.info("Clicking on signIn Submit button");
		element.getWebElement("css", LoginPageElements.signInSubmitButton).click();
	}
	
	/**
	 * Verifies the credentials entered are not correct
	 */
	public static void verifyIfCredentialsEnteredAreIncorrect()
	{
		TestInitiator.logger.info("Verifying the credentials entered are not correct");
		assertTrue(element.getWebElement("xpath", LoginPageElements.reEnterPassword).isDisplayed(), "Element not displayed on the page");
	}
}

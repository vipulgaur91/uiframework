package automationTests;

import java.io.IOException;

import org.testng.annotations.Test;

import utilities.configPropertyReader;
import pageObjects.HomePageEvents;
import pageObjects.LoginPageEvents;

/**
 * @author vipulgaur
 *
 */

public class LoginPageTest extends TestInitiator{
	private static String testDataFilename = "Test-Data.properties";
	
	/**testLoginWithInvalidCredentials tests the login functionality with invalid username and password
	 * @throws IOException
	 */
	@Test
	public void testLoginWithInvalidCredentials() throws IOException
	{
		HomePageEvents.clickSignInButton();
		LoginPageEvents.verifySignInPage();
		LoginPageEvents.enterEmailAddress(configPropertyReader.readAndGetProperty("invalid_username", testDataFilename).toString());
		LoginPageEvents.clickOnContinueButton();
		LoginPageEvents.enterPassword(configPropertyReader.readAndGetProperty("invalid_password", testDataFilename).toString());
		LoginPageEvents.clickSignInSubmitButton();
		LoginPageEvents.verifyIfCredentialsEnteredAreIncorrect();
	}
}

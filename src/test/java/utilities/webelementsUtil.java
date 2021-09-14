package utilities;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automationTests.TestInitiator;

public class webelementsUtil {
	int retry_count=4;
	Long explicitTimeout = (long) 15;
	WebDriverWait wait = new WebDriverWait(TestInitiator.driver, explicitTimeout);
	
	/** Takes identifier type and its value, finds webelement on this basis and returns it
	 * @param identifierType
	 * 							type of identifier e.g. css, xpath etc.
	 * @param identifierValue
	 * 							value of the locator
	 * @return
	 */
	public WebElement getWebElement(String identifierType, String identifierValue)
	{
		switch (Locators.valueOf(identifierType)) {
		 case id:
			return TestInitiator.driver.findElement(By.id(identifierValue));
		 case css:
			return TestInitiator.driver.findElement(By.cssSelector(identifierValue));
		 case xpath:
			return TestInitiator.driver.findElement(By.xpath(identifierValue));
		 case tagname:
			return TestInitiator.driver.findElement(By.tagName(identifierValue));
			default:
				return null;
		}
	}
	
	/**Takes identifier type and its value, finds webelements and returns the list of webelements
	 * @param identifierType
	 * 							type of identifier e.g. css, xpath etc.
	 * @param identifierValue
	 * 							value of the locator
	 * @return
	 */
	public List<WebElement> getListWebElements(String identifierType, String identifierValue)
	{
		switch (Locators.valueOf(identifierType)) {
		 case id:
			return TestInitiator.driver.findElements(By.id(identifierValue));
		 case css:
			return TestInitiator.driver.findElements(By.cssSelector(identifierValue));
		 case xpath:
			return TestInitiator.driver.findElements(By.xpath(identifierValue));
		 case tagname:
			return TestInitiator.driver.findElements(By.tagName(identifierValue));
			default:
				return null;
		}
	}
	
	/**createDynamicXpath replaces the args provided with their values and returns the created xpath out of it
	 * @param xpathExpression
	 * 							  xpath expression in which values are to be replaced
	 * @param args
	 * 							   args values which is to pe put in the xpath expression	
	 * @return
	 */
	public WebElement createDynamicXpath(String xpathExpression, Object ...args)
	{	
		WebElement dynamicXpath = null;
		for(int i=0; i<args.length; i++)
		{
			xpathExpression = xpathExpression.replace("{"+i+"}", (CharSequence) args[i]);	
		}
		dynamicXpath = TestInitiator.driver.findElement(By.xpath(xpathExpression));
		return dynamicXpath;
	}
	
	/**hoverOverWebElement   hovers over the web element
	 * @param element
	 * 					Webelement at which we need to hover
	 */
	public void hoverOverWebElement(WebElement element)
	{
		Actions actions = new Actions(TestInitiator.driver);
		actions.moveToElement(element).perform();
	}
	
	/** Waits For Element To Be Visible
	 * @param element
	 * 						Webelement for which we need to wait
	 */
	public void waitForElementToBeVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**		Waits For Element To Be Clickable
	 * @param element
	 * 						Webelement for which we need to wait
	 */
	public void waitForElementToBeClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**wait For Element To Be Retrieved Again In Dom
	 * @param element
	 * 					Webelement for which we need to wait
	 */
	public void waitForElementToBeRetrievedAgainInDom(WebElement element)
	{
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
	}
	
	/**
	 *  Refreshes the webpage
	 */
	public void refreshPage()
	{
		TestInitiator.driver.navigate().refresh();
	}
	
	public void verifyElementTextContains(WebElement elementName, String expectedPattern) {
        waitForElementToBeVisible(elementName);
        String elementText = elementName.getText().trim();
        Predicate<String> textElement = Pattern.compile(expectedPattern).asPredicate();
        Assert.assertTrue(textElement.test(elementText));
    }
	
	/**Extracts and returns the page title
	 * @return
	 */
	public String getPageTitle()
	{
		String title = TestInitiator.driver.getTitle();
		return title;
	}
	
	/**Executes the Java script on the given web element
	 * @param script
	 * 						Java Script to be executed
	 * @param element
	 * 						Webelement at which java script to be executed
	 */
	public void executeJavascript(String script, WebElement element) {
		((JavascriptExecutor) TestInitiator.driver).executeScript(script,element);
	}
	
	/** waits for the element and retry to find it
	 * @param retry
	 */
	public void waitForElement(int retry)
	{
		for(int i=0; i<retry; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	  }
	}
	

}

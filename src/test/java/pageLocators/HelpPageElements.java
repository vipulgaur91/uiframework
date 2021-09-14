package pageLocators;

public interface HelpPageElements {
	
	String helpPageTitle = "Amazon.in Help: Help";
	String verifyTextOnHelpPageLoad = "//div/h1[contains(text(),'Hello. What can we help you with?')]";
	String findSolutionsSearchMenu = "div.a-search input[type='search']";
	String goButton = "span.a-button-inner input[aria-labelledby='helpSearchSubmit-announce']";
	String searchResultsHeading = "//div/h1[contains(text(),'Help Search Results')]";
	String searchResultsParagraph = "//div/h1[contains(text(),'Help Search Results')]/following-sibling::p";
	String searchResultsPattern = "\\d+\\ssearch results for";
}

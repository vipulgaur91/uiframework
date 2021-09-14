package pageLocators;

public interface HomePageElements {
	String homePageTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	String searchBar = "div[id='nav-search']";
	String searchTextBox = "input[id='twotabsearchtextbox']";
	String searchSubmitButton = "input[id='nav-search-submit-button']";
	String helloSignInBox = "a[id='nav-link-accountList']";
	String signInButton = "#nav-link-accountList";
	String selectYourAddressBox = "//span[@class='nav-line-2 nav-progressive-content']";
	String enterPincodeTextBox = "input[aria-label=\"or enter an Indian pincode\"]";
	String applyButton = "//span[contains(text(),'Apply')]/parent::*";
	String verifyPincode = "//span[@class='nav-line-2 nav-progressive-content']";
	String verifySearchResults = "//span[contains(text(),'{0}')]/following-sibling::span[contains(text(),'{1}')]";
	String hamburgerMenuAll = "#nav-hamburger-menu";
	String customerService = "//li/a[contains(text(),'Customer Service')]";
	String chooseLanguageIcon = "#nav-tools>#icp-nav-flyout";
	String hindiLanguageRadioButton = "div.a-radio label input[value='hi_IN']+i";
	String saveChangesButton = "span.a-button-inner input.a-button-input";
	String verifyLanguageRadioButtonSelected = "//div[@id='nav-flyout-icp']//span[contains(text(), '{0}')]/i[@class='icp-radio icp-radio-active']";
	String careersLink = "//a[contains(text(),'Careers')]";
}

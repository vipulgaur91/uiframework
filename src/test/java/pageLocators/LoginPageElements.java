package pageLocators;

import org.openqa.selenium.By;

public interface LoginPageElements {
		String verifySignInText = "//div/h1[contains(text(),\"Sign-In\")]";
		String enterEmail = "input[id=\"ap_email\"]";
		String continueButton = "input[id=\"continue\"]"; 
		String enterPassword = "input[id=\"ap_password\"]";
		String signInSubmitButton = "input[id=\"signInSubmit\"]";
		String reEnterPassword = "//span[contains(text(), 'To better protect your account, please re-enter your password')]";
}

package com.abpayments.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.abpayments.ui.driver.BaseTest;

public class LoginPage extends BaseTest{
	
	
	@FindBy(name = "e_code") private WebElement enterLoginId;
	@FindBy(name = "password") private WebElement enterPwd;
	@FindBy(id = "submitbtn") private WebElement loginBtn;
	
	
	private LoginPage enterLoginId(String loginId) {
		enterText(enterLoginId, loginId);
		return this;
	}
	
	private LoginPage enterPwd(String password) {
		enterText(enterPwd, password);
		return this;
	}
	
	private void clickLoginBtn() {
		clickElement(loginBtn, "Login Button");
	}
	
	public HomePage loginToWebSite(String loginId, String password) {
		enterLoginId(loginId);
		enterPwd(password);
		clickLoginBtn();
		return new HomePage();
	}

	

}

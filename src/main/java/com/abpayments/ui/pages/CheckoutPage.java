package com.abpayments.ui.pages;

import com.abpayments.ui.driver.BaseTest;

public class CheckoutPage extends BaseTest{
	
	public SuccessPage clickOnDespositButton() {
		return new SuccessPage();
	}
	
	public CheckoutPage clickOnPaymentOption(String payment) {
		return this;
	}

}

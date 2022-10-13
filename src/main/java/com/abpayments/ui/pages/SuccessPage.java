package com.abpayments.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.abpayments.ui.driver.BaseTest;

public class SuccessPage extends BaseTest{
	
	@FindBy(id = "order")
	private WebElement order;
	
	public String getOrderId() {
		return getText(order);
	}

}

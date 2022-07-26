package com.abpayments.com.abpayment;

import org.testng.annotations.Test;

import com.abpayments.api.applicationapi.DepositApi;
import com.abpayments.api.utils.ApiUtils;
import com.abpayments.ui.pages.LoginPage;

import io.restassured.response.Response;

public class ABTest {
	
	
	@Test
	public void abTest() {
		String orderId = new LoginPage().loginToWebSite("abc", "123")
					   .navigateToProduct()
					   .navigateToPaymentPage()
					   .clickOnPaymentOption("UPI")
					   .clickOnDespositButton()
					   .getOrderId();
		
		System.out.println(orderId);
			
	}
	
	
	@Test
	public void testUserId() {
		for(int i = 0; i < 10; i++) {
			int randomUserId = ApiUtils.randomNumberGen(1, 10);
			int userId = ApiUtils.getuserid(randomUserId);
			Response response = DepositApi.get(userId);
			String paymentOption = response.body().path("Payment_options"+ "[" + userId + "]");
		}
		
	}

}

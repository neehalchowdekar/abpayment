package com.abpayments.api.applicationapi;



import com.abpayments.api.pojo.Payments;

import io.restassured.response.Response;

public final class DepositApi {
	
	private DepositApi() {}
 	
	public static Response post(Payments requestUsers) {
		return RestResource.post(requestUsers, Route.DEPOSIT);
	}
	
	public static Response get(int userId) {
		return RestResource.get(userId);
	}
	
	public static Response delete() {
		return RestResource.delete(Route.DEPOSIT);
	}
}


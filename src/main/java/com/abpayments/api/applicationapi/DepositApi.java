package com.abpayments.api.applicationapi;



import com.abpayments.api.pojo.Payments;

import io.restassured.response.Response;

public final class DepositApi {
	
	private DepositApi() {}
 	
	public static Response post(Payments requestUsers) {
		return RestResource.post(requestUsers, Route.USERS);
	}
	
	public static Response get(int pageNo) {
		return RestResource.get(Route.TRANSACTIONS, pageNo);
	}
	
	public static Response delete() {
		return RestResource.delete(Route.TRANSACTIONS);
	}
}


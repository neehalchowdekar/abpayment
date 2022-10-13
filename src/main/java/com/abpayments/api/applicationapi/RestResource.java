package com.abpayments.api.applicationapi;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public final class RestResource {
	
	private RestResource() {}
	
	
	// Create
	public static Response post(Object request, String path) {

		return given(SpecBuilder.getRequestSpec())
				.body(request)
				.when()
				.post(path)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
	}
	
	// Read
	public static Response get() {
		return given(SpecBuilder.getRequestSpec())
				.when()
				.get()
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
	}
	
	
	public static Response get(int userId) {
		return given(SpecBuilder.getRequestSpec())
				.when()
				.get("/" + userId)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
	}
	
	// Delete
	public static Response delete(String path) {
		return given(SpecBuilder.getRequestSpec())
				.when()
				.delete(path)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
	}
	
	
	// Update
	public static Response put(String path, String request) {
		return given(SpecBuilder.getRequestSpec())
				.body(request)
				.when()
				.put(path)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
	}
	
	

}

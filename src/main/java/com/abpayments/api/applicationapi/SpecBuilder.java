package com.abpayments.api.applicationapi;

import com.abpayments.api.config.ConfigFactory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public final class SpecBuilder {

	private SpecBuilder() {
	}

	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(ConfigFactory.getConfig().baseUri())
				.setBasePath(Route.API)
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL)
				.build();
	}

	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().
		// expectContentType(ContentType.JSON).
				log(LogDetail.ALL).build();
	}

}

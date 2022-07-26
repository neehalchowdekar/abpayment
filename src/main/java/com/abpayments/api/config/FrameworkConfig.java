package com.abpayments.api.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	"system:properties",
	"system:env",
	"file:${user.dir}/src/test/resources/prod-config.properties",
})
public interface FrameworkConfig extends Config{
	
	@DefaultValue("prod")
	String environment();
	
	@Key("${environment}.baseUri")
	String baseUri();
}

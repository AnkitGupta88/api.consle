package com.global.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

	@Value("${spring.data.kong.admin}")
	private String KONGADMIN;
	
	@Value("${spring.data.kong.user}")
	private String KONGUSER;

	public String getKONGADMIN() {
		return KONGADMIN;
	}

	public String getKONGUSER() {
		return KONGUSER;
	}

}

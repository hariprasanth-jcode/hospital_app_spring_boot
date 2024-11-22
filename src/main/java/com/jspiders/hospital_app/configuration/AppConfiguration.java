package com.jspiders.hospital_app.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfiguration {

	@Bean
	public OpenAPI appconfiguration() {

		Server s = new Server();
		s.setUrl("http://localhost:8080/hospital_app");
		s.setDescription("Hospital App");

		Contact c = new Contact();
		c.setEmail("email@gmail.com");
		c.setName("hospital");
		c.setUrl("testyantra.com");

		License l = new License();
		l.setName("SSL License");
		l.setUrl("tewst.com");

		Info i = new Info().contact(c).license(l).description("hospital").version("1.0").termsOfService("xyz");
	

		return new OpenAPI().info(i).servers(List.of(s));
	}
}

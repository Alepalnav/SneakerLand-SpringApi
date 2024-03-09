package com.jacaranda.apiPalmaAlejandro;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

@SpringBootApplication
public class ApiPalmaAlejandroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPalmaAlejandroApplication.class, args);
	}
	
	@Bean
	public Cloudinary cloudinaryConfig() {
		Cloudinary cloudinary = null;
		Map<String,String> config = new HashMap<String,String>();
		config.put("cloud_name", "dahrrd7yj");
		config.put("api_key", "155954149867264");
	
		config.put("api_secret", "kzDPxlR0yYE6HmzDmw_5DF47lZY");
		cloudinary = new Cloudinary(config);
	return cloudinary;
	}

}

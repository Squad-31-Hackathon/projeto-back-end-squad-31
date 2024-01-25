package com.squad31.apiorangeportifolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "api-squad-31", version = "1.0", description = "API Squad-31"))
public class ApiOrangePortifolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiOrangePortifolioApplication.class, args);
	}

}

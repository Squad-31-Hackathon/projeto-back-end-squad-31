package com.squad31.apiorangeportifolio;

import com.squad31.apiorangeportifolio.Utils.LoadSeeds;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "api-squad-31", version = "1.0", description = "API Squad-31"))
@SecurityScheme(name = "token", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
<<<<<<< Updated upstream
public class ApiOrangePortifolioApplication  {
=======
public class ApiOrangePortifolioApplication implements ApplicationRunner {



>>>>>>> Stashed changes

	public static void main(String[] args) {
		SpringApplication.run(ApiOrangePortifolioApplication.class, args);
	}


<<<<<<< Updated upstream
=======


	}
>>>>>>> Stashed changes
}

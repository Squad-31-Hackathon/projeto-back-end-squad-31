package com.squad31.apiorangeportifolio;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import com.squad31.apiorangeportifolio.Utils.LoadSeeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiOrangePortifolioApplication implements ApplicationRunner {

	@Autowired
	private LoadSeeds loadSeeds;

	public static void main(String[] args) {
		SpringApplication.run(ApiOrangePortifolioApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		loadSeeds.loadUsers();

	}
}

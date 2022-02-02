package com.crudv2.crudSpring;

import com.crudv2.crudSpring.repository.ImagenRepository;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories

public class CrudSpringApplication  implements CommandLineRunner {
	@Autowired
	ImagenRepository imgRepo;
	public static void main(String[] args) {

		SpringApplication.run(CrudSpringApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {}

	@Bean
	public OpenAPI openAPIConfig(){
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		Info info = new Info();
		info
				.title("Monolito con swagger")
				.description("Documentación del monolito con swagger")
				.version("V1");

		return info;
	}



}

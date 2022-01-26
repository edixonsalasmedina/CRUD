package com.crudv2.crudSpring;

import com.crudv2.crudSpring.entity.Imagen;
import com.crudv2.crudSpring.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	public void run(String... args) throws Exception {
		//System.out.println("Data creation started...");
		//imgRepo.save(new Imagen("1","Whole Wheat Biscuit", "15", "snacks"));
		//imgRepo.save(new Imagen("2","XYZ Kodo Millet healthy", "2", "millets"));
		//System.out.println("Data creation complete...");

	}



}

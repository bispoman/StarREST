package com.bisponet.StarREST;

import com.bisponet.StarREST.model.Planet;
import com.bisponet.StarREST.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarRestApplication implements CommandLineRunner {

	@Autowired
	private PlanetRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StarRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		repository.save(new Planet("Tatooine", "arid", "desert", 5));
		repository.save(new Planet("Alderaan", "temperate", "grasslands, mountains", 2));
		repository.save(new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests", 1));
		repository.save(new Planet("Hoth", "frozen", "tundra, ice caves, mountain ranges", 4));
	}

}

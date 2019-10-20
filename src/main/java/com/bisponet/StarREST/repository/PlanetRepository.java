package com.bisponet.StarREST.repository;

import com.bisponet.StarREST.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    Planet findByName(String name);

}

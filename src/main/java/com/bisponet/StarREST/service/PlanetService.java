package com.bisponet.StarREST.service;

import com.bisponet.StarREST.model.Planet;
import com.bisponet.StarREST.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository repository;

    public List<Planet> findAllPlanets() {
        return repository.findAll();
    }

    public Optional<Planet> getPlanetById(String id){
        return repository.findById(id);
    }

    public Planet getPlanetByName(String nome) {
        return repository.findByName(nome);
    }

    public Boolean saveNewPlanet(String nome, String clima, String terreno) {

        try {
            Planet planet = new Planet(nome, clima, terreno);
            repository.save(planet);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

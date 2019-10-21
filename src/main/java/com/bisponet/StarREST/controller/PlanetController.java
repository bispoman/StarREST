package com.bisponet.StarREST.controller;

import com.bisponet.StarREST.model.Planet;
import com.bisponet.StarREST.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlanetController {

    @Autowired
    private PlanetService service;

    //Endpoint para buscar todos os planetas do banco
    @GetMapping("/planetas")
    public List<Planet> planets() {
        return service.findAllPlanets();
    }

    //Endpoint para buscar os planetas baseado no ID
    @GetMapping("/planeta/id/{id}")
    public Optional<Planet> findPlanetById(@PathVariable String id) {
        return service.getPlanetById(id);
    }

    //Endpoint para buscar os planetas baseado no nome
    @GetMapping("/planeta/nome/{name}")
    public Planet findPlanetByName(@PathVariable String name) {
        return service.getPlanetByName(name);
    }

    //Endpoint para inserir planetas no banco de dados
    @PostMapping("/planeta")
    public ResponseEntity<?> addNewPlanet(@Valid @RequestBody Planet planeta) {

            if (service.saveNewPlanet(planeta)) {
                return new ResponseEntity<String>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
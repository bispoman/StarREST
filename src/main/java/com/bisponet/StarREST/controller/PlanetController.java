package com.bisponet.StarREST.controller;

import com.bisponet.StarREST.model.Planet;
import com.bisponet.StarREST.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlanetController {

    @Autowired
    private PlanetService service;

    @GetMapping("/planetas")
    public List<Planet> planets() {
        return service.findAllPlanets();
    }

    @GetMapping("/planeta/{id}")
    public Optional<Planet> findPlanetById(@PathVariable String id) {
        return service.getPlanetById(id);
    }

    @GetMapping("/planeta/{name}")
    public Planet findPlanetByName(@PathVariable String name) {
        return service.getPlanetByName(name);
    }

    @PostMapping("/planeta")
    public ResponseEntity<?> addNewPlanet(@RequestBody Map<String, String> payload) {

        if (payload.get("nome").isEmpty() || payload.get("clima").isEmpty() || payload.get("terreno").isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String nome = payload.get("nome");
            String clima = payload.get("clima");
            String terreno = payload.get("terreno");
            if (service.saveNewPlanet(nome, clima, terreno)) {
                return new ResponseEntity<String>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
}
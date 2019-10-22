package com.bisponet.StarREST.service;

import com.bisponet.StarREST.model.Planet;
import com.bisponet.StarREST.repository.PlanetRepository;
import com.sun.source.tree.ModuleTree;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanetServiceTest {

    @Autowired
    private PlanetService service;
    @MockBean
    private PlanetRepository repository;

    @Test
    void findAllPlanets() {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("tatooine","arid", "desert", 5));
        planets.add(new Planet("Alderaan", "temperate", "grasslands, mountains", 2));
        planets.add(new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests", 1));
        planets.add(new Planet("Hoth", "frozen", "tundra, ice caves, mountain ranges", 4));

        Mockito.when(repository.findAll()).thenReturn(planets);

        Assert.assertEquals(planets, service.findAllPlanets());
    }

    @Test
    void getPlanetById() {
        Optional<Planet> planet = java.util.Optional.of(new Planet("tatooine","arid", "desert", 5));

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(planet);

        Assert.assertEquals(planet, service.getPlanetById(Mockito.anyString()));

    }

    @Test
    void getPlanetByName() {
        Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains", 2);

        Mockito.when(repository.findByNome(Mockito.anyString())).thenReturn(planet);

        Assert.assertEquals(planet, service.getPlanetByName(Mockito.anyString()));
    }

    @Test
    void saveNewPlanet() {
        Planet planet = new Planet("Alderaan", "temperate", "grasslands, mountains", 2);

        Mockito.when(repository.save(Mockito.anyObject())).thenReturn(planet);

        Assert.assertEquals(true, service.saveNewPlanet(planet));

    }
}
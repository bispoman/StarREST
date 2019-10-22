package com.bisponet.StarREST.controller;

import com.bisponet.StarREST.model.Planet;
import com.bisponet.StarREST.service.PlanetService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlanetService planetService;

    @Test
    void getAllplanets() throws Exception{
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("tatooine","arid", "desert", 5));

        Mockito.when(planetService.findAllPlanets()).thenReturn(planets);

        MvcResult mvcResult = mockMvc.perform(get("/api/planetas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<Planet> planetsResponse = objectMapper.readValue(contentAsString, new TypeReference<List<Planet>>() {});
        Assert.assertEquals(planets, planetsResponse);
    }

    @Test
    void errorOnGetAllplanets() throws Exception{
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet());

        Mockito.when(planetService.findAllPlanets()).thenReturn(planets);

        mockMvc.perform(get("/api/planetas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    @Test
    void findPlanetById() throws Exception {
        Planet planet = new Planet("tatooine","arid", "desert", 5);

        Mockito.when(planetService.getPlanetById(Mockito.anyString())).thenReturn(java.util.Optional.of(planet));

        MvcResult mvcResult = mockMvc.perform(get("/api/planeta/id/1321").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Planet planetsResponse = objectMapper.readValue(contentAsString, Planet.class);
        Assert.assertEquals(planet, planetsResponse);
    }

    @Test
    void findPlanetByName() throws Exception {
        Planet planet = new Planet("tatooine","arid", "desert", 5);

        Mockito.when(planetService.getPlanetByName(Mockito.anyString())).thenReturn(planet);

        MvcResult mvcResult = mockMvc.perform(get("/api/planeta/nome/qualquernome").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Planet planetsResponse = objectMapper.readValue(contentAsString, Planet.class);
        Assert.assertEquals(planet, planetsResponse);
    }


    @Test
    void addNewPlanet() throws Exception {
        Planet planet = new Planet("tatooine","arid", "desert", 5);
        String json = "{\n" +
                "    \"nome\": \"tatooine\",\n" +
                "    \"clima\": \"arid\",\n" +
                "    \"terreno\": \"desert\", \n" +
                "    \"aif\": 5\n" +
                "}";

        Mockito.when(planetService.saveNewPlanet(planet)).thenReturn(true);

        mockMvc.perform(post("/api/planeta").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void failToAddNewPlanet() throws Exception {
        Planet planet = new Planet("tatooine","arid", "desert", 5);
        String json = "{\n" +
                "    \"nome\": \"tatooine\",\n" +
                "    \"clima\": \"arid\",\n" +
                "    \"terreno\": \"desert\", \n" +
                "    \"aif\": 5\n" +
                "}";

        Mockito.when(planetService.saveNewPlanet(planet)).thenReturn(false);

        mockMvc.perform(post("/api/planeta").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }
}
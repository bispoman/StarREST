package com.bisponet.StarREST.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Planet")
public class Planet {

    @Id
    private String id;

    private String nome;

    private String clima;

    private String terreno;

    private Integer aif;

    public Planet(String nome, String clima, String terreno, Integer aif) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
        this.aif = aif;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public int getAif() {
        return aif;
    }

    public void setAif(Integer aif) {
        this.aif = aif;
    }
}

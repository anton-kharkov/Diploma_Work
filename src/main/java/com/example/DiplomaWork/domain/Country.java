package com.example.DiplomaWork.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {

    private int id;
    private String name;
    private String continent;
    private int residents;
    private int gdp;

    public Country() {}

    public Country(int id, String name, String continent, int residents, int gdp) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.residents = residents;
        this.gdp = gdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public int getGdp() {
        return gdp;
    }

    public void setGdp(int gdp) {
        this.gdp = gdp;
    }
}

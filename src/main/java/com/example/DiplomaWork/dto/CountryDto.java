package com.example.DiplomaWork.dto;

import lombok.Builder;

@Builder
public class CountryDto {

    private int id;
    private String name;
    private String continent;
    private int residents;
    private int gdp;

    public CountryDto() {
    }

    public CountryDto(int id, String name, String continent, int residents, int gdp) {
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

package com.example.DiplomaWork.dto;

import lombok.Builder;

@Builder
public class CityDto {

    private int id;
    private String name;
    private int country_id;
    private int residents;
    private boolean capital;

    public CityDto() {
    }

    public CityDto(String name, int country_id, int residents, boolean capital) {
        this.name = name;
        this.country_id = country_id;
        this.residents = residents;
        this.capital = capital;
    }

    public CityDto(int id, String name, int country_id, int residents, boolean capital) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
        this.residents = residents;
        this.capital = capital;
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

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }
}

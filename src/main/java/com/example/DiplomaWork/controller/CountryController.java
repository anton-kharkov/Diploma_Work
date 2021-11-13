package com.example.DiplomaWork.controller;

import com.example.DiplomaWork.dto.CountryDto;
import com.example.DiplomaWork.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryDto> findAllCountry(){return countryService.findAllCountry();}

    @GetMapping("/all-by-country")
    public void getAllCityByCountryName(@RequestParam String country){
        countryService.getAllCityByCountryName(country);
    }

    @GetMapping("/getCountryCapital")
    public void getCapitalByCountryName(@RequestParam String country){
        countryService.getCapitalByCountryName(country);
    }

    @PostMapping
    public void addCountry(@RequestBody CountryDto countryDto) throws SQLException {
        countryService.addCountry(countryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){countryService.deleteById(id);}
}

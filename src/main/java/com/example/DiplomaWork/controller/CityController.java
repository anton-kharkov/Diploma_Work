package com.example.DiplomaWork.controller;

import com.example.DiplomaWork.dto.CityDto;
import com.example.DiplomaWork.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public void getAllHumanByCityName(@RequestParam String city){cityService.getAllHumanByCityName(city);}

    @PostMapping
    public void addCity(@RequestBody CityDto cityDto){cityService.addCity(cityDto);}

    @DeleteMapping
    public void deleteById(@RequestParam int id){cityService.deleteById(id);}
}

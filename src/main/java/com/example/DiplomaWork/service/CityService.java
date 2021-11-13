package com.example.DiplomaWork.service;

import com.example.DiplomaWork.dao.CityDao;
import com.example.DiplomaWork.domain.City;
import com.example.DiplomaWork.domain.Human;
import com.example.DiplomaWork.dto.CityDto;
import com.example.DiplomaWork.dto.HumanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityDao cityDao;

    @Transactional
    public void addCity(CityDto cityDto){
        City city = fromCityDto(cityDto);
        cityDao.addCity(city);
    }

    @Transactional
    public void deleteById(int id){cityDao.deleteById(id);}

    public List<HumanDto> getAllHumanByCityName(String city){
        return cityDao.getAllHumanByCityName(city)
                .stream()
                .map(this::getBuildHuman)
                .collect(Collectors.toList());
    }


    private HumanDto getBuildHuman(Human human) {
        return HumanDto.builder()
                .id(human.getId())
                .firstName(human.getFirstName())
                .lastName(human.getLastName())
                .patronymic(human.getPatronymic())
                .gender(human.getGender())
                .city_name(human.getCity_name())
                .yearOfBirth(human.getYearOfBirth())
                .inn(human.getInn())
                .city_id(human.getCity_id())
                .build();
    }

    private City fromCityDto(CityDto cityDto){
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .country_id(cityDto.getCountry_id())
                .residents(cityDto.getResidents())
                .capital(cityDto.isCapital())
                .build();
    }
}

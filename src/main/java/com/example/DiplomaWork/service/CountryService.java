package com.example.DiplomaWork.service;

import com.example.DiplomaWork.dao.CountryDao;
import com.example.DiplomaWork.domain.City;
import com.example.DiplomaWork.domain.Country;
import com.example.DiplomaWork.dto.CityDto;
import com.example.DiplomaWork.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryDao countryDao;

    @Transactional
    public void addCountry(CountryDto countryDto) throws SQLException {
        Country country = fromCountryDto(countryDto);
        countryDao.addCountry(country);
    }

    @Transactional
    public List<CountryDto> findAllCountry(){
        return countryDao.findAllCountry()
                .stream()
                .map(this::getBuildCountry)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(int id){countryDao.deleteById(id);}

    @Transactional
    public List<CityDto> getAllCityByCountryName(String country){
        return countryDao.getAllCityByCountryName(country)
                .stream()
                .map(city -> CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .country_id(city.getCountry_id())
                .residents(city.getResidents())
                .capital(city.isCapital())
                .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public CityDto getCapitalByCountryName(String country){
        City city = countryDao.getCapitalByCountryName(country);
        CityDto cityDto = getBuildCity(city);
        return cityDto;
    }

    private CityDto getBuildCity(City city){
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .country_id(city.getCountry_id())
                .residents(city.getResidents())
                .capital(city.isCapital())
                .build();
    }

    private CountryDto getBuildCountry(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .residents(country.getResidents())
                .continent(country.getContinent())
                .gdp(country.getGdp())
                .build();
    }

    private Country fromCountryDto(CountryDto countryDto){
        return Country.builder()
                .id(countryDto.getId())
                .name(countryDto.getName())
                .residents(countryDto.getResidents())
                .build();
    }
}

package com.example.DiplomaWork.service;

import com.example.DiplomaWork.dao.HumanDao;
import com.example.DiplomaWork.domain.Human;
import com.example.DiplomaWork.dto.HumanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HumanService {

    private final HumanDao humanDao;

    public List<HumanDto> findAllHuman(){
        return humanDao.findAllHuman()
                .stream()
                .map(this::getBuildHuman)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addHuman(HumanDto humanDto) throws SQLException {
        Human human = fromHumanDto(humanDto);
        humanDao.addHuman(human);
    }

    @Transactional
    public void deleteById(int id){humanDao.deleteById(id);}

    private HumanDto getBuildHuman(Human human) {
        return HumanDto.builder()
                .id(human.getId())
                .firstName(human.getFirstName())
                .lastName(human.getLastName())
                .patronymic(human.getPatronymic())
                .gender(human.getGender())
                .yearOfBirth(human.getYearOfBirth())
                .inn(human.getInn())
                .city_name(human.getCity_name())
                .build();
    }

    public Human fromHumanDto(HumanDto humanDto){
        Human human = new Human();
        human.setFirstName(humanDto.getFirstName());
        human.setLastName(humanDto.getLastName());
        human.setPatronymic(humanDto.getPatronymic());
        human.setGender(humanDto.getGender());
        human.setYearOfBirth(humanDto.getYearOfBirth());
        human.setInn(humanDto.getInn());
        human.setCity_id(humanDto.getCity_id());
        return human;
    }
}

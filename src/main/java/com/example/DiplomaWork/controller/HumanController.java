package com.example.DiplomaWork.controller;

import com.example.DiplomaWork.dto.HumanDto;
import com.example.DiplomaWork.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/human")
public class HumanController {

    private final HumanService humanService;

    @GetMapping
    public List<HumanDto> findAllHuman(){return humanService.findAllHuman();}

    @PostMapping
    public void addHuman(@RequestBody HumanDto humanDto) throws SQLException {
        humanService.addHuman(humanDto);
    }

    @DeleteMapping
    public void deleteById(@RequestParam int id){humanService.deleteById(id);}
}

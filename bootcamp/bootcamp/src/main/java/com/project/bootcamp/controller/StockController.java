package com.project.bootcamp.controller;
// Stock = Ações

import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock") //Direcionador

public class StockController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> save(@RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll(){
        List<StockDTO> list = new ArrayList<>();
        StockDTO dto = new StockDTO();
        dto.setId(1l);
        dto.setName("Magazine Luiza");
        dto.setPrice(10D);
        dto.setVariation(10D);
        dto.setDate(LocalDate.now());
        return ResponseEntity.ok(list);

    }




}

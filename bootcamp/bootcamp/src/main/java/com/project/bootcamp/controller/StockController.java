package com.project.bootcamp.controller;
// Stock = Ações

import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.service.StockService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock") //Direcionador

public class StockController {

    @Autowired
    private StockService service;

    // Criando stock
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(service.save(dto));

    }

    // Alterar stock
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);

    }

    // Selecionar stock
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll(){
        List<StockDTO> list = new ArrayList<>();
        StockDTO stock1 = new StockDTO();
        stock1.setId(1l);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(10D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());
        list.add(stock1);
        return ResponseEntity.ok(list);

    }

    // Selecionar stock por ID
    @GetMapping(value = "/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id){
        List<StockDTO> list = new ArrayList<>();
        StockDTO stock1 = new StockDTO();
        stock1.setId(1l);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(10D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());
        StockDTO stock2 = new StockDTO();
        stock2.setId(2l);
        stock2.setName("WEG");
        stock2.setPrice(20D);
        stock2.setVariation(5D);
        stock2.setDate(LocalDate.now());
        list.add(stock1);
        list.add(stock2);

        StockDTO dtoSelect = list.stream().filter(x -> x.getId().compareTo(id) == 0).findFirst().get();
        return ResponseEntity.ok(dtoSelect);

    }



}

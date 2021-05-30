package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    // Criando conexão com o Repository
    @Autowired
    private StockRepository repository;

    // Transformando o dto em uma entidade
    @Autowired
    private StockMapper mapper;



    @Transactional // Contola a transação
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate()); // Verificando que existe o registro na base

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);

        }

        Stock stock = mapper.toEntify(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }
}

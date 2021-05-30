package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

@Service
public class StockService {

    // Criando conexão com o Repository
    @Autowired
    private StockRepository repository;

    // Transformando o dto em uma entidade
    @Autowired
    private StockMapper mapper;

    //Salvando stock
    @Transactional // Contola a transação
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate()); // Verificando que existe o registro na base

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    // Atualizando stock
    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId()); // Verificando que existe o registro na base

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);

        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);

    }

    // Deletando por ID
    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(id);
        return dto;
    }

    // Retornando a lista
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<StockDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    //Retornando por ID
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    // Buscando as ações do dia
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
}

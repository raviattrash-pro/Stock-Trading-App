package com.stocks.stocks_Exchange_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocks_Exchange_model.Stock;
import com.stocks.stocks_Exchange_repository.StockRepository;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired private StockRepository stockRepository;

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Optional: Admin-only - initialize stock data
    @PostConstruct
    public void initStocks() {
        if (stockRepository.count() == 0) {
            stockRepository.saveAll(List.of(
                new Stock("TCS", "Tata Consultancy Services", 3600.0),
                new Stock("INFY", "Infosys", 1550.0),
                new Stock("RELIANCE", "Reliance Industries", 2750.0),
                new Stock("ITC", "ITC Limited", 450.0)
            ));
        }
    }
}

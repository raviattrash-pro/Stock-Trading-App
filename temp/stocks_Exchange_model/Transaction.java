package com.stocks.stocks_Exchange_model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id 
    /*@GeneratedValue*/
    private Long id;

    private String symbol;
    private String type; // BUY or SELL
    private Integer quantity;
    private Double price;
    private LocalDate timestamp;

    @ManyToOne
    private User user;
}


package com.stocks.stocks_Exchange_controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocks_Exchange_model.Stock;
import com.stocks.stocks_Exchange_model.Transaction;
import com.stocks.stocks_Exchange_model.User;
import com.stocks.stocks_Exchange_repository.StockRepository;
import com.stocks.stocks_Exchange_repository.TransactionRepository;
import com.stocks.stocks_Exchange_repository.UserRepository;
import com.stocks.stocks_Exchange_security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private StockRepository stockRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository transactionRepo;
    @Autowired private JwtUtil jwtUtil;

    private User getUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(token);
        return userRepo.findByUsername(username).orElseThrow();
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestParam String symbol, @RequestParam int quantity, HttpServletRequest request) {
        User user = getUserFromRequest(request);
        Stock stock = stockRepo.findById(symbol).orElseThrow();

        double totalCost = stock.getPrice() * quantity;

        if (user.getBalance() < totalCost) {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }

        user.setBalance(user.getBalance() - totalCost);
        //user.getPortfolio().merge(symbol, quantity, Integer::sum);

        Transaction tx = new Transaction();
        transactionRepo.save(tx);
        userRepo.save(user);

        return ResponseEntity.ok("Bought " + quantity + " shares of " + symbol);
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestParam String symbol, @RequestParam int quantity, HttpServletRequest request) {
        User user = getUserFromRequest(request);
        Stock stock = stockRepo.findById(symbol).orElseThrow();

        /*int owned = user.getPortfolio().getOrDefault(symbol, 0);
        if (owned < quantity) {
            return ResponseEntity.badRequest().body("Not enough shares to sell");
        }*/

        /*user.setBalance(user.getBalance() + stock.getPrice() * quantity);
        user.getPortfolio().put(symbol, owned - quantity);*/

        Transaction tx = new Transaction();
        transactionRepo.save(tx);
        userRepo.save(user);

        return ResponseEntity.ok("Sold " + quantity + " shares of " + symbol);
    }
}


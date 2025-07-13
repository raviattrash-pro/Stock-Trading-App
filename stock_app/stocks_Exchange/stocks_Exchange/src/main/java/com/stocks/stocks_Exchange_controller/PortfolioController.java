package com.stocks.stocks_Exchange_controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocks_Exchange_model.Transaction;
import com.stocks.stocks_Exchange_model.User;
import com.stocks.stocks_Exchange_repository.TransactionRepository;
import com.stocks.stocks_Exchange_repository.UserRepository;
import com.stocks.stocks_Exchange_security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository transactionRepo;

    private User getUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(token);
        return userRepo.findByUsername(username).orElseThrow();
    }

    @GetMapping("/portfolio")
    public Map<String, Object> viewPortfolio(HttpServletRequest request) {
        User user = getUserFromRequest(request);

        Map<String, Object> response = new HashMap<>();
        response.put("balance", user.getBalance());
        response.put("portfolio", user.getPortfolio());
        return response;
    }

    @GetMapping("/history")
    public List<Transaction> viewHistory(HttpServletRequest request) {
        User user = getUserFromRequest(request);
        return transactionRepo.findByUser(user);
    }
}


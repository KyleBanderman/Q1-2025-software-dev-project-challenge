package org.launchcode.backend.controllers;

import org.launchcode.backend.models.Transaction;
import org.launchcode.backend.models.User;
import org.launchcode.backend.models.data.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.launchcode.backend.models.data.UserRepository;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired
    public TransactionController (TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> returnUsersTransactions (Authentication authentication) {
        String username = authentication.getName();
        User user;
        LinkedList<Transaction> transactionList = new LinkedList<>();
        if (userRepository.findByUsername(username).isPresent()) {
            user = userRepository.findByUsername(username).get();
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<Transaction> allTransactions = transactionRepository.findAll();
        allTransactions.forEach(item -> {
            if (item.getUser().getId() == user.getId()) {
                transactionList.add(item);
            }
        });
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    //POST /transactions creates a new transaction (income or expense)
    // GET /transactions/:id retrieves a specific transaction by id
    //PUT /transactions/:id updates an existing transaction
    //DELETE /transactions/:id deletes a transaction by id

}

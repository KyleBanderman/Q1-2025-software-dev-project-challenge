package org.launchcode.backend.controllers;

import org.launchcode.backend.models.Transaction;
import org.launchcode.backend.models.User;
import org.launchcode.backend.models.data.TransactionRepository;
import org.launchcode.backend.models.dto.TransactionDTO;
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

    @PostMapping("/transactions")
    public ResponseEntity<String> createTransaction (Authentication authentication, TransactionDTO transactionDTO) {
        String username = authentication.getName();
        if (!username.equals(transactionDTO.getUser())) {
            return new ResponseEntity<>("Users do not match", HttpStatus.BAD_REQUEST);
        }

        User user;
        if (userRepository.findByUsername(username).isPresent()) {
            user = userRepository.findByUsername(username).get();
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        User transactionUser = userRepository.findByUsername(transactionDTO.getUser()).get();
        Double transactionAmount = transactionDTO.getAmount();
        Transaction transaction = new Transaction (transactionUser, transactionAmount);
        transactionRepository.save(transaction);
        return new ResponseEntity<>("Transaction successfully created", HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransactionById (@PathVariable int id, Authentication authentication) {
        Transaction transaction = transactionRepository.findById(id).get();
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }


    //PUT /transactions/:id updates an existing transaction
    //DELETE /transactions/:id deletes a transaction by id

}

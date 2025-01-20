package org.launchcode.backend.controllers;

import org.launchcode.backend.models.data.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public ResponseEntity<String> returnUsersTransactions ()
    //GET /transactions returns all transactions belonging to user
    //POST /transactions creates a new transaction (income or expense)
    // GET /transactions/:id retrieves a specific transaction by id
    //PUT /transactions/:id updates an existing transaction
    //DELETE /transactions/:id deletes a transaction by id

}

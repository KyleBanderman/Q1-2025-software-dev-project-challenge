package org.launchcode.backend.models;

import jakarta.persistence.*;


@Entity
public class Transaction {

    enum TransactionType {
        INCOME,
        EXPENSE
    }

    @ManyToOne
    private User user;

    private Double amount;

    private TransactionType transactionType;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;


    public Transaction(User user, Double amount, int id) {
        this.user = user;
        this.amount = amount;
        this.id = id;
        if (this.amount < 0) {
            transactionType = TransactionType.EXPENSE;
        }
        else if (this.amount > 0) {
            transactionType = TransactionType.INCOME;
        }
        else {
            transactionType = null;
        }
        this.amount = Math.abs(amount);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getId() {
        return id;
    }
}

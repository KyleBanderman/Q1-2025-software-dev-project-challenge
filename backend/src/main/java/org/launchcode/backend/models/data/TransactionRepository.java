package org.launchcode.backend.models.data;

import org.launchcode.backend.models.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
}

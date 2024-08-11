package org.szekai.paymentvalidationsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.szekai.paymentvalidationsimple.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

package com.Bankomat.Bankomat.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Bankomat.Bankomat.Entites.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
}

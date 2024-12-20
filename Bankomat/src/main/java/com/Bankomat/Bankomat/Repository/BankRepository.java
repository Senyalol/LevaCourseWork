package com.Bankomat.Bankomat.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Bankomat.Bankomat.Entites.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    Bank findByName(String name);
}

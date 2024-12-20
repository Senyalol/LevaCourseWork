package com.Bankomat.Bankomat.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Bankomat.Bankomat.Entites.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Integer> {
    Atm findById(int id);
}
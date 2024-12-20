package com.Bankomat.Bankomat.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Bankomat.Bankomat.Entites.User;

@Repository
public interface UserRepostiory extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

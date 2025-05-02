package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("FROM account WHERE username = :usernameVar")
    Optional<Account> findAccount(@Param("usernameVar") String username);

    @Query("FROM account WHERE username = :usernameVar AND password = :passwordVar")
    Optional<Account> login(@Param("usernameVar") String username, @Param("passwordVar") String password);
}

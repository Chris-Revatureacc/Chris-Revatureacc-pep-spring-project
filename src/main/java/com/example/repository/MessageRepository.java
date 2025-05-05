package com.example.repository;

import com.example.entity.Account;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("FROM Account WHERE accountId = :accountIdVar")
    Optional<Account> findAccount(@Param("accountIdVar") Integer accountId);

    @Query("FROM Message WHERE postedBy = :postedByVar")
    List<Message> findMessages(@Param("postedByVar") Long postedBy);
}

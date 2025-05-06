package com.example.repository;

import com.example.entity.Account;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("FROM Account WHERE accountId = :accountIdVar")
    Optional<Account> findAccount(@Param("accountIdVar") Integer accountId);

    @Query("FROM Message WHERE postedBy = :postedByVar")
    List<Message> findMessages(@Param("postedByVar") Integer postedBy);

    @Query("FROM Message WHERE messageId =:messageIdVar")
    Optional<Message> findById(@Param("messageIdVar") Integer messageId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Message WHERE messageId =:messageIdVar")
    int deleteMessageById(@Param("messageIdVar") Integer messageId);
}

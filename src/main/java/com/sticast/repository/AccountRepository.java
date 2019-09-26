package com.sticast.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Account;

@Repository("AccountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
    Account findOneById(Integer accountID);
    
    @Modifying
    @Query(value = "UPDATE account SET balance = balance + :quantity WHERE id = :winner", nativeQuery = true)
    void payWinner(@Param("winner") Integer accountID, @Param("quantity") Integer quantity);
	
    @Query(value = "SELECT COALESCE(sum(quantity), 0) FROM forecast where question_id = :question AND account_id = :account AND answer = :answer", nativeQuery = true)
    Integer getShareQuantity(@Param("account") Integer accountID, @Param("question") Integer questionID, @Param("answer") String answer);
}
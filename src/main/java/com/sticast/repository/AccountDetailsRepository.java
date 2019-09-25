package com.sticast.repository;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.AccountDetails;

@Repository("AccountDetailsRepository")
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer>{

	Optional<AccountDetails> findByAccountIdAndQuestionId(Integer accountID, Integer questionID);
	ArrayList<AccountDetails> findAllByAccountIdAndIsFollowed(Integer accountID, Integer isFollowed);
}

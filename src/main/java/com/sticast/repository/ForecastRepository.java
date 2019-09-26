package com.sticast.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Forecast;
import com.sticast.entity.Winner;

@Repository("ForecastRepository")
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {
	
    ArrayList<Forecast> findAllByAccountId(Integer accountID);
    
    @Query(value = "SELECT sum(quantity) as Q, account_id as A FROM forecast WHERE question_id= :question_id AND answer = :answer GROUP BY account_id", nativeQuery = true)
    List<Winner> getWinners(@Param("question_id") Integer question, @Param("answer") String answer);
}

package com.sticast.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Question;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	 ArrayList<Question> findAll();
	 ArrayList<Question> findByCategoryName(String category);
}

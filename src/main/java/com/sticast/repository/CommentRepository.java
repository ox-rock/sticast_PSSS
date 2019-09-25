package com.sticast.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Comment;

@Repository("CommentRepository")
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	 ArrayList<Comment> findAllByQuestionId(Integer questionID);
}

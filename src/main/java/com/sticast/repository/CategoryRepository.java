package com.sticast.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Category;

@Repository("CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	ArrayList<Category> findAll();
	
}

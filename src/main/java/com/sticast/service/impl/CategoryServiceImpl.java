package com.sticast.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Category;
import com.sticast.repository.CategoryRepository;
import com.sticast.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
    @Autowired
	CategoryRepository categoryRepository;

	@Override
	public ArrayList<Category> getAllCategories(){		
		return categoryRepository.findAll();	
	}
}
package com.sticast.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.sticast.entity.Category;

@Service
public interface CategoryService {

	public ArrayList<Category> getAllCategories();
}

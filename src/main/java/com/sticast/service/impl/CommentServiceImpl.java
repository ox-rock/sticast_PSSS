package com.sticast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sticast.entity.Comment;
import com.sticast.repository.CommentRepository;
import com.sticast.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Transactional
	@Override
	public void saveComment(Comment comment) {
		commentRepository.save(comment);	
	}

}
package com.sticast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.entity.Comment;
import com.sticast.service.ServiceFacade;

@Controller
public class CommentController {
	
	@Autowired
    ServiceFacade serviceFacade;
	
	@RequestMapping(value = "/question/{questionID}/comments", method = RequestMethod.POST)
	public String postComment(@ModelAttribute("comment") Comment comment, @PathVariable Integer questionID) {   
		serviceFacade.saveComment(comment);
		return "redirect:/question/"+questionID ;	  
	}
}
package com.sticast.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.model.Comment;
import com.sticast.model.Question;

@Controller
public class CommentController {
	
	  @RequestMapping(value = "/question/{id}/comments", method = RequestMethod.GET)
	  public String showComments(Model model, @PathVariable Integer id) {
	      Question question = new Question();
		  question.getAllComments(id);
		  ArrayList<Comment> commentsList = question.getCommentsList();  
	      model.addAttribute("comment", commentsList);
	      
		  return "question";
	  }
	  
	  @RequestMapping(value = "/question/{questionID}/comments", method = RequestMethod.POST)
	  public String postComment(Model model, @PathVariable Integer questionID, HttpServletRequest request) {
	      HttpSession session = request.getSession(false);
		  Integer accountID = (Integer)session.getAttribute("accountID");
	
		  String text = request.getParameter("addComment");
		  Comment comment = new Comment();
		  comment.saveComment(questionID, accountID, text);
			
		  return "redirect:/question/"+questionID ;	  
	   }
}
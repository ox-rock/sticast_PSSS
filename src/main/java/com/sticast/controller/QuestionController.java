package com.sticast.controller;

import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.entity.Category;
import com.sticast.entity.Comment;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.service.ServiceFacade;

@Controller
public class QuestionController {
	
	@Autowired
    ServiceFacade serviceFacade;

    @RequestMapping(value = "/questions/{category}",  method = { RequestMethod.GET, RequestMethod.POST })
    public String showQuestionsList(Model model, @PathVariable String category,
	    HttpServletRequest request) {
	
        HttpSession session = request.getSession(false);			
	    Integer accountID = (Integer)session.getAttribute("accountID");
	    if(accountID == null)
		    return "login";
	
	    ArrayList<Question> questionsList = serviceFacade.getAllQuestions(category);
	    ArrayList<Category> categoriesList = serviceFacade.getAllCategories();
	  	    
	    model.addAttribute("categoriesList", categoriesList);
	    model.addAttribute("questionsList",questionsList);
	    model.addAttribute("category", category);
	
	    return "questionslist";
    }

    @RequestMapping(value = "/question/{questionID}",  method = RequestMethod.GET)
    public String showQuestion(Model model, @PathVariable("questionID") Integer questionID,
	    HttpServletRequest request, @ModelAttribute("forecast") Forecast forecast, @ModelAttribute("comment") Comment comment) {
	
		HttpSession session = request.getSession(false);			
		Integer accountID = (Integer)session.getAttribute("accountID");
		if(accountID == null)
			return "login";

		Optional<Question> question = serviceFacade.getQuestion(questionID);
		if (question.isEmpty()) 
			return "redirect:/questions/all";
		
		Integer yesQuantity = serviceFacade.getShareQuantity(accountID, questionID, "yes");
		Integer noQuantity = serviceFacade.getShareQuantity(accountID, questionID, "no");
		Double yesShareValue = serviceFacade.calculateShareValue(question.get());
		
		model.addAttribute("question",question);
		model.addAttribute("yesQuantity", yesQuantity);	
		model.addAttribute("noQuantity", noQuantity);	
		model.addAttribute("yesValue", yesShareValue);
		model.addAttribute("noValue", 1 - yesShareValue);
	    model.addAttribute("commentsList", question.get().getCommenstList());	

	    return "question";	  
    }
    
    @RequestMapping(value = "/question/{questionID}/comments", method = RequestMethod.POST)
	public String postComment(@ModelAttribute("comment") Comment comment, @PathVariable Integer questionID) {   
		serviceFacade.saveComment(comment);
		return "redirect:/question/"+questionID ;	  
	}
    
    @RequestMapping(value = "/question/{questionID}/close",  method = RequestMethod.GET)
    public String closeQuestion(Model model, @PathVariable Integer questionID,
	    HttpServletRequest request) {	
    	serviceFacade.closeQuestion(questionID, "yes");	
		return "redirect:/questions/all";	
    }
    
}
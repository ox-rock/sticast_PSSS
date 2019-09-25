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
import org.springframework.web.bind.annotation.RequestParam;
import com.sticast.entity.AccountDetails;
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
		model.addAttribute("question",question);
		
		AccountDetails accountDetails = serviceFacade.getAccountDetails(accountID, questionID);
		model.addAttribute("accountDetails", accountDetails);
	    model.addAttribute("commentsList", question.get().getCommenstList());	

	    return "question";	  
    }
   /* 
    @RequestMapping(value = "/question/{questionID}/close",  method = RequestMethod.POST)
    public String closeQuestion(Model model, @PathVariable Integer questionID,
	    HttpServletRequest request) {
	
    	String winnerType = request.getParameter("winnerType");
		
		//ArrayList<Share> playersList = new ArrayList<Share>();
		QuestionServiceImpl question = new QuestionServiceImpl();
		question.getAllPlayers(questionID);
		playersList = question.getAllPlayers();
		
		for(int i=0; i < playersList.size(); i++){
			Integer accountID = playersList.get(i).getAccount_id();
			Integer yesShareQuantity = playersList.get(i).getYesShareQuantity();
			Integer noShareQuantity = playersList.get(i).getNoShareQuantity();
			payWinners(accountID, yesShareQuantity, noShareQuantity, winnerType);
		}
		
		question.closeQuestion(questionID);
	
		return "redirect:questions/all";
    	
    }
    
    private void payWinners(Integer accountID, Integer yesShareQuantity, Integer noShareQuantity, String winnerType) {
		AccountServiceImpl account = new AccountServiceImpl(null, accountID);
		Double newBudget;
		
		if(winnerType.equals("yes")) 
		    newBudget = account.getBudget() + yesShareQuantity;    		
		else newBudget = account.getBudget() + noShareQuantity;    
		
		AccountServiceImpl acc = new AccountServiceImpl();
		acc.updateBudget(accountID,newBudget);
	}
    
    */
    
    @RequestMapping(value = "/following", method = RequestMethod.GET)
	public String showFollowsList(Model model, HttpServletRequest request) {	  
       
    	HttpSession session = request.getSession(false);	
		Integer accountID = (Integer)session.getAttribute("accountID");
		if(accountID == null)
			return "login"; 	
		
		ArrayList<AccountDetails> followList = serviceFacade.getFollowList(accountID); 
		model.addAttribute("followList", followList);
	    return "following"; 
	}
	  
	@RequestMapping(value = "/following", method = RequestMethod.POST)
	public void followQuestion(Model model, HttpServletRequest request, 
		@RequestParam("type") String requestType, @RequestParam("questionID") Integer questionID) {
		
        HttpSession session = request.getSession(false);			
        Integer accountID = (Integer)session.getAttribute("accountID");
		
        serviceFacade.followOrUnfollowQuestion(accountID, questionID, requestType);    		
    }    
}
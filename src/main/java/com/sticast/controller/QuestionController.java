package com.sticast.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.model.Share;
import com.sticast.model.Account;
import com.sticast.model.Category;
import com.sticast.model.Question;
import com.sticast.model.Follow;

@Controller
public class QuestionController {
	
    @RequestMapping(value = "/questions/{category}",  method = { RequestMethod.GET, RequestMethod.POST })
    public String showQuestionsList(Model model, @PathVariable String category,
	    HttpServletRequest request) {
	
        HttpSession session = request.getSession(false);			
	    Integer accountID = (Integer)session.getAttribute("accountID");
	    if(accountID == null)
		    return "login";
	
	    ArrayList<Question> questionsList = new ArrayList<Question>();
	    ArrayList<Category> categoriesList = new ArrayList<Category>();
    
	    if(category.equals("all")) {
	    	Category domande = new Category();
	    	domande.getAllQuestions();
	    	questionsList = domande.getQuestionsList();
	    	categoriesList = domande.getAllCategories();
	    } 
	    else {                    
	    	Category domande = new Category();
	    	domande.getAllQuestions(category);
	    	questionsList = domande.getQuestionsList();
	    	categoriesList = domande.getAllCategories();
	    } 
  
	    model.addAttribute("categoriesList", categoriesList);
	    model.addAttribute("questionsList",questionsList);
	    model.addAttribute("category", category);
	
	    return "questionslist";
    }

    @RequestMapping(value = "/question/{questionID}",  method = RequestMethod.GET)
    public String showQuestion(Model model, @PathVariable Integer questionID,
	    HttpServletRequest request) {
	
    	//Get question's ID, user's budget and user's ID from request and session
		HttpSession session = request.getSession(false);			
		Integer accountID = (Integer)session.getAttribute("accountID");
		if(accountID == null)
			return "login";

		//Check if this question is followed, then passes the result to front-end
		Follow follow = new Follow(accountID,questionID);
		Boolean result = follow.checkFollow();			
		if (result == true) 
			model.addAttribute("isFollowed", 1);
		else model.addAttribute("isFollowed", 0);
				
		//Pass some parameters to front-end
		Question question = new Question(questionID);
		Share share = new Share(accountID,questionID);
		model.addAttribute("yes_share_user",share.getYesShareQuantity());
		model.addAttribute("no_share_user",share.getNoShareQuantity());
		model.addAttribute("isOpen", question.getIsOpen());
		model.addAttribute("question", question.getQuestion());
		model.addAttribute("yesValue", question.getYesValue());
		model.addAttribute("noValue", question.getNoValue());
		model.addAttribute("yesQuantity", question.getYesShareQuantity());
		model.addAttribute("noQuantity", question.getNoShareQuantity());
		model.addAttribute("questionID", questionID);
			
	    return "forward:/question/" + questionID + "/comments"; 
    }
    
    
    @RequestMapping(value = "/question/{questionID}/close",  method = RequestMethod.POST)
    public String closeQuestion(Model model, @PathVariable Integer questionID,
	    HttpServletRequest request) {
	
    	String winnerType = request.getParameter("winnerType");
		
		ArrayList<Share> playersList = new ArrayList<Share>();
		Question question = new Question();
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
		Account account = new Account(null, accountID);
		Double newBudget;
		
		if(winnerType.equals("yes")) 
		    newBudget = account.getBudget() + yesShareQuantity;    		
		else newBudget = account.getBudget() + noShareQuantity;    
		
		Account acc = new Account();
		acc.updateBudget(accountID,newBudget);
	}
}
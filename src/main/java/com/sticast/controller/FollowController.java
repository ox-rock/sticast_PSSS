package com.sticast.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.model.Account;
import com.sticast.model.Follow;

@Controller
public class FollowController {

    @RequestMapping(value = "/following", method = RequestMethod.GET)
	public String showFollow(Model model, HttpServletRequest request) {
		  
        HttpSession session = request.getSession(false);			
		Integer accountID = (Integer)session.getAttribute("accountID");
		if(accountID == null)
			return "login";
		Account account = new Account();
		account.getAllFollow(accountID);
		ArrayList<Follow> followList = account.getFollowList();  
		model.addAttribute("followList", followList);
	    return "following"; 
	}
	  
	@RequestMapping(value = "/following", method = RequestMethod.POST)
	public void followQuestion(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);			
		Integer accountID = (Integer)session.getAttribute("accountID");
		String requestType = request.getParameter("type");
		String s_questionID = request.getParameter("questionID");
		Integer questionID = Integer.parseInt(s_questionID);
		Follow follow = new Follow(accountID,questionID);
			
		if(requestType.equals("follow"))
		    follow.saveFollow();
		else follow.deleteFollow();	  
    }
}
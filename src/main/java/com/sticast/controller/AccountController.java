package com.sticast.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.entity.Account;
import com.sticast.service.ServiceFacade;

@Controller
public class AccountController {
	
	@Autowired
    ServiceFacade serviceFacade;
	
	/**************************
	          Login            		
	***************************/
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
	       return "login";
	}
	  
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, 
	     @ModelAttribute("username") Account account, HttpServletRequest request) {
	 
		 Optional<Account> theAccount = serviceFacade.getAccountByUsername(account.getUsername());
		 // theAccount = serviceFacade.getAccountByUsername(account.getUsername());

		 if(theAccount.isEmpty()) {
			 System.out.println("[AccountController] ERROR: Wrong username!");			
			 model.addAttribute("LoginResult", "wrongUsername");
	    	 return "login";
		 } 
		 else if(!theAccount.get().getPassword().equals(account.getPassword())) {
			 System.out.println("[AccountController] ERROR: Wrong password!");			
		     model.addAttribute("LoginResult", "wrongPassword");
		     return "login";	 
		 }
		 else {
			 HttpSession session = request.getSession();
			 session.setAttribute("username", theAccount.get().getUsername());
			 session.setAttribute("userBudget",theAccount.get().getBudget());
			 session.setAttribute("accountID", theAccount.get().getId());
			 session.setAttribute("isAdmin", theAccount.get().getIsAdmin());  	       
			 return "redirect:questions/all";
		 }
	}
	  
	/*****************************
   	 * Registration              *        																		     
	 *****************************/
  
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationPage() {
	     return "register";
	}

	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	  public String doRegistration(Model model, @ModelAttribute("account") Account account, HttpServletRequest request){
		   try { 
			   serviceFacade.SaveAccount(account);	
 	       } 
		   catch(DataIntegrityViolationException ex) {
			   System.out.println("[AccountController] ERROR: Username or Email already taken!");
			   model.addAttribute("registrationCheck","error");
			   return "register";
 	       }
		 
	      System.out.println("[AccountController] OK: Account successfully created!");
	      Optional<Account> theAccount = serviceFacade.getAccountByUsername(account.getUsername());
	            
	      HttpSession session = request.getSession();
	      session.setAttribute("username", account.getUsername());
	      session.setAttribute("accountID", theAccount.get().getId());
	      session.setAttribute("userBudget", 100.00);
	      return "redirect:questions/all";
	  }	
	  
	  
	  /***************************
   	   * Logout                  *
	   ***************************/
	
	  @RequestMapping(value = "/logout", method = RequestMethod.GET)
	  public String doLogout(HttpServletRequest request) {
	       HttpSession session=request.getSession();
		   if(session.getAttribute("username") != null){
		        String session_username = (String) session.getAttribute("username");
				session.removeAttribute("username");
				session.invalidate();
		    	System.out.println("[AccountController] OK: "+ session_username +"'s session successfully destroyed!");
		   }
		   else System.out.println("[AccountController] WARNING: session not found!");
           return "index";
	  }
	  
	  /****************************
   	   * Profile                  *       
	  *****************************/
	  
	  @RequestMapping(value = "/profile", method = RequestMethod.GET)
	  public String showProfile(Model model, HttpServletRequest request, @ModelAttribute("account") Account account) {	     
	       HttpSession session = request.getSession(false);
		   Integer accountID = (Integer)session.getAttribute("accountID");
		   if(accountID == null)
		        return "login";
			
		   account = serviceFacade.getAccountById(accountID);
		   model.addAttribute("account", account);
			
		   return "profile";
	  }
	  
	  @RequestMapping(value = "/profile", method = RequestMethod.POST)
	  public String editProfile(Model model, HttpServletRequest request, @ModelAttribute("account") Account account) throws ServletException, IOException{
 	       HttpSession session = request.getSession(false);    
 	       try { 
 	    	  serviceFacade.SaveAccount(account);	
 	       } 
 	       catch(DataIntegrityViolationException ex) {
 	    	   model.addAttribute("editProfileCheck", "error");
 	    	   return "profile";
 	       }	  
 	       session.setAttribute("username", account.getUsername());	
		   model.addAttribute("editProfileCheck", "success");
		   return "profile";
	  }
}

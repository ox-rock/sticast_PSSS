package com.sticast.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;	
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sticast.model.Account;

@Controller
public class AccountController {
	
	 /***************************
	  * Login            		*
	  ***************************/
	
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public String showLoginPage() {
	       return "login";
	  }
	  
	  @RequestMapping(value = "/login", method = RequestMethod.POST)
	  public String doLogin(Model model, 
	       @ModelAttribute("username") Account account, 
	       HttpServletRequest request) {
		 
		   String loginCheck = account.checkAccount();
		  
		   if(loginCheck.equals("wrongUsername")) {
		        System.out.println("[AccountController] ERROR: Wrong username!");			
			    model.addAttribute("LoginResult", "wrongUsername");
		        return "login";
		   } 
	       else if(loginCheck.equals("wrongPassword")) {
			    System.out.println("[AccountController] ERROR: Wrong password!");			
			    model.addAttribute("LoginResult", "wrongPassword");
		        return "login";
		   } 
		   else {			 
			    System.out.println("[AccountController] OK: user '"+ account.getUsername() +"' successfully logged in!");	
			    HttpSession session = request.getSession();
			    session.setAttribute("username", account.getUsername());
			    session.setAttribute("userBudget", account.getBudget());
			    session.setAttribute("accountID", account.getId());
			    session.setAttribute("isAdmin", account.getIsAdmin());  	    
			    return "redirect:questions/all";
	        }  	  
	   }
	  
	  /*****************************
   	   * Registration              *        																		     *
	   *****************************/
	  
	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public String showRegistrationPage() {
	       return "register";
	  }

	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	  public String doRegistration(Model model, 
	       @ModelAttribute("account") Account account, 
	       HttpServletRequest request) {

		   Account acc2 = new Account(account.getUsername(),account.getPassword(), account.getName(), account.getEmail());			
		 
		   if (acc2.saveAccount() == 0) {
			    System.out.println("[AccountController] ERROR: Username or Email already taken!");
			    request.setAttribute("registrationCheck","error");
			    return "register";
	       }
	       else {
	    	    System.out.println("[AccountController] OK: Account successfully created!");
	    	    acc2.checkAccount();
	    	    HttpSession session = request.getSession();
	            session.setAttribute("username", account.getUsername());
	            session.setAttribute("accountID", acc2.getId());
	            session.setAttribute("userBudget", 100.00);
	            return "redirect:questions/all";
	       } 	
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
	  public String showProfile(Model model, HttpServletRequest request) {	     
	       HttpSession session = request.getSession(false);
		   Integer userID = (Integer)session.getAttribute("accountID");
		   if(userID == null)
		        return "login";
			
		   Account acc = new Account(null, userID);
		   model.addAttribute("username", acc.getUsername());
		   model.addAttribute("password", acc.getPassword());
		   model.addAttribute("name", acc.getName());
		   model.addAttribute("email", acc.getEmail());			
		   return "profile";
	  }
	  
	  @RequestMapping(value = "/profile", method = RequestMethod.POST)
	  public void editProfile(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		  
 	       HttpSession session = request.getSession(false);
		   Integer id = (Integer)session.getAttribute("accountID");
		   String newUsername = request.getParameter("newUsername");
		   String newPassword = request.getParameter("newPassword");
		   String newName = request.getParameter("newName");
		   String newEmail = request.getParameter("newEmail");
		   String result;
		   Account acc = new Account(newUsername, newPassword, newName, newEmail);
		   PrintWriter out = response.getWriter();
			
		   if(acc.updateAccount(id) == 0) 
		        result = "error";
		   else {
				result="ok";
				session.setAttribute("username", newUsername);	
		   }
	       out.write(result);
	  }
	 
}
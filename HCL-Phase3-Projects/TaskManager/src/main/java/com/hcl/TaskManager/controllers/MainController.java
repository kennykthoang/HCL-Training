package com.hcl.TaskManager.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.TaskManager.entities.User;
import com.hcl.TaskManager.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
    @GetMapping("/")
    public String defaultPage() {
    	return "index";
    }
    
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String submitLogin(@RequestParam String email, @RequestParam String password, ModelMap model){
    	
        //TODO:
    	if(!userService.validateEmptyInput(email) || !userService.validateEmptyInput(password))
    	{
    		model.addAttribute("message", "422 - Unprocessable Entity");
    		return "error";
    	}
    	
    	if(!userService.validateCredentials(email, password))
    	{
    		model.addAttribute("message", "404 - Not Found");
    		return "error";
    	}
    	
    	return "mainmenu";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/";
    }
    
    @GetMapping("/mainmenu")
    public String showMainMenu()
    {
    	return "mainmenu";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register()
    {
		return "register";
    	
    }
    
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView submitRegistration(@RequestParam String name, @RequestParam String username,
    		 @RequestParam String password)
    {
    	if(!userService.validateEmptyInput(name) || !userService.validateEmptyInput(username)
    			|| !userService.validateEmptyInput(password))
        {    
	        return new ModelAndView("error", "message", "422 - Unprocessable Entity");
        }
    	
    	if(userService.existsByUserName(username))
    	{
    		return new ModelAndView("error", "message", "Username already exists in system");
    	}
    	
    	User user = new User(name, username, password);
    	userService.updateUser(user);
    	return new ModelAndView("registersuccess", "user", user);
    }
}

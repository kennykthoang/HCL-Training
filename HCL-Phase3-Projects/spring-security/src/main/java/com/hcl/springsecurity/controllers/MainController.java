package com.hcl.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.springsecurity.entities.User;
import com.hcl.springsecurity.services.UserService;

@Controller

public class MainController {
	
	
	@Autowired
	private UserService userService;
	
    @RequestMapping(value="/", method = RequestMethod.GET)

    public String defaultPage() {
    	return "index";
    }
    
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home() {
    	return "home";
    }

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello() {
    	return "hello";
    }
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public String users() {
    	return "users";
    }
    
    @RequestMapping(value="/error", method = RequestMethod.GET)
    public String error() {
    	return "error";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register()
    {
		return "register";
    	
    }
    
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView submitRegistration(@RequestParam String username,
    		 @RequestParam String password)
    {
    	if(!userService.validateEmptyInput(username)
    			|| !userService.validateEmptyInput(password))
        {    
	        return new ModelAndView("error", "message", "422 - Unprocessable Entity");
        }
    	
    	User user = new User(username, password);
    	userService.updateUser(user);
    	return new ModelAndView("registersuccess", "user", user);
    }
}

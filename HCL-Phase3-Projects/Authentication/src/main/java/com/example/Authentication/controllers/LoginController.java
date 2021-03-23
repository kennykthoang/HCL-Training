package com.example.Authentication.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.Authentication.entities.User;
import com.example.Authentication.services.UserService;


@Controller
public class LoginController {

	@Autowired
	private UserService userService;

    @GetMapping("/")
    public String showGreeting() {
        return "greeting";
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
    	
    	return "loginsuccess";	 
    }
    
    @GetMapping("/register")
    public String showRegistration()
    {
    	return "register";
    }
    
    @PostMapping("/register")
    public ModelAndView submitRegistration(@RequestParam String name,
    		@RequestParam String email, @RequestParam String password)
    {
    	if(!userService.validateEmptyInput(name) || !userService.validateEmptyInput(email)
    			|| !userService.validateEmptyInput(password))
        {    
	        return new ModelAndView("error", "message", "422 - Unprocessable Entity");
        }
    	
    	User user = new User();
    	user.setName(name);
    	user.setEmail(email);
    	user.setPassword(password);
    	userService.updateUser(user);
    	return new ModelAndView("registersuccess", "user", user);
    }
}

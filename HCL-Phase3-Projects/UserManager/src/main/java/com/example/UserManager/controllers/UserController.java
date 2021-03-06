package com.example.UserManager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.UserManager.entities.User;
import com.example.UserManager.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
    Logger logger = LoggerFactory.getLogger(UserController.class);
    
	@GetMapping("/users")
	public String showUsers(ModelMap model) {
		
		
		logger.info("Getting all Users");
		Iterable<User> users = userService.getAllUsers();
		
		logger.info("Passing users to view");
	    model.addAttribute("users", users);    
		
        return "users";
    }
	
	@GetMapping("/userid")
	public String showUserIDPage()
	{
		return "userid";
	}
	
//	@PostMapping("/userid")
//	public String processUserID(ModelMap model, @RequestParam int id)
//	{
//		if(!userService.validateUserID(id))
//		{
//			return "redirect:error";
//		}	
//		
//		return "update";
//	}
	
	@PostMapping("/userid")
	public ModelAndView processUserID(@RequestParam String id)
	{
		if(id.isEmpty())
			return new ModelAndView("error", "message", "Empty ID input!");
		
		if(!userService.validateUserID(Integer.parseInt(id)))
		{
			return new ModelAndView("error", "message", "User ID \"" + id + "\" not found!");
		}	
		User user = userService.getUserById(Integer.parseInt(id));
		return new ModelAndView("update", "update", user);
	}
	
	@GetMapping("/update")
	public String showUpdatePage()
	{
		return "update";
	}
	
	@PostMapping("/update")
	public String updateUsers(@RequestParam int id, @RequestParam String name, @RequestParam String email, 
			@RequestParam String password)
	{
		User user = userService.getUserById(id);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		userService.updateUser(user);
		return "confirmation";
	}
}

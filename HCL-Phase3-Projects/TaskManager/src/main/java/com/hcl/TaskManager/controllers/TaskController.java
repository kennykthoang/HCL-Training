package com.hcl.TaskManager.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.TaskManager.entities.MyUserDetails;
import com.hcl.TaskManager.entities.Task;
import com.hcl.TaskManager.entities.User;
import com.hcl.TaskManager.services.TaskService;
import com.hcl.TaskManager.services.UserService;

@Controller
public class TaskController {
	
	
	@Autowired 
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/task")
//    public String showTaskPage(@RequestParam("taskId") Integer taskId, ModelMap model ) {   
	public String showTaskForm(ModelMap model) 
	{
//		Optional<Task> task = taskService.getTaskById(taskId);
//		
//        model.addAttribute("task", task.get());
		
		return "taskform";
	}
	
	@PostMapping(value="/task")
    public String handleTaskForm(@RequestParam(value="name") String name, 
    		@RequestParam(value="sdate") String startDate, @RequestParam(value="edate") String endDate, 
    		@RequestParam(value="desc") String desc, @RequestParam(value="sev") String severity, ModelMap model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails) principal).getUsername();
		User user = userService.getUserByUserName(username);
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = date.parse(startDate);
			eDate = date.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "Date Parse exception!");
			return "error";
		}
		
		Task task = new Task(name, sDate, eDate, severity, desc, user);
		
		// Format the dates as a strings to remove the time
		String sd = DateFormat.getDateInstance().format(sDate);
		String ed = DateFormat.getDateInstance().format(eDate);
		model.addAttribute("sDate", sd);
		model.addAttribute("eDate", ed);
		model.addAttribute("action", "created");
		model.addAttribute("task", task);
		model.addAttribute("user", task.getUser());
		return "tasksuccess";
	}
	
}

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
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping(value="/task-create")
//    public String showTaskPage(@RequestParam("taskId") Integer taskId, ModelMap model ) {   
	public String showTaskForm(ModelMap model) 
	{
//		Optional<Task> task = taskService.getTaskById(taskId);
//		
//        model.addAttribute("task", task.get());
		
		return "createtask";
	}
	
	@PostMapping(value="/task-create")
    public String handleTaskForm(@RequestParam(value="name") String name, 
    		@RequestParam(value="sdate") String startDate, @RequestParam(value="edate") String endDate, 
    		@RequestParam(value="desc") String desc, @RequestParam(value="sev") String severity, ModelMap model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails) principal).getUsername();
		User user = userService.getUserByUserName(username);
		
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "Date Parse exception!");
			return "error";
		}
		
		if(!taskService.validateEmptyInput(name))
		{
			model.addAttribute("message", "422 - Unprocessable Entity");
			return "taskerror";
		}
		
		if(!taskService.checkTaskDates(sDate, eDate))
		{
			model.addAttribute("message", "422 - Unprocessable Entity <br> Could not process dates! Dates are out of order!");
			return "taskerror";
		}
		
		// Format the Dates as Date Instance strings to remove the time from Date object
		String sd = DateFormat.getDateInstance().format(sDate);
		String ed = DateFormat.getDateInstance().format(eDate);
		
		Task task = new Task(name, sDate, eDate, severity, desc, user);
		
		model.addAttribute("sDate", sd);
		model.addAttribute("eDate", ed);
		model.addAttribute("action", "creation");
		model.addAttribute("task", task);
		model.addAttribute("user", task.getUser());
		taskService.updateTask(task);
		return "tasksuccess";
	}
	
	@GetMapping("/task-view")
	public String viewUserTask(ModelMap model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails) principal).getUsername();
		User user = userService.getUserByUserName(username);
//		Iterable<Task> tasks = taskService.getTasksByUser(user);
		Iterable<Task> tasks = taskService.getAllTasks();
	    model.addAttribute("tasks", tasks);
		
		return "viewtask";
	}
	
	@GetMapping("/task-update-select")
	public String showTaskSelect()
	{
		return "selectupdatetask";
	}
	
	@PostMapping("/task-update-select")
	public ModelAndView processTaskSelect(@RequestParam String id)
	{
		if(id.isEmpty())
			return new ModelAndView("error", "message", "Empty ID input!");
		
		if(!taskService.validateTaskID(Integer.parseInt(id)))
		{
			return new ModelAndView("error", "message", "User ID \"" + id + "\" not found!");
		}	
		Optional<Task> task = null;
		try {
			task = taskService.getTaskById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error", "message", e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error", "message", e.toString());
		}
		return new ModelAndView("updatetask", "task", task.get());
	}
	
	@GetMapping("task-update")
	public String showUpdateForm()
	{
		return "updatetask";
	}
	
	
	
}

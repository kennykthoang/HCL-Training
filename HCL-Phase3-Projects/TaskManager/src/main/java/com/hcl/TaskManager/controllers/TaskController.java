package com.hcl.TaskManager.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String showCreateFrom(ModelMap model) 
	{
//		Optional<Task> task = taskService.getTaskById(taskId);
//		
//        model.addAttribute("task", task.get());
		
		return "createtask";
	}
	
	@PostMapping(value="/task-create")
    public String processTaskCreation(@RequestParam(value="name") String name, 
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
		Iterable<Task> tasks = taskService.getTasksByUser(user);
//		Iterable<Task> tasks = taskService.getAllTasks();
	    model.addAttribute("tasks", tasks);
		
		return "viewtask";
	}
	
	@GetMapping("/task-update-select")
	public String showTaskUpdateSelect(ModelMap model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails) principal).getUsername();
		User user = userService.getUserByUserName(username);
		Iterable<Task> tasks = taskService.getTasksByUser(user);
//		Iterable<Task> tasks = taskService.getAllTasks();
	    model.addAttribute("tasks", tasks);
		return "updatetaskselect";
	}
	
	@PostMapping("/task-update-select")
	public String processTaskUpdateSelect(@RequestParam String id, ModelMap model, 
			RedirectAttributes redirectAttributes)//, HttpSession session)
	/*
	 * Two different ways to pass task object to the update page
	 * RedirectAttributes and HttpSession are used to pass the task object to task-update 
	 * for formatting output to updatetask.jsp
	 */
	{
		if(id.isEmpty())
		{
			model.addAttribute("message", "Empty ID input!");
			return "taskerror";
		}
		if(!taskService.validateTaskID(Integer.parseInt(id)))
		{
			model.addAttribute("message", "Task ID \"" + id + "\" not found!");
			return "taskerror";
		}	
		Optional<Task> task = null;
		try {
			task = taskService.getTaskById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.toString());
			return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.toString());
			return "error";
		}
		model.addAttribute("task", task.get());
//		session.setAttribute("task", task.get());
		redirectAttributes.addFlashAttribute("task", task.get());
		return "redirect:/task-update";
	}
	
	@GetMapping("/task-update")
	public String showUpdateForm(ModelMap model, @ModelAttribute("task") Task task) //, HttpSession session)
	{
//		Task task = null;
//		if(session.getAttribute("task") != null)
//		{
//			task = (Task) session.getAttribute("task");
//		}
		String sd = DateFormat.getDateInstance().format(task.getStartDate());
		String ed = DateFormat.getDateInstance().format(task.getEndDate());
		model.addAttribute("sDate", sd);
		model.addAttribute("eDate", ed);
		return "updatetask";
	}
	
	@PostMapping("/task-update")
    public String processTaskUpdate(@RequestParam int id, @RequestParam(value="name") String name, 
    		@RequestParam(value="sdate") String startDate, @RequestParam(value="edate") String endDate, 
    		@RequestParam(value="desc") String desc, @RequestParam(value="sev") String severity, ModelMap model)
	{
		Task task = null;
		try {
			Optional<Task> temp = taskService.getTaskById(id);
			task = temp.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.toString());
			return "error";
		}
		
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
		String sd = DateFormat.getDateInstance().format(sDate);
		String ed = DateFormat.getDateInstance().format(eDate);
		
		task.setName(name);
		task.setDescription(desc);
		task.setSeverity(severity);
		task.setStartDate(sDate);
		task.setEndDate(eDate);
		taskService.updateTask(task);
		
		model.addAttribute("sDate", sd);
		model.addAttribute("eDate", ed);
		model.addAttribute("action", "update");
		model.addAttribute("task", task);
		model.addAttribute("user", task.getUser());
		return "tasksuccess";
	}
	
	@GetMapping("/task-delete")
	public String showTaskDelete(ModelMap model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails) principal).getUsername();
		User user = userService.getUserByUserName(username);
		Iterable<Task> tasks = taskService.getTasksByUser(user);
//		Iterable<Task> tasks = taskService.getAllTasks();
	    model.addAttribute("tasks", tasks);
		return "deletetask";
	}
	
	@PostMapping("/task-delete")
	public String processTaskDelete(@RequestParam String id, ModelMap model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails) principal).getUsername();
		User user = userService.getUserByUserName(username);
		
		if(id.isEmpty())
		{
			model.addAttribute("message", "Empty ID input!");
			return "taskerror";
		}
		
		// Check for valid ID number
		if(!taskService.validateTaskID(Integer.parseInt(id)))
		{
			model.addAttribute("message", "Could not find Task# " + id);
			return "taskerror";
		}
		
		Optional<Task> task = null;
		try {
			task = taskService.getTaskById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.toString());
			return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.toString());
			return "error";
		}
		
		// Check that the selected task belongs to logged in user
		if(!task.get().getUser().getId().equals(user.getId()))
		{
			model.addAttribute("message", "Could not find Task# " + id);
			return "taskerror";
		}
		
		String sd = DateFormat.getDateInstance().format(task.get().getStartDate());
		String ed = DateFormat.getDateInstance().format(task.get().getEndDate());
		
		model.addAttribute("sDate", sd);
		model.addAttribute("eDate", ed);
		model.addAttribute("action", "deletion");
		model.addAttribute("task", task.get());
		model.addAttribute("user", task.get().getUser());
		taskService.deleteTask(task.get());
		return "tasksuccess";
	}
}

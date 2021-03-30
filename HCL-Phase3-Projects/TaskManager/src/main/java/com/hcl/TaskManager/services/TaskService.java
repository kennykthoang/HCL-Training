package com.hcl.TaskManager.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.TaskManager.entities.Task;
import com.hcl.TaskManager.entities.User;
import com.hcl.TaskManager.repositories.TaskRepository;

@Service
public class TaskService {
	
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Iterable<Task> getAllTasks() 
	{
	
		return taskRepository.findAll();
	}

	public Optional<Task> getTaskById(Integer taskId) throws Exception
	{
		
		//TODO: create exception for task not found.
		if(!taskRepository.findById(taskId).isPresent())
			throw new Exception("Task# " + taskId + " not found!");
		return taskRepository.findById(taskId);
	}
	
	public Iterable<Task> getTasksByUser(User user) 
	{
		//TODO: what do we do if the user doesn't have any tasks or doesn't exist?
		
		return (taskRepository.findAllByUser(user));
	}
	
	public void updateTask(Task tasktoUpdate) 
	{
		taskRepository.save(tasktoUpdate);
    }
	
	public void deleteTask(Task tasktoUpdate)
	{
		taskRepository.delete(tasktoUpdate);
	}
	
	public boolean checkTaskDates(Date sDate, Date eDate)
	{
		if(sDate.after(eDate))
			return false;
		return true;
	}
	
	public boolean validateEmptyInput(String input)
	{
		if(input.isEmpty() || input.trim().isEmpty())
		{
			return false;
		}
		return true;
	}
	 
	public boolean validateTaskID(int id)
    {
    	if(!taskRepository.findById(id).isPresent())
    	{
    		return false;
    	}
    	return true;
    }
}

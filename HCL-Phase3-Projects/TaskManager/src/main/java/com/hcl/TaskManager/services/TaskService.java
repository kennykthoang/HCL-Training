package com.hcl.TaskManager.services;

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
	
	public Iterable<Task> getAllTasks() {
	
		return taskRepository.findAll();
	}

	public Optional<Task> getTaskById(Integer taskId) {
		
		//TODO: create exception for task not found.
		return taskRepository.findById(taskId);
	}
	
	public Iterable<Task> getTasksByUser(User user) {
		//TODO: what do we do if the user doesn't have any tasks or doesn't exist?
		
		return(taskRepository.findAllByUser(user));
	}
}

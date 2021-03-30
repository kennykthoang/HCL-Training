package com.hcl.TaskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.TaskManager.entities.Task;
import com.hcl.TaskManager.entities.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    public Task findByName(String name);
    
    public Iterable<Task> findAllByUser(User user);
}
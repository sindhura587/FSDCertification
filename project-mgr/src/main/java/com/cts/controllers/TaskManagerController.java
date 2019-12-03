package com.cts.controllers;

import java.util.List;

import com.cts.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.service.TaskManagerService;

@CrossOrigin
@RestController
public class TaskManagerController {
	
	@Autowired
	TaskManagerService service;
	
	@RequestMapping(path="/tasks", method=RequestMethod.GET)
	public List<Task> findAllTasks(){
		return service.findAllTasks();
	}
	
	@RequestMapping(path="/tasks/{id}", method=RequestMethod.GET)
	public Task findTask(@PathVariable Integer id){
		return service.findTask(id);
	}
	
	@RequestMapping(path="/tasks/projects/{id}", method=RequestMethod.GET)
	public List<Task> findTaskByProject(@PathVariable Integer id){
		return service.findTaskByProject(id);
	}
	
	@RequestMapping(path="/tasks", method=RequestMethod.POST)
	public void addTask(@RequestBody Task task) {
		 service.addTask(task);
	}
	
	@RequestMapping(path="/tasks", method=RequestMethod.PUT)
	public void updateTask(@RequestBody Task task){
		 service.updateTask(task);
	}
	
	@RequestMapping(path="/tasks/{id}", method=RequestMethod.DELETE)
	public void deleteTask(@PathVariable Integer id){
		 service.deleteTask(id);
	}
	
	@RequestMapping(path="/tasks/{id}", method=RequestMethod.PUT)
	public void endTask(@PathVariable Integer id){
		 service.endTask(id);
	}
}

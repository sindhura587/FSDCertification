package com.cts.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cts.entities.Project;
import com.cts.entities.Task;
import com.cts.entities.User;
import com.cts.repos.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.repos.UserRepository;

@Service
public class ProjectService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
    ProjectRepository projectRepo;
	
	@Autowired
	UserRepository userRepo;

	public List<Project> findAllProjects() {
		return projectRepo.findAll();
	}
	
	public List<Project> findAllProjectsWithTask() {
		 List<Project> projects = new ArrayList<>();
		 Predicate<Task> isCompleted = ct -> ct.getEndDate() != null && ct.getEndDate().before(new Date());
		 projectRepo.findAll().stream().forEach(p -> {
			 Project project = new Project(p.getId(), p.getProject(), 
					 					   p.getStartDate(), p.getEndDate(), 
					 					   p.getPriority());
			 List<Task> noOfTasks = retrieveTasksByProject(p.getId());
			 project.setCountOfTasks(noOfTasks.size());
			 project.setCountOfCompletedTasks((int)noOfTasks.stream().filter(isCompleted).count());
							 
			 projects.add(project);
		 });
		 
		 return projects;
	}

	public Project findProject(Integer projectId) {
		Optional<Project> project = projectRepo.findById(projectId);
		return project.isPresent() ? project.get() : null;
	}

	public void addProject(Project project) {
		if (project != null) {
			projectRepo.save(project);
			
			if(project.getManagerId() != null) {
				Optional<User> optUser = userRepo.findById(project.getManagerId());
				if(optUser.isPresent()) {
					User user = optUser.get();
					user.setProject(project);
					userRepo.save(user);
				}
			}			
		}
	}

	public void updateProject(Project project) {
		addProject(project);
	}

	public void endProject(Integer id) {
		Optional<Project> optProject = projectRepo.findById(id);
		if (optProject.isPresent()) {
			Project project = optProject.get();
			project.setEndDate(new Date());
			projectRepo.save(project);
		}
	}

	@SuppressWarnings("unchecked")
	private List<Task> retrieveTasksByProject(int projectId) {
	    Query nativeQuery = entityManager.createNativeQuery("select t.* from task t "
	    		+ "left outer join project p on t.project_id = p.id "
	    		+ "where p.id=:id", Task.class)
	    		.setParameter("id", projectId);
	    
	    return (List<Task>) nativeQuery.getResultList();
	}
}

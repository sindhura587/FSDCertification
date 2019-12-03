package com.cts.repos;

import java.util.List;

import com.cts.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public List<User> findByProjectId(Integer id);
	public List<User> findByTaskId(Integer id);
}

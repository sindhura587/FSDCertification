package com.cts.repos;

import com.cts.entities.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTaskManagerRepository extends JpaRepository<ParentTask, Integer>{

}

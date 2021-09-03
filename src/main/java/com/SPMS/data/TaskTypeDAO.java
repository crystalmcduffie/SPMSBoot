package com.SPMS.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.TaskType;

@Repository
public interface TaskTypeDAO extends JpaRepository<TaskType, Integer>{

}

package com.SPMS.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.Assignment;

@Repository
public interface AssignmentDAO extends JpaRepository<Assignment, Integer>{

}

package com.SPMS.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.Stage;

@Repository
public interface StageDAO extends JpaRepository<Stage, Integer>{

}

package com.SPMS.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.EditorStatus;

@Repository
public interface EditorStatusDAO extends JpaRepository<EditorStatus, Integer>{

}

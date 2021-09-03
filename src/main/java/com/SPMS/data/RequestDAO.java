package com.SPMS.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.Request;

@Repository
public interface RequestDAO extends JpaRepository<Request, Integer>{

}

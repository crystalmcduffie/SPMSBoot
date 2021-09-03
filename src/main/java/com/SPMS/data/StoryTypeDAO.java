package com.SPMS.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.StoryType;

@Repository
public interface StoryTypeDAO extends JpaRepository<StoryType, Integer>{

}

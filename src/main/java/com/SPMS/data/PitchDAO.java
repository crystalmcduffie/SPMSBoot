package com.SPMS.data;

import com.SPMS.beans.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.StoryType;

@Repository
public interface PitchDAO extends JpaRepository<Pitch, Integer>{

}

package com.SPMS.data;

import com.SPMS.beans.Editor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.Pitch;


@Repository
public interface PitchDAO extends JpaRepository<Pitch, Integer>{
	//public List<Pitch> findByAuthorId(Integer id);

}

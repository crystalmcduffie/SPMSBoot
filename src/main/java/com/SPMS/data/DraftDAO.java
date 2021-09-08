package com.SPMS.data;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Draft;

@Repository
public interface DraftDAO extends JpaRepository<Draft, Integer>{
	public Draft findByPitchId(Integer id);
}

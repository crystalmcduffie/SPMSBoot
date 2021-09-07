package com.SPMS.data;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer>{
	public Genre findByName(String name);

}

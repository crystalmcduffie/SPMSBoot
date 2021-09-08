package com.SPMS.data;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.GenreCommittee;

@Repository
public interface GenreCommitteeDAO extends JpaRepository<GenreCommittee, Integer>{
	public List<GenreCommittee> findAllByEditorId(Integer id);
	public List<GenreCommittee> findAllByGenreId(Integer id);
}

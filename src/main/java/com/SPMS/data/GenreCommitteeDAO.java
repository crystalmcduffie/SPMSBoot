package com.SPMS.data;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.GenreCommittee;

@Repository
public interface GenreCommitteeDAO extends JpaRepository<GenreCommittee, Integer>{
	public List<GenreCommittee> findByEditorId(Integer id);
}

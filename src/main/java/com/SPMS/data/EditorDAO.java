package com.SPMS.data;

import java.util.Set;

import com.SPMS.beans.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorDAO extends JpaRepository<Editor, Integer>{
	public Editor getByUsername(String username);
}

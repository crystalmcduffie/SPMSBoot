package com.SPMS.data;

import java.util.Set;

import com.SPMS.beans.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Integer>{
	public Author getByUsername(String username);
}

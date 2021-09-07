package com.SPMS.data;

import com.SPMS.beans.Person;
import com.SPMS.exceptions.NonUniqueUsernameException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person,Integer>{
	public Person getByUsername(String username);
	
}

package com.SPMS.data;

import com.SPMS.beans.Person;
import com.SPMS.exceptions.NonUniqueUsernameException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PersonDAO {
	public Person getByUsername(String username);
	public Person add(Person p) throws NonUniqueUsernameException;
	
}

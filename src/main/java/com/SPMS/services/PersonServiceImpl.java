package com.SPMS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Person;
import com.SPMS.data.PersonDAO;

@Service
public class PersonServiceImpl implements PersonService {
	PersonDAO personDAO;
	
	@Autowired
	public PersonServiceImpl(PersonDAO p) {
		this.personDAO = p;
	}
	
	public Person getByUsername(String username) {
		return personDAO.getByUsername(username);
	}

	@Override
	public void update(Person p) {
		personDAO.save(p);
		
	}

	@Override
	public List<Person> getAll() {
		return personDAO.findAll();
	}
}

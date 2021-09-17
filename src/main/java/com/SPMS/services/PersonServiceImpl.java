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
	public Person update(Person p) {
		return personDAO.save(p);
		
	}

	@Override
	public List<Person> getAll() {
		return personDAO.findAll();
	}


	@Override
	public boolean isAuthor(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person createUser(Person p) {
		return p = personDAO.save(p);
	}
	
	@Override
	public void deleteUser(Person p) {
		personDAO.delete(p);
	}
}

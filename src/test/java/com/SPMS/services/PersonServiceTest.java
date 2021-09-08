package com.SPMS.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Person;
import com.SPMS.beans.Role;
import com.SPMS.data.RoleDAO;
import com.SPMS.exceptions.NonUniqueUsernameException;


@SpringBootTest
public class PersonServiceTest {
	
	private static Logger log = Logger.getLogger(PersonServiceTest.class);

	@Autowired
	PersonService personService;
	RoleDAO roleDAO;

	/*	public Person getByUsername(String username);
	public void update(Person p);
	public List<Person> getAll();
	public boolean isAuthor(Person p);
	public Person createUser(Person p) throws NonUniqueUsernameException;*/
	
	@Test
	public void getPersonByUsername() {
		Person p = personService.getByUsername("kate");
		assertEquals("kate", p.getUsername());
		log.debug(p.getUsername() + " " + p.getPassword() );
	}
	
	//run all below
	@Test
	public void getEditorOrAuthor() {
		Person author = personService.getByUsername("derrick");
		Person editor = personService.getByUsername("thomas");
		assertTrue(personService.isAuthor(author));
		assertFalse(personService.isAuthor(editor));
	}

	@Test
	public void updatePerson() {
		Person p = personService.getByUsername("kate");
		p.setPassword("kitkat");
		personService.update(p);
		Person p2 = personService.getByUsername("kate");
		assertEquals(p.getPassword(),p2.getPassword());
	}
	
	@Test
	public void createPersonException() {
		Person p = new Person();
		p.setUsername("kate");
		p.setPassword("pass");
		Role r = roleDAO.findByName("editor");
		p.setRole(r);
		Assertions.assertThrows(NonUniqueUsernameException.class, () -> {
			personService.createUser(p);
			});
	}
	
	@Test
	public void createPerson() {
		Person p = new Person();
		p.setUsername("daniel");
		p.setPassword("pass");
		Role r = roleDAO.findByName("author");
		p.setRole(r);
		try {
			personService.createUser(p);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person p2 = personService.getByUsername("daniel");
		assertEquals(p,p2);
	}
}

package com.SPMS.services;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Person;
import com.SPMS.beans.Role;
import com.SPMS.data.RoleDAO;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class PersonServiceTest {
	
	private static Logger log = Logger.getLogger(PersonServiceTest.class);

	@Autowired
	PersonService personService;
	@Autowired
	RoleDAO roleDAO;
	
	@Test
	@Order(1)
	public void getPersonByUsername() {
		Person p = personService.getByUsername("kate");
		assertEquals("kate", p.getUsername());
		log.debug(p.getUsername() + " " + p.getPassword() );
	}

	@Test
	@Order(2)
	public void updatePerson() {
		Person p = personService.getByUsername("kate");
		p.setPassword("kitkat");
		personService.update(p);
		Person p2 = personService.getByUsername("kate");
		assertEquals(p.getPassword(),p2.getPassword());
	}
	
	@Test
	@Order(4)
	public void createPersonException() {
		Person p = new Person();
		p.setUsername("kate");
		p.setPassword("pass");
		Role r = roleDAO.findByName("editor");
		p.setRole(r);
		Assertions.assertThrows(Exception.class, () -> {
			personService.createUser(p);
			});
	}
	
	@Test
	@Order(5)
	public void createPerson() {
		Person p = new Person();
		p.setUsername("erica");
		p.setPassword("pass");
		Role r = roleDAO.findByName("author");
		p.setRole(r);
		p = personService.createUser(p);
		Person p2 = personService.getByUsername("erica");
		assertTrue(p.equals(p2));
	}
	
}

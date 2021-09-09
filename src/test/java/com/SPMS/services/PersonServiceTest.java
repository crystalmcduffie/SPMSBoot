package com.SPMS.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Author;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Person;
import com.SPMS.beans.Role;
import com.SPMS.data.RoleDAO;
import com.SPMS.exceptions.IncorrectTypeException;
import com.SPMS.exceptions.NonUniqueUsernameException;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class PersonServiceTest {
	
	private static Logger log = Logger.getLogger(PersonServiceTest.class);

	@Autowired
	PersonService personService;
	@Autowired
	AuthorService authorService;
	@Autowired
	EditorService editorService;
	@Autowired
	RoleDAO roleDAO;

	/*	public Person getByUsername(String username);
	public void update(Person p);
	public List<Person> getAll();
	public boolean isAuthor(Person p);
	public Person createUser(Person p) throws NonUniqueUsernameException;*/
	
	@Test
	@Order(1)
	public void getPersonByUsername() {
		Person p = personService.getByUsername("kate");
		assertEquals("kate", p.getUsername());
		log.debug(p.getUsername() + " " + p.getPassword() );
	}
	
	//run all below
	/*@Test
	@Order(3)
	public void getEditorOrAuthor() {
		Person author = personService.getByUsername("derrick");
		Person editor = personService.getByUsername("thomas");
		assertTrue(personService.isAuthor(author));
		assertFalse(personService.isAuthor(editor));
	}*/

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
		try {
			personService.createUser(p);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person p2 = personService.getByUsername("erica");
		assertTrue(p.equals(p2));
	}
	
	@Test
	@Order(6)
	public void createAuthor() {
		Person p = personService.getByUsername("erica");
		Author a = new Author(p);
		try {
			authorService.create(a);
		} catch (IncorrectTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Author a2 = authorService.getByUsername("erica");
		assertEquals(a, a2);
	}
	
	@Test
	@Order(7)
	public void createAuthorException() {
		Person p = new Person();
		p.setUsername("christy");
		p.setPassword("pass");
		Role r = roleDAO.findByName("editor");
		p.setRole(r);
		try {
			p = personService.createUser(p);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Author a = (Author) p;
		Assertions.assertThrows(IncorrectTypeException.class, () -> {
			authorService.create(a);
			});
	}
	
	@Test
	@Order(8)
	public void createEditor() {
		Person p = personService.getByUsername("christy");
		Editor e = new Editor(p);
		try {
			editorService.create(e);
		} catch (IncorrectTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Editor e2 = editorService.getByUsername("christy");
		assertEquals(e, e2);
	}
	
	@Test
	@Order(9)
	public void createEditorException() {
		Person p = new Person();
		p.setUsername("francis");
		p.setPassword("pass");
		Role r = roleDAO.findByName("author");
		p.setRole(r);
		try {
			personService.createUser(p);
		} catch (NonUniqueUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Editor e = (Editor) p;
		Assertions.assertThrows(IncorrectTypeException.class, 
				() -> {editorService.create(e);}
				);
	}
	
}

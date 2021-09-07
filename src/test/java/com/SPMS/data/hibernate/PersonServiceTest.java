package com.SPMS.data.hibernate;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.hibernate.EditorHibernate;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;
import com.SPMS.services.PersonService;

@SpringBootTest
public class PersonServiceTest {
	//private static EditorService editorServ;
	
	private Logger log = Logger.getLogger(PersonServiceTest.class);
	
	@Autowired
	EditorService editorService;
	@Autowired
	PersonService personService;

	
	
	@Test
	public void getPerson() {
		Person p = personService.getByUsername("kate");
		System.out.println(p.getUsername() + " " + p.getPassword());
	}

	@Test
	public void getEditor() {
		Editor e = editorService.getByUsername("kate");
		System.out.println(e.getUsername() + " " + e.getPassword() + " " + e.getAssignments());
	}


}

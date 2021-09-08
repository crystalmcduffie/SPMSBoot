package com.SPMS.data.hibernate;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Message;
import com.SPMS.beans.Person;
import com.SPMS.data.GenreCommitteeDAO;
import com.SPMS.services.EditorService;
import com.SPMS.services.PersonService;

@SpringBootTest
public class GenreCommitteeHibernateTest {
	
	private static Logger log = Logger.getLogger(GenreCommitteeHibernateTest.class);
	
	@Autowired
	EditorService editorService;
	@Autowired
	PersonService personService;
	@Autowired
	GenreCommitteeDAO gcDAO;

	@Test
	public void getMessages() {
		Person p = personService.getByUsername("gabriel");
		log.debug(p.getUsername() + " " + p.getPassword() );
		List<GenreCommittee> gcs = gcDAO.findByEditorId(p.getId());
		printGenreCommittees(gcs);
	}


	public static void printGenreCommittees(List<GenreCommittee> gcs) {
		for(GenreCommittee gc : gcs) {
			log.info("Genre: " + gc.getGenre());
			log.info("Editor: " + gc.getEditor());
			log.info("Status: " + gc.getEditorStatus());
		}
	}

}

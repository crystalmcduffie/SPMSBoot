package com.SPMS.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Message;
import com.SPMS.beans.Person;
import com.SPMS.data.GenreCommitteeDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.services.EditorService;
import com.SPMS.services.PersonService;

@SpringBootTest
public class GenreServiceTest {
	
	private static Logger log = Logger.getLogger(GenreServiceTest.class);
	
	@Autowired
	PersonService personService;
	@Autowired
	GenreCommitteeDAO gcDAO;
	@Autowired
	GenreDAO gDAO;

	@Test
	public void getEditorGenreCommitte() {
		Person p = personService.getByUsername("gabriel");
		log.debug(p.getUsername() + " " + p.getPassword() );
		List<GenreCommittee> gcs = gcDAO.findByEditorId(p.getId());
		for(GenreCommittee gc : gcs) {
			assertEquals(p.getId(), gc.getEditor().getId());
			assertEquals("gabriel", gc.getEditor().getUsername());
		}
		printGenreCommittees(gcs);
	}
	
	@Test
	public void getGenreByName() {
		Genre g = gDAO.findByName("horror");
		assertEquals(2, g.getId());
		assertEquals("horror", g.getName());
	}
	
	@Test
	public void getGenreCommitteeByGenre() {
		List<GenreCommittee> gcs = gcDAO.findByGenreId(2);
		for(GenreCommittee gc : gcs) {
			assertEquals("horror", gc.getGenre().getName());
		}
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

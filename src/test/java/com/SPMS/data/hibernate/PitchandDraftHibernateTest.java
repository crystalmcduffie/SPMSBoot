package com.SPMS.data.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.services.PersonService;

public class PitchandDraftHibernateTest {
	private static Logger log = Logger.getLogger(PitchandDraftHibernateTest.class);
	
	@Autowired
	PersonService personService;
	@Autowired
	PitchHibernate pitchDAO;
	@Autowired
	DraftHibernate draftDAO;
	
	public void getPitches() {
		Person p = personService.getByUsername("derrick");
		log.debug(p.getUsername() + " " + p.getPassword() );
		List<Pitch> pitches = pitchDAO.getAuthorPitches(p.getId());
		List<Draft> drafts = draftDAO.getAuthorDrafts(pitches);
		log.debug(pitches);
		log.debug(drafts);
	}
	
	public static void printPitches(List<Pitch> pitches) {
		for(Pitch p : pitches) {
			log.info("Id: " + p.getId());
			log.info("Task type: " + p.getType());
			log.info("Title: " + p.getTitle());
			log.info("Genre: " + p.getGenre());
		}
	}
	
	public static void printDrafts(List<Draft> drafts) {
		for(Draft d : drafts) {
			log.info("Id: " + d.getId());
			log.info("Task type: " + d.getType());
			log.info("Draft: " + d.getDraft());
		}
	}
	
}

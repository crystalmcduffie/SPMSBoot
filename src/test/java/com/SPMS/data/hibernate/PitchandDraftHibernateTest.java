package com.SPMS.data.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.services.PersonService;

@SpringBootTest
public class PitchandDraftHibernateTest {
	private static Logger log = Logger.getLogger(PitchandDraftHibernateTest.class);
	
	@Autowired
	PersonService personService;
	@Autowired
	PitchDAO pitchDAO;
	@Autowired
	DraftDAO draftDAO;
	
	@Test
	public void getPitches() {
		Person p = personService.getByUsername("derrick");
		log.debug(p.getUsername() + " " + p.getPassword() );
		List<Pitch> pitches = pitchDAO.findByAuthorId(p.getId());
		//List<Draft> drafts = draftDAO.findByPitchId(pitches);
		List<Draft> drafts = new ArrayList<>();
		for(Pitch pitch : pitches) {
			drafts.add(draftDAO.findByPitchId(pitch.getId()));
		}
		log.debug("pitches: " + pitches);
		log.debug("drafts: " + drafts);
		printPitches(pitches);
		printDrafts(drafts);
	}
	
	public static void printPitches(List<Pitch> pitches) {
		log.info("pitches: ");
		for(Pitch p : pitches) {
			log.info("Id: " + p.getId());
			log.info("Task type: " + p.getType().getName());
			log.info("Title: " + p.getTitle());
			log.info("Genre: " + p.getGenre());
		}
	}
	
	public static void printDrafts(List<Draft> drafts) {
		log.info("drafts: ");
		for(Draft d : drafts) {
			log.info("Id: " + d.getId());
			log.info("Task type: " + d.getType().getName());
			log.info("Draft: " + d.getDraft());
		}
	}
	
}

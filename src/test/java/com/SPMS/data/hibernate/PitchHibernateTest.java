package com.SPMS.data.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.SPMS.beans.Author;
import com.SPMS.beans.Genre;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.hibernate.PitchHibernate;
import com.SPMS.services.AuthorService;
import com.SPMS.services.AuthorServiceImpl;
import com.SPMS.services.PitchService;
import com.SPMS.services.PitchServiceImpl;

public class PitchHibernateTest {
	private static PitchService pitchServ;
	private static AuthorService authorServ;
	private PitchHibernate pitchDAO = new PitchHibernate();
	private Logger log = Logger.getLogger(PitchHibernateTest.class);
	
	@BeforeAll
	public static void setup() {
		pitchServ = new PitchServiceImpl();
		authorServ = new AuthorServiceImpl();
	}
	
	@Test void testGetPitch() {
		Pitch pitch= pitchServ.getPitch(1);
		log.debug(pitch);
	}
	
	@Test
	public void testCreatePitch() {
		Author a = authorServ.getByUsername("derrick");
		Pitch p = new Pitch();
		StoryType st = new StoryType();
		st.setStoryType("novel");
		st.setId(1);
		st.setPoints(50);
		p.setStoryType(st);
		p.setStoryTitle("test");
		Genre g = new Genre();
		g.setName("romance");
		g.setId(2);
		p.setGenre(g);
		log.debug(pitchServ.createPitch(a, p));
		log.debug(p.getStage().getName());
	}
	
	@Test 
	public void testGetPitchStage(){
		PitchStage ps = pitchDAO.getPitchStage("assistant_editor");
		log.debug(ps);
	}
	@Test
	public void testApprovePitch() {
		Pitch p = pitchServ.getPitch(14);
		pitchServ.approvePitch(p);
	}
	
	@Test
	public void testRejectPitch() {
		Pitch p = pitchServ.getPitch(14);
		pitchServ.rejectPitch(p);
	}
	
	@Test
	public void testCancelPitch() {
		Pitch p = pitchServ.getPitch(15);
		pitchServ.cancelPitch(p);
	}
	
	@Test
	public void testupdatePitch() {
		Pitch pitch = pitchServ.getPitch(id);
	}
	
	@Test
	public void testSortPitches() {
		Author a = authorServ.getByUsername("derrick");
		List<Pitch> orderedPitches = pitchServ.listPitchesInOrder(a.getPitches());
		for(Pitch p : orderedPitches) {
			log.debug(p.getId());
		}
	}
}

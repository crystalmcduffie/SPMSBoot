package com.revature.data.hibernate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Pitch;
import com.SPMS.services.GenreService;
import com.SPMS.services.GenreServiceImpl;

public class GenreHibernateTest {
	private static GenreService genreServ;
	private Logger log = Logger.getLogger(GenreHibernateTest.class);

	@BeforeAll
	public static void setup() {
		genreServ = new GenreServiceImpl();	
	}
	
	@Test
	public void testGetGenre() {
		Genre testGenre = genreServ.getGenre("horror");
		Genre g = new Genre();
		g.setId(2);
		g.setName("horror");
		assertEquals(g, testGenre);
	}
	
	@Test void testGetAllGenres() {
		Set<Genre> allGenres = new HashSet<>();
		Set<Genre> testAllGenres = genreServ.getAllGenres();
		Genre g = new Genre();
		g.setId(1);
		g.setName("romance");
		allGenres.add(g);
		g = new Genre();
		g.setId(2);
		g.setName("horror");
		allGenres.add(g);
		g = new Genre();
		g.setId(3);
		g.setName("sci-fi");
		allGenres.add(g);
		g = new Genre();
		g.setId(4);
		g.setName("adventure");
		allGenres.add(g);
		g = new Genre();
		g.setId(5);
		g.setName("nonfiction");
		allGenres.add(g);
		for(Genre gr : allGenres) {
			assertTrue(testAllGenres.contains(gr));
		}
		
	}
	@Test
	public void testGetGenreCommittee() {
		log.debug("This is the test to get a genre committee");
		Genre g = genreServ.getGenre("adventure");
		
		Set<GenreCommittee> gc = genreServ.getGenreCommittee(g);
		
		printGenreCommittee(gc);
	}
	
	
	
	@Test
	public void testGetAllGenreCommittees() {
		log.debug("This is the test to get all genre committees");
		Set<Set<GenreCommittee>> allGC = genreServ.getAllGenreCommittees();
		
		for(Set<GenreCommittee> gc : allGC) {
			printGenreCommittee(gc);
		}
	}
	
	public void printGenreCommittee(Set<GenreCommittee> genreCommittee) {
		for(GenreCommittee gc: genreCommittee) {
			log.debug("This is editor: " + gc.getEditor().getUsername());
			log.debug("They are on the " + gc.getGenre().getName() + " committee");
			log.debug("Their status on this committee is: " + gc.getEditorStatus().getName());
			log.debug("These are the pitches that they have been assigned to:");
			Set<Pitch> pitches = gc.getEditor().getPitches();
			for(Pitch p : pitches) {
				log.debug(p);
			}
			System.out.println();
		}
	}
}

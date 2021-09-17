package com.SPMS.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Message;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.Role;
import com.SPMS.beans.Stage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.GenreCommitteeDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.RoleDAO;
import com.SPMS.data.StageDAO;
import com.SPMS.data.StoryTypeDAO;
import com.SPMS.services.PersonService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class PitchServiceTest {
	
	private static Logger log = Logger.getLogger(PitchServiceTest.class);
	
	@Autowired
	PitchService pitchService;
	@Autowired
	PersonService personService;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	GenreDAO genreDAO;
	@Autowired
	StoryTypeDAO storyTypeDAO;
	@Autowired
	StageDAO stageDAO;
/*//CRUD
	//If CreatePitch returns "insufficient points", the pitch
	//was created and placed on hold.
	//If it returns "pitch created" then the pitch process
	//has started
	//If it returns "error" then something went wrong in the DB
	public Pitch getPitch(Integer id);
	public Pitch createPitch(Author a, Pitch p);
	public String calculatePriority(Pitch p);
	public void approvePitch(Pitch p);
	//general editor cannot be the same as the assistant editor
	public void assignPitch(Pitch p);
	public void updatePitch(Pitch p);
	public void cancelPitch(Pitch p);
	public void rejectPitch(Pitch p);
	public List<Pitch> listPitchesInOrder(Set<Pitch> pitches);
	public Set<Pitch> getAuthorPitches(Author a);*/
	
	private static Person author;
	private static Person editor;
	private static Date today;
	
	@BeforeAll
	public void setUp() {
		author = new Person();
		author.setUsername("test_author");
		author.setPassword("pass");
		Role roleAuthor = new Role();
		roleDAO.findByName("author");
		author.setRole(roleAuthor);
		personService.createUser(author);
		
		editor = new Person();
		editor.setUsername("test_editor");
		editor.setPassword("pass");
		Role roleEditor = new Role();
		roleDAO.findByName("editor");
		editor.setRole(roleEditor);
		personService.createUser(editor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateString = "17-09-2021";
		try{
	           //formatting the dateString to convert it into a Date 
		   java.util.Date date = sdf.parse(dateString);
		   //System.out.println("Given Time in milliseconds : "+date.getTime());
		   today = new Date(date.getTime());
		}catch(ParseException e){
		   e.printStackTrace();
		 } 
	}
	
	@AfterAll 
	public void tearDown() {
		personService.deleteUser(author);
		personService.deleteUser(editor);
	}

	@Test
	@Order(1)
	public void createPitchCalculatePoints() {
		/*1)if author has sufficient points
		 * then they should be able to create a pitch and it
		 * not be on hold
		 * 2)if author has insufficient points then
		 * the pitch they create should be placed on hold
		 * 3)author should not be allowed to set a completion
		 * date that is after the current date
		 * 4) createPitch method should set stage to assistant editor
		 * 5) completion date should be current date*/
		
		//author doesn't have any pitches,
		//so their points should be 100
		int points = personService.calculatePoints(author);
		assertEquals(100, points);
		
		Pitch p = new Pitch();
		p.setAuthor(author);
		p.setAuthorInfo("test author created a pitch");
		p.setDescription("test author created a pitch");
		p.setTitle("test author's pitch");
		p.setTagLine("pitch in testing");
		Genre g = new Genre();
		g = genreDAO.findByName("adventure");
		p.setGenre(g);
		StoryType st = new StoryType();
		st = storyTypeDAO.findByName("novel");
		p.setStoryType(st);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateString = "21-03-2029";
		try{
	       //formatting the dateString to convert it into a Date 
		   java.util.Date date = sdf.parse(dateString);
		   //System.out.println("Given Time in milliseconds : "+date.getTime());
		   Date sqlDate = new Date(date.getTime());
		   p.setTentativeCompletionDate(sqlDate);
		}catch(ParseException e){
		   e.printStackTrace();
		 } 
		
		p = pitchService.createPitch(author, p);
		
		//1)
		assertFalse(p.isOnHold());
		//5)
		assertEquals(p.getTimestamp(), today);
		//4)
		assertEquals(p.getStage(), stageDAO.findByName("assistant_editor").getId());
		
		points = personService.calculatePoints(author);
		assertEquals(points, (100 - st.getPoints()));
		
		//tentative completion date cannot be in the past
		final Pitch p2 = new Pitch();
		p2.setAuthor(author);
		p2.setAuthorInfo("test author created a pitch");
		p2.setDescription("test author created a pitch");
		p2.setTitle("test author's pitch");
		p2.setTagLine("pitch in testing");
		Genre g = new Genre();
		g = genreDAO.findByName("adventure");
		p2.setGenre(g);
		StoryType st = new StoryType();
		st = storyTypeDAO.findByName("novel");
		p2.setStoryType(st);
		dateString = "10-01-2020";
		try {
			java.util.Date date = sdf.parse(dateString);
			Date sqlDate = new Date(date.getTime());
			p2.setTentativeCompletionDate(sqlDate);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		//3)
		Assertions.assertThrows(Exception.class, () -> {
			pitchService.createPitch(author, p2);
			});
		
		//pitch should not have been created
		//so points should not change
		points = personService.calculatePoints(author);
		assertEquals(points, (100 - st.getPoints()));
		
		p = pitchService.createPitch(author, p);
		points = personService.calculatePoints(author);
		assertEquals(points, (100 - st.getPoints()*2));
		
		p = pitchService.createPitch(author, p);
		points = personService.calculatePoints(author);
		assertEquals(points, 0);
		
		//2)
		assertTrue(p.isOnHold());
	}

}

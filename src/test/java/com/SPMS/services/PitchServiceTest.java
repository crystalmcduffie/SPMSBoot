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

import com.SPMS.beans.Author;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Message;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.data.GenreCommitteeDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.services.EditorService;
import com.SPMS.services.PersonService;

@SpringBootTest
public class PitchServiceTest {
	
	private static Logger log = Logger.getLogger(PitchServiceTest.class);
	
	@Autowired
	PitchService pitchService;
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

	public void createPitch() {
		
	}

}

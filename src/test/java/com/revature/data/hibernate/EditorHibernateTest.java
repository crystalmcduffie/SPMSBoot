package com.revature.data.hibernate;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.SPMS.beans.Author;
import com.SPMS.beans.Draft;
import com.SPMS.beans.DraftMessage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchMessage;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;

public class EditorHibernateTest {
	private static EditorService editorServ;
	private Logger log = Logger.getLogger(EditorHibernateTest.class);
	
	@BeforeAll
	public static void setup() {
		editorServ = new EditorServiceImpl();
	}
	

	
	@Test
	public void getByUsernameTest() {
		Editor e = editorServ.getByUsername("gabriel");
		//log.debug(a.toString());
		System.out.println(e.getUsername() + e.getId() + e.getPassword());
		
		printPitches(e);

		}
	
	@Test
	public void updateEditor() {
		Editor e = editorServ.getByUsername("gabriel");
		Pitch p = new Pitch();
		p.setId(9);
		p.setAuthorInfo("Crystal McDuffie");
		Set<Pitch> pitches = new HashSet<>();
		pitches.add(p);
		e.setPitches(pitches);
		editorServ.update(e);
		e = editorServ.getByUsername("gabriel");
		printPitches(e);
	}
	public void printPitches(Editor e){
		Set<Pitch> pitches = new HashSet<>();
		pitches.addAll(e.getPitches());
		for(Pitch pitch : pitches) {
			
			System.out.println(pitch.toString());
		}
	}
	
//	public void printMessages(Editor e) {
//		Set<DraftMessage> receivedDM = new HashSet<>();
//		receivedDM.addAll(e.getReceivedDraftMessages());
//		for(DraftMessage draftMessage : receivedDM) {
//			System.out.println(draftMessage.toString());
//		}
//		Set<DraftMessage> sentDM = new HashSet<>();
//		sentDM.addAll(e.getSentDraftMessages());
//		for(DraftMessage draftMessage : sentDM) {
//			System.out.println(draftMessage.toString());
//		}
//		
//		Set<PitchMessage> receivedAPM = new HashSet<>();
//		receivedAPM.addAll(e.getReceivedAuthorPitchMessages());
//		for(PitchMessage pitchMessage : receivedAPM) {
//			System.out.println(pitchMessage.toString());
//		}
//		Set<PitchMessage> sentAPM = new HashSet<>();
//		sentAPM.addAll(e.getSentAuthorPitchMessages());
//		for(PitchMessage pitchMessage : sentAPM) {
//			System.out.println(pitchMessage.toString());
//		}
//		
//		Set<PitchMessage> receivedEPM = new HashSet<>();
//		receivedEPM.addAll(e.getReceivedEditorPitchMessages());
//		for(PitchMessage pitchMessage : receivedEPM) {
//			System.out.println(pitchMessage.toString());
//		}
//		Set<PitchMessage> sentEPM = new HashSet<>();
//		sentEPM.addAll(e.getSentEditorPitchMessages());
//		for(PitchMessage pitchMessage : sentEPM) {
//			System.out.println(pitchMessage.toString());
//		}
//	}
	
	
}

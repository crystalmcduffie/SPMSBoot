package com.SPMS.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorPitch;
import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.EditorPitch;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchLog;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.data.hibernate.PitchHibernate;

import sun.jvm.hotspot.oops.java_lang_Class;

public class PitchServiceImpl implements PitchService{
	private PitchDAO pitchDAO;
	private AuthorDAO authorDAO;
	private GenreDAO genreDAO;
	private EditorDAO editorDAO;
	
	public PitchServiceImpl() {
		pitchDAO = DAOFactory.getPitchDAO();
		authorDAO = DAOFactory.getAuthorDAO();
		genreDAO = DAOFactory.getGenreDAO();
		editorDAO = DAOFactory.getEditorDAO();
	}
	
	@Override
	public Pitch getPitch(Integer id) {
		return pitchDAO.getPitch(id);
	}
	
	@Override
	public Pitch createPitch(Author a, Pitch p) {
		Integer authorPoints = a.getPoints();
		Integer storyPoints = p.getStoryType().getPoints();
		if(authorPoints >= storyPoints) {
			boolean wasOnHold = p.isOnHold();
			p.setOnHold(false);
			PitchStage ps = pitchDAO.getPitchStage("assistant_editor");
			p.setStage(ps);
			if(wasOnHold == false) {
				p.setPriority("low");
				pitchDAO.createPitch(p);
				AuthorPitch ap = new AuthorPitch();
				ap.setAuthor(a);
				ap.setPitch(p);
				pitchDAO.addToAuthorPitch(ap);
				
			}else {
				pitchDAO.updatePitch(p);
			}
			assignPitch(p);
			authorPoints-=storyPoints;
			a.setPoints(authorPoints);
			authorDAO.update(a);
			return p;
			}
		else {
			p.setOnHold(true);
			p.setPriority("high");
			PitchStage ps = pitchDAO.getPitchStage("on_hold");
			p.setStage(ps);
			pitchDAO.createPitch(p);
			AuthorPitch ap = new AuthorPitch();
			ap.setAuthor(a);
			ap.setPitch(p);
			pitchDAO.addToAuthorPitch(ap);
			return p;
		}
		
	}

	@Override
	public String calculatePriority(Pitch p) {
		//Ideally, each stage in approving a pitch should take 1/3 time between
		//the pitch's submission date and completion date
//		java.sql.Date timestamp = p.getTimestamp();
//		java.sql.Date completionDate = p.getCompletionDate();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//		LocalDateTime now = LocalDateTime.now(); 
//		try {
//			utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(completionDate);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//		log.debug(sqlDate);
		return null;
	}

	@Override
	public void approvePitch(Pitch p) {
		PitchStage ps = p.getStage();
		if(ps.getName().equals("senior_editor")) {
			//set pitch's stage to "approved", delete from editor_pitch table
			//so that no editor is assigned to work on the pitch,
			//give the author's points back
			ps = pitchDAO.getPitchStage("approved");
			p.setStage(ps);
			pitchDAO.updatePitch(p);
			EditorPitch ep = pitchDAO.getEditorPitch(p);
			pitchDAO.deleteEditorPitch(ep);
//			AuthorPitch ap = pitchDAO.getAuthorPitch(p);
//			Author a = ap.getAuthor();
//			Integer points = p.getStoryType().getPoints();
//			Integer authorPoints = a.getPoints();
//			authorPoints+=points;
//			a.setPoints(authorPoints);
//			authorDAO.update(a);
		}
		else {
			if(ps.getName().equals("assistant_editor")) {
				ps = pitchDAO.getPitchStage("general_editor");
				p.setStage(ps);
				pitchDAO.updatePitch(p);
				assignPitch(p);
			}
			else {
				ps = pitchDAO.getPitchStage("senior_editor");
				p.setStage(ps);
				pitchDAO.updatePitch(p);
				assignPitch(p);
			}
		}
		
	}

	@Override
	public void assignPitch(Pitch p) {
		//assumes the author's stage is set to what the work
		//assignment should be
		PitchStage ps = p.getStage();
		//If flag = true then we skipped the assistant_editor stage
		boolean flag = false;
		if(ps.getName().equals("assistant_editor")) {
			//if there are no assistant editor, assign to a general editor
			Set<GenreCommittee> genreCommittee = genreDAO.getGenreCommittee(p.getGenre());
			Set<Editor> assistantEditors = new HashSet<>();
			for(GenreCommittee gc : genreCommittee) {
				if(gc.getEditorStatus().getName().equals("assistant")) {
					assistantEditors.add(gc.getEditor());
				}
			}
			
			if(assistantEditors.isEmpty()) {
				ps = pitchDAO.getPitchStage("general_editor");
				p.setStage(ps);
				pitchDAO.updatePitch(p);
				flag = true;
			}
			else {
				//choose editor that is in the relevant committee and has the
				//lightest workload
				Iterator<Editor> aeItr = assistantEditors.iterator();
				Editor ed = aeItr.next();
				for(Editor e : assistantEditors) {
					if(e.getPitches().size() < ed.getPitches().size()) {
						ed = e;
					}
				}
				//add to pitch_log, add to editor_pitch
				PitchLog pl = new PitchLog();
				pl.setEditor(ed);
				pl.setPitch(p);
				pl.setPitchStage(p.getStage());
				pitchDAO.addToPitchLog(pl);
				EditorPitch ep = new EditorPitch();
				ep.setEditor(ed);
				ep.setPitch(p);
				pitchDAO.addToEditorPitch(ep);
			}
		}
		//This is not an else if because the approve code may need to run here
		//if there weren't any assistant editors to assign work to
		if(ps.getName().equals("general_editor")){
			//choose editor that is not in the relevant committee and has the
			//lightest workload
			

			//Set<Set<GenreCommittee>> allGC = genreServ.getAllGenreCommittees();
			Set<GenreCommittee> relevantGC = genreDAO.getGenreCommittee(p.getGenre());
			//allGC.remove(relevantGC);
			
			//We don't want any editors within the relevant genre committee
			//If we remove from allEditors within the first for loop we'll
			//get an exception because we modified the set during is iteration
			Set<Editor> allEditors = editorDAO.getAll();
			Set<Editor> editorsToRemove = new HashSet<>();
			for(Editor e : allEditors) {
				for(GenreCommittee gc : relevantGC) {
					if(gc.getEditor().getId() == e.getId()) {
						editorsToRemove.add(e);
					}
				}
			}
			
			for(Editor e : editorsToRemove) {
				allEditors.remove(e);
			}
			
			Iterator<Editor> eItr = allEditors.iterator();
			Editor ed = eItr.next();
			for(Editor e : allEditors) {
				if(e.getPitches().size() < ed.getPitches().size()) {
					ed = e;
				}
			}
			//add to pitch_log, update editor_pitch
			PitchLog pl = new PitchLog();
			pl.setEditor(ed);
			pl.setPitch(p);
			pl.setPitchStage(p.getStage());
			pitchDAO.addToPitchLog(pl);
			if(flag) {
				EditorPitch ep = new EditorPitch();
				ep.setEditor(ed);
				ep.setPitch(p);
				pitchDAO.addToEditorPitch(ep);
			}
			else {
				EditorPitch ep = pitchDAO.getEditorPitch(p);
				ep.setEditor(ed);
				pitchDAO.updateEditorPitch(ep);
			}
			
		}
		else if(ps.getName().equals("senior_editor")) {
			Set<GenreCommittee> genreCommittee = genreDAO.getGenreCommittee(p.getGenre());
			Set<Editor> seniorEditors = new HashSet<>();
			for(GenreCommittee gc : genreCommittee) {
				if(gc.getEditorStatus().getName().equals("senior")) {
					seniorEditors.add(gc.getEditor());
				}
			}
			//choose senior editor that is in the relevant committee and has the
			//lightest workload
			Iterator<Editor> aeItr = seniorEditors.iterator();
			Editor ed = aeItr.next();
			for(Editor e : seniorEditors) {
				if(e.getPitches().size() < ed.getPitches().size()) {
					ed = e;
				}
			}
			//add to pitch_log, update editor_pitch
			PitchLog pl = new PitchLog();
			pl.setEditor(ed);
			pl.setPitch(p);
			pl.setPitchStage(p.getStage());
			pitchDAO.addToPitchLog(pl);
			EditorPitch ep = pitchDAO.getEditorPitch(p);
			ep.setEditor(ed);
			pitchDAO.updateEditorPitch(ep);
		}
		
	}

	@Override
	public void updatePitch(Pitch p) {
		pitchDAO.updatePitch(p);
		
	}

	@Override
	public void cancelPitch(Pitch p) {
		PitchStage ps = pitchDAO.getPitchStage("canceled");
		p.setStage(ps);
		pitchDAO.updatePitch(p);
		EditorPitch ep = pitchDAO.getEditorPitch(p);
		pitchDAO.deleteEditorPitch(ep);
		
	}

	@Override
	public void rejectPitch(Pitch p) {
		PitchStage ps = pitchDAO.getPitchStage("denied");
		p.setStage(ps);
		pitchDAO.updatePitch(p);
		EditorPitch ep = pitchDAO.getEditorPitch(p);
		pitchDAO.deleteEditorPitch(ep);
	}

	@Override
	public PitchStage getPitchStage(String name) {
		return pitchDAO.getPitchStage(name);
	}

	@Override
	public StoryType getStoryType(String name) {
		return pitchDAO.getStoryType(name);
	}

	public Set<Pitch> getApprovedPitches(Author a){
		Set<Pitch> pitches = a.getPitches();
		Set<Pitch> approvedPitches = new HashSet<>();
		for(Pitch p : pitches) {
			if(p.getStage().getName().equals("approved")) {
				approvedPitches.add(p);
			}
		}
		return approvedPitches;
	}
	
	public Set<Pitch> getPitchesOnHold(Author a){
		Set<Pitch> pitches = a.getPitches();
		Set<Pitch> onHoldPitches = new HashSet<>();
		for(Pitch p : pitches) {
			if(p.isOnHold()) {
				onHoldPitches.add(p);
			}
		}
		return onHoldPitches;
	}

	@Override
	public Set<Pitch> getSeniorEditorPitches(Editor e) {
		Set<Pitch> pitches = e.getPitches();
		Set<Pitch> seniorEditorPitches = new HashSet<>();
		for(Pitch p : pitches) {
			if(p.getStage().getName().equals("senior_editor")) {
				seniorEditorPitches.add(p);
			}
		}
		return seniorEditorPitches;
	}

	@Override
	public List<Pitch> listPitchesInOrder(Set<Pitch> pitches) {
		List<Pitch> plist = new ArrayList<>();
		for(Pitch p : pitches) {
			plist.add(p);
		}
		Collections.sort(plist);
		return plist;
	}

}

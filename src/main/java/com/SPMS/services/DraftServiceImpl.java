package com.SPMS.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorDraft;
import com.SPMS.beans.Draft;
import com.SPMS.beans.DraftLog;
import com.SPMS.beans.DraftStage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.data.hibernate.DraftHibernate;

public class DraftServiceImpl implements DraftService{
	private DraftDAO draftDAO;
	private PitchDAO pitchDAO;
	private AuthorDAO authorDAO;
	private GenreDAO genreDAO;
	private Logger log = Logger.getLogger(DraftServiceImpl.class);
	
	public DraftServiceImpl() {
		authorDAO = DAOFactory.getAuthorDAO();
		draftDAO =DAOFactory.getDraftDAO();
		pitchDAO = DAOFactory.getPitchDAO();
		genreDAO = DAOFactory.getGenreDAO();
	}
	
	@Override
	public Draft getDraft(Integer id) {
		return draftDAO.getDraft(id);
	}
	
	@Override
	public void submitDraft(Author a, Draft d, Pitch p) {
		DraftStage ds = draftDAO.getDraftStage("pending");
		d.setStage(ds);
		draftDAO.submitDraft(d);
		AuthorDraft ad = new AuthorDraft();
		ad.setAuthor(a);
		ad.setDraft(d);
		draftDAO.addToAuthorDraft(ad);
		PitchStage ps = pitchDAO.getPitchStage("draft_submitted");
		p.setStage(ps);
		pitchDAO.updatePitch(p);
	
	}

	@Override
	public void updateDraft(Draft d) {
		draftDAO.updateDraft(d);
		
	}

	@Override
	public void approveDraft(Editor e, Draft d) {
		//Add to draft_log which is a list of author approvals
		DraftLog dl = new DraftLog();
		dl.setEditor(e);
		dl.setDraft(d);
		draftDAO.addToDraftLog(dl);
		//Check if draft meets criteria for final approval
		StoryType st = d.getStoryType();
		Set<DraftLog> draftApprovals = draftDAO.getAllDraftApprovals(d);
		Set<GenreCommittee> genreCommittee = genreDAO.getGenreCommittee(d.getGenre());
//		Set<Editor> editors = new HashSet<>();
//		for(GenreCommittee gc : genreCommittee) {
//			editors.add(gc.getEditor());
//		}
		boolean approve = false;
		if (st.getStoryType().equals("novel") || st.getStoryType().equals("novella")) {
			//Needs to be approved by a majority of
			//the editors in the relevant genre committee
			Integer majorityAmount = genreCommittee.size();
			if(majorityAmount%2==1) {
				majorityAmount++;
			}
			majorityAmount = majorityAmount/2;
			Integer count = 0;
			for(GenreCommittee gc : genreCommittee) {
				Editor ed = gc.getEditor();
				for(DraftLog dla : draftApprovals) {
					if(ed.getId()==dla.getEditor().getId()) {
						count++;
					}
				}
			}
			
			if(count>=majorityAmount) {
				approve = true;
			}
			
		}
		else if (st.getStoryType().equals("short_story")) {
			//Needs to be approved by a senior editor
			//and at least one other editor from the relevant
			//genre committee
			Integer countSenior = 0;
			Integer countAssistant = 0;
			for(GenreCommittee gc : genreCommittee) {
				Editor ed = gc.getEditor();
				for(DraftLog dla : draftApprovals) {
					if(ed.getId()==dla.getEditor().getId()) {
						if(gc.getEditorStatus().getName().equals("senior")) {
							countSenior++;
						}
						else {
							countAssistant++;
						}
					}
				}
			}
			if(countSenior>0) {
				if(countAssistant>0) {
					approve = true;
				}
			}
			
		}
		else if (st.getStoryType().equals("article")) {
			//Needs to be approved by one senior editor
			//in the relevant genre committee
			Integer countSenior = 0;
			for(GenreCommittee gc : genreCommittee) {
				Editor ed = gc.getEditor();
				for(DraftLog dla : draftApprovals) {
					if(ed.getId()==dla.getEditor().getId()) {
						if(gc.getEditorStatus().getName().equals("senior")) {
							countSenior++;
						}
					}
				}
			}
			if(countSenior > 0) {
				approve = true;	
			}
		}
		//Change draft's stage to approved
		if(approve) {
			DraftStage ds = draftDAO.getDraftStage("approved");
			d.setStage(ds);
			AuthorDraft ad = draftDAO.getAuthorDraft(d);
			Author a = ad.getAuthor();
			Integer points = d.getStoryType().getPoints();
			Integer authorPoints = a.getPoints();
			authorPoints+=points;
			a.setPoints(authorPoints);
			authorDAO.update(a);
			draftDAO.updateDraft(d);
		}
	}
	
	public Set<Draft> getAll(){
		return draftDAO.getAll();
	}

	public Set<Draft> getAllPendingDrafts(){
		return draftDAO.getAllPendingDrafts();
	}

	@Override
	public Set<Draft> getPendingDraftsForEditor(Editor e) {
		Set<Draft> allPendingDrafts = getAllPendingDrafts();
		for(Draft d : allPendingDrafts) {
			log.debug(d.getId());
		}
		Set<DraftLog> editorDraftApprovals = draftDAO.getEditorDraftApprovals(e);
		for(DraftLog d : editorDraftApprovals) {
			log.debug(d.getDraft().getId());
		}
		//Set<Draft> pendingDraftsForEditor = new HashSet<>();
		Set<Draft> draftsToRemove = new HashSet<>();
		
		for(Draft d : allPendingDrafts) {
			for(DraftLog dl : editorDraftApprovals) {
				if(d.getId() == dl.getDraft().getId()) {
					//editor has already approved this draft
					draftsToRemove.add(d);
				}
			}
		}
		for(Draft d : draftsToRemove) {
			log.debug(d.getId());
		}
		for(Draft d : draftsToRemove) {
			allPendingDrafts.remove(d);
		}
		for(Draft d : allPendingDrafts) {
			log.debug(d.getId());
		}
		return allPendingDrafts;
	}

	@Override
	public List<Draft> listDraftsInOrder(Set<Draft> drafts) {
		List<Draft> dlist = new ArrayList<>();
		for(Draft d : drafts) {
			dlist.add(d);
		}
		Collections.sort(dlist);
		return dlist;
	}
}

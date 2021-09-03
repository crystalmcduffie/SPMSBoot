package com.SPMS.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorDraft;
import com.SPMS.beans.AuthorPitch;
import com.SPMS.beans.DChangeA2E;
import com.SPMS.beans.DChangeE2A;
import com.SPMS.beans.DMessageA2E;
import com.SPMS.beans.DMessageE2A;
import com.SPMS.beans.Draft;
import com.SPMS.beans.DraftChange;
import com.SPMS.beans.DraftMessage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.EditorPitch;
import com.SPMS.beans.PMessageA2E;
import com.SPMS.beans.PMessageE2A;
import com.SPMS.beans.PMessageE2E;
import com.SPMS.beans.PReviewA2E;
import com.SPMS.beans.PReviewE2A;
import com.SPMS.beans.PReviewE2E;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchMessage;
import com.SPMS.beans.PitchReview;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.MessageDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.data.hibernate.AuthorHibernate;
import com.SPMS.data.hibernate.EditorHibernate;
import com.SPMS.data.hibernate.MessageHibernate;
import com.SPMS.data.hibernate.PitchHibernate;

public class MessageServiceImpl implements MessageService{
	private static MessageDAO messageDAO;
	private static AuthorDAO authorDAO;
	private static EditorDAO editorDAO;
	private static PitchDAO pitchDAO;
	private static DraftDAO draftDAO;
	
	public MessageServiceImpl() {
		messageDAO = DAOFactory.getMessageDAO();
		authorDAO = DAOFactory.getAuthorDAO();
		editorDAO = DAOFactory.getEditorDAO();
		pitchDAO = DAOFactory.getPitchDAO();
		draftDAO = DAOFactory.getDraftDAO();
	}
	
	public PitchMessage getPitchMessageById(Integer id) {
		return messageDAO.getPitchMessageById(id);
	}
	
	public PitchReview getPitchReviewById(Integer id) {
		return messageDAO.getPitchReviewById(id);
	}
	public DraftChange getDraftChangeById(Integer id) {
		return messageDAO.getDraftChangeById(id);
	}
	
	@Override
	public void sendReviewRequestE2E(Editor se, Editor re, PitchMessage pm) {
		PitchReview pr = new PitchReview();
		pr.setEditor(se);
		pr.setPitch(pm.getPitch());
		
		PReviewE2E pree = new PReviewE2E();
		pree.setPitchReview(pr);
		pree.setReceiverEditor(re);
		pree.setSenderEditor(se);
		PMessageE2E pmee = new PMessageE2E();
		pmee.setPitchMessage(pm);
		pmee.setReceiverEditor(re);
		pmee.setSenderEditor(se);
		
		//create a pitch review request
		messageDAO.addPitchReview(pr);
		
		//create a notification for the receiving editor
		messageDAO.addToPReviewE2E(pree);
		
		//send message
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageE2E(pmee);
		
	}

	@Override
	public void sendReviewRequestE2A(Editor e, Author a, PitchReview pr, PitchMessage pm) {
		PReviewE2A prea = new PReviewE2A();
		prea.setPitchReview(pr);
		prea.setAuthor(a);
		prea.setEditor(e);
		PMessageE2A pmea = new PMessageE2A();
		pmea.setPitchMessage(pm);
		pmea.setAuthor(a);
		pmea.setEditor(e);
		
		//create a pitch review request
		messageDAO.addPitchReview(pr);
		
		//create a notification for the receiving author
		messageDAO.addToPReviewE2A(prea);
		
		//send message
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageE2A(pmea);
		
	}

	@Override
	public void sendReviewResponseE2E(Editor se, Editor re, PitchMessage pm, PitchReview pr) {
		PReviewE2E prees = new PReviewE2E();
		prees.setPitchReview(pr);
		prees.setReceiverEditor(re);
		prees.setSenderEditor(se);
		PMessageE2E pmeer = new PMessageE2E();
		pmeer.setPitchMessage(pm);
		pmeer.setReceiverEditor(re);
		pmeer.setSenderEditor(se);

	
		//notification that receiving editor initially sent
		PReviewE2E oldpree = messageDAO.getPReviewE2E(pr);
		//delete Pitch Review notification that the sending editor had
		//initially received, and delete the corresponding record
		messageDAO.deleteFromPReviewE2E(oldpree);
		
		//create new notification
		messageDAO.addToPReviewE2E(prees);
		
		//send message
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageE2E(pmeer);
		
	}

	@Override
	public void sendReviewResponseA2E(Author a, Editor e, PitchMessage pm, PitchReview pr) {
		PReviewA2E prae = new PReviewA2E();
		prae.setPitchReview(pr);
		prae.setAuthor(a);
		prae.setEditor(e);
		PMessageA2E pmae = new PMessageA2E();
		pmae.setPitchMessage(pm);
		pmae.setAuthor(a);
		pmae.setEditor(e);
		
		//get Pitch Review notification that sending author had received
		//and delete it
		PReviewE2A prea = messageDAO.getPReviewE2A(pr);
		messageDAO.deleteFromPReviewE2A(prea);
		
		//create new notification
		messageDAO.addToPReviewA2E(prae);
		
		//send message
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageA2E(pmae);
		
	}
	
	@Override
	public void sendReviewResponseE2A(Editor e, Author a, PitchMessage pm, PitchReview pr) {
		//Allow Editor to response to author instead of resolving the pitch review		
		PReviewE2A prea = new PReviewE2A();
		prea.setPitchReview(pr);
		prea.setAuthor(a);
		prea.setEditor(e);
		
		PMessageE2A pmea = new PMessageE2A();
		pmea.setAuthor(a);
		pmea.setEditor(e);
		pmea.setPitchMessage(pm);
		
		//delete Draft Change notification the author sent
		PReviewA2E prae = messageDAO.getPReviewA2E(pr);
		messageDAO.deleteFromPReviewA2E(prae);
		
		//create a notification for the author
		messageDAO.addToPReviewE2A(prea);
		
		//send message
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageE2A(pmea);
		
		
	}


	@Override
	public void sendChangeRequest(Editor e, Author a, DraftChange dc, DraftMessage dm) {
		DChangeE2A dcea = new DChangeE2A();
		dcea.setDraftChange(dc);
		dcea.setAuthor(a);
		dcea.setEditor(e);
		DMessageE2A dmea = new DMessageE2A();
		dmea.setDraftMessage(dm);
		dmea.setAuthor(a);
		dmea.setEditor(e);
		
		//create a draft change request
		messageDAO.addDraftChange(dc);
		
		//create a notification for the author
		messageDAO.addToDChangeE2A(dcea);
		
		//send message
		messageDAO.addDraftMessage(dm);
		messageDAO.addToDMessageE2A(dmea);
	}

	@Override
	public void sendChangeResponseA2E(Author a, Editor e, DraftMessage dm, DraftChange dc) {
		DChangeA2E dcae = new DChangeA2E();
		dcae.setDraftChange(dc);
		dcae.setAuthor(a);
		dcae.setEditor(e);
		DMessageA2E dmae = new DMessageA2E();
		dmae.setDraftMessage(dm);
		dmae.setAuthor(a);
		dmae.setEditor(e);
		
		//delete Draft Change notification that the editor had sent
		DChangeE2A dcea = messageDAO.getDChangeE2A(dc);
		messageDAO.deleteFromDChangeE2A(dcea);
		
		//create a notification for the editor
		messageDAO.addToDChangeA2E(dcae);
		
		//send message
		messageDAO.addDraftMessage(dm);
		messageDAO.addToDMessageA2E(dmae);
	}
	
	
	@Override
	public void sendChangeResponseE2A(Editor e, Author a, DraftMessage dm, DraftChange dc) {
		//Allow Editor to response to author instead of accepting the new draft
		DChangeE2A dcea = new DChangeE2A();
		dcea.setDraftChange(dc);
		dcea.setAuthor(a);
		dcea.setEditor(e);
		DMessageE2A dmea = new DMessageE2A();
		dmea.setDraftMessage(dm);
		dmea.setAuthor(a);
		dmea.setEditor(e);
		
		//delete Draft Change notification the author sent
		DChangeA2E dcae = messageDAO.getDChangeA2E(dc);
		messageDAO.deleteFromDChangeA2E(dcae);
		
		//create a notification for the author
		messageDAO.addToDChangeA2E(dcae);
		
		//send message
		messageDAO.addDraftMessage(dm);
		messageDAO.addToDMessageE2A(dmea);
		
		
	}

	@Override
	public void resolvePitchReviewE2E(Editor e, PitchReview pr) {
		PReviewE2E pree = messageDAO.getPReviewE2E(pr);
		messageDAO.deleteFromPReviewE2E(pree);
		
		//delete pitch review
		messageDAO.deletePitchReview(pr);
		
	}
	
	@Override
	public void resolvePitchReviewE2A(Editor e, PitchReview pr) {
		//delete notification that the author sent the editor
		PReviewA2E prae = messageDAO.getPReviewA2E(pr);
		//delete pitch review
		messageDAO.deletePitchReview(pr);
	}

	@Override
	public void acceptDraftChange(Editor e, DraftChange dc) {
		//delete notification that the author sent the editor
		DChangeA2E dcae = messageDAO.getDChangeA2E(dc);
		messageDAO.deleteFromDChangeA2E(dcae);
		//delete draft change
		messageDAO.deleteDraftChange(dc);
		
	}

	@Override
	public List<DraftMessage> listDraftMessagesInOrder(Set<DraftMessage> receivedDraftMessages,
			Set<DraftMessage> sentDraftMessages) {
		
		List<DraftMessage> dmlist = new ArrayList<>();
		for(DraftMessage dm : receivedDraftMessages) {
			dmlist.add(dm);
		}
		for(DraftMessage dm : sentDraftMessages) {
			dmlist.add(dm);
		}
		Collections.sort(dmlist);
		return dmlist;
	}

	@Override
	public List<PitchMessage> listPitchMessagesInOrder(Set<PitchMessage> receivedPitchMessages,
			Set<PitchMessage> sentPitchMessages) {
		// TODO Auto-generated method stub
		List<PitchMessage> pmlist = new ArrayList<>();
		for(PitchMessage pm : receivedPitchMessages) {
			pmlist.add(pm);
		}
		for(PitchMessage pm : sentPitchMessages) {
			pmlist.add(pm);
		}
		Collections.sort(pmlist);
		return pmlist;
	}

	@Override
	public Set<DraftMessage> getReceivedDraftMessages(Editor e) {
		return messageDAO.getReceivedDraftMessages(e);
	}

	@Override
	public Set<DraftMessage> getReceivedDraftMessages(Author a) {
		return messageDAO.getReceivedDraftMessages(a);
	}

	@Override
	public Set<DraftMessage> getSentDraftMessages(Editor e) {
		return messageDAO.getSentDraftMessages(e);
	}

	@Override
	public Set<DraftMessage> getSentDraftMessages(Author a) {
		return messageDAO.getSentDraftMessages(a);
	}

	@Override
	public Set<PitchMessage> getReceivedEditorPitchMessages(Editor e) {
		return messageDAO.getReceivedEditorPitchMessages(e);
	}

	@Override
	public Set<PitchMessage> getReceivedAuthorPitchMessages(Editor e) {
		return messageDAO.getReceivedAuthorPitchMessages(e);
	}

	@Override
	public Set<PitchMessage> getSentEditorPitchMessages(Editor e) {
		return messageDAO.getSentEditorPitchMessages(e);
	}

	@Override
	public Set<PitchMessage> getSentAuthorPitchMessages(Editor e) {
		return messageDAO.getSentAuthorPitchMessages(e);
	}

	@Override
	public Set<PitchMessage> getReceivedPitchMessages(Author a) {
		return messageDAO.getReceivedPitchMessages(a);
	}

	@Override
	public Set<PitchMessage> getSentPitchMessages(Author a) {
		return messageDAO.getSentPitchMessages(a);
	}

	@Override
	public Set<DraftChange> getReceivedDraftChange(Editor e) {
		return messageDAO.getReceivedDraftChange(e);
	}

	@Override
	public Set<DraftChange> getSentDraftChange(Editor e) {
		return messageDAO.getSentDraftChange(e);
	}

	@Override
	public Set<DraftChange> getReceivedDraftChange(Author a) {
		return messageDAO.getReceivedDraftChange(a);
	}

	@Override
	public Set<DraftChange> getSentDraftChange(Author a) {
		return messageDAO.getSentDraftChange(a);
	}

	@Override
	public Set<PitchReview> getReceivedEditorPitchReview(Editor e) {
		return messageDAO.getReceivedEditorPitchReview(e);
	}

	@Override
	public Set<PitchReview> getReceivedAuthorPitchReview(Editor e) {
		return messageDAO.getReceivedAuthorPitchReview(e);
	}

	@Override
	public Set<PitchReview> getSentEditorPitchReview(Editor e) {
		return messageDAO.getSentEditorPitchReview(e);
	}

	@Override
	public Set<PitchReview> getSentAuthorPitchReview(Editor e) {
		return messageDAO.getSentAuthorPitchReview(e);
	}

	@Override
	public Set<PitchReview> getReceivedPitchReview(Author a) {
		return messageDAO.getReceivedPitchReview(a);
	}

	@Override
	public Set<PitchReview> getSentPitchReview(Author a) {
		return messageDAO.getSentPitchReview(a);
	}
	@Override
	public void sendPME2A(PitchMessage pm, Editor e) {
		Pitch p = new Pitch();
		AuthorPitch ap = pitchDAO.getAuthorPitch(p);
		Author a = ap.getAuthor();
		PMessageE2A pmea = new PMessageE2A();
		pmea.setPitchMessage(pm);
		pmea.setAuthor(a);
		pmea.setEditor(e);
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageE2A(pmea);
		
		
	}
	@Override
	public void sendPMA2E(PitchMessage pm, Author a) {
		Pitch p = new Pitch();
		EditorPitch ep = pitchDAO.getEditorPitch(p);
		Editor e = ep.getEditor();
		PMessageA2E pmae = new PMessageA2E();
		pmae.setPitchMessage(pm);
		pmae.setAuthor(a);
		pmae.setEditor(e);
		messageDAO.addPitchMessage(pm);
		messageDAO.addToPMessageA2E(pmae);
		
	}

	@Override
	public void sendDME2A(DraftMessage dm, Editor e) {
		Draft d = new Draft();
		AuthorDraft ad = draftDAO.getAuthorDraft(d);
		Author a = ad.getAuthor();
		DMessageE2A dmea = new DMessageE2A();
		dmea.setDraftMessage(dm);
		dmea.setAuthor(a);
		dmea.setEditor(e);
		messageDAO.addDraftMessage(dm);
		messageDAO.addToDMessageE2A(dmea);
		
	}



}

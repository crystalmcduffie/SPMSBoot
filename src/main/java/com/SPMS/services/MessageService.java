package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Author;
import com.SPMS.beans.DraftChange;
import com.SPMS.beans.DraftMessage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.PitchMessage;
import com.SPMS.beans.PitchReview;

public interface MessageService {
	public PitchMessage getPitchMessageById(Integer id);
	public PitchReview getPitchReviewById(Integer id);
	public DraftChange getDraftChangeById(Integer id);
	
	public void sendReviewRequestE2E(Editor se, Editor re, PitchReview pr, PitchMessage pm);
	public void sendReviewRequestE2A(Editor e, Author a, PitchReview pr, PitchMessage pm);
	public void sendReviewResponseE2E(Editor se, Editor re,PitchMessage pm, PitchReview pr);
	public void sendReviewResponseA2E(Author a, Editor e, PitchMessage pm, PitchReview pr);
	public void sendReviewResponseE2A(Editor e, Author a, PitchMessage pm, PitchReview pr);
	public void resolvePitchReviewE2E(Editor e, PitchReview pr);
	public void resolvePitchReviewE2A(Editor e, PitchReview pr);
	public void sendChangeRequest(Editor e, Author a, DraftChange dc, DraftMessage dm);
	public void sendChangeResponseA2E(Author a, Editor e, DraftMessage dm, DraftChange dc);
	public void sendChangeResponseE2A(Editor e, Author a, DraftMessage dm, DraftChange dc);
	public void acceptDraftChange(Editor e, DraftChange dc);
	public List<DraftMessage> listDraftMessagesInOrder(Set<DraftMessage> receivedDraftMessages, Set<DraftMessage> sentDraftMessages);
	public List<PitchMessage> listPitchMessagesInOrder(Set<PitchMessage> receivedPitchMessages, Set<PitchMessage> sentPitchMessages);
	
	public Set<DraftMessage> getReceivedDraftMessages(Editor e);
	public Set<DraftMessage> getReceivedDraftMessages(Author a);
	public Set<DraftMessage> getSentDraftMessages(Editor e);
	public Set<DraftMessage> getSentDraftMessages(Author a);
	
	public Set<PitchMessage> getReceivedEditorPitchMessages(Editor e);
	public Set<PitchMessage> getReceivedAuthorPitchMessages(Editor e);
	public Set<PitchMessage> getSentEditorPitchMessages(Editor e);
	public Set<PitchMessage> getSentAuthorPitchMessages(Editor e);
	
	public Set<PitchMessage> getReceivedPitchMessages(Author a);
	public Set<PitchMessage> getSentPitchMessages(Author a);
	
	public Set<DraftChange> getReceivedDraftChange(Editor e);
	public Set<DraftChange> getSentDraftChange(Editor e);
	public Set<DraftChange> getReceivedDraftChange(Author a);
	public Set<DraftChange> getSentDraftChange(Author a);
	
	public Set<PitchReview> getReceivedEditorPitchReview(Editor e);
	public Set<PitchReview> getReceivedAuthorPitchReview(Editor e);
	public Set<PitchReview> getSentEditorPitchReview(Editor e);
	public Set<PitchReview> getSentAuthorPitchReview(Editor e);
	
	public Set<PitchReview> getReceivedPitchReview(Author a);
	public Set<PitchReview> getSentPitchReview(Author a);
	
	public void sendPME2A(PitchMessage pm, Editor e);
	public void sendPMA2E(PitchMessage pm, Author a);
	public void sendDME2A(DraftMessage dm, Editor e);
}

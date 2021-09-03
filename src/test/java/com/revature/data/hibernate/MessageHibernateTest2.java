package com.revature.data.hibernate;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.SPMS.beans.Author;
import com.SPMS.beans.DraftChange;
import com.SPMS.beans.DraftMessage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Genre;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchMessage;
import com.SPMS.beans.PitchReview;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.hibernate.PitchHibernate;
import com.SPMS.services.AuthorService;
import com.SPMS.services.AuthorServiceImpl;
import com.SPMS.services.DraftService;
import com.SPMS.services.DraftServiceImpl;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;
import com.SPMS.services.MessageService;
import com.SPMS.services.MessageServiceImpl;
import com.SPMS.services.PitchService;
import com.SPMS.services.PitchServiceImpl;

public class MessageHibernateTest2 {
	private static PitchService pitchServ;
	private static AuthorService authorServ;
	
	private static DraftService draftServ;
	private static EditorService editorServ;
	private static MessageService messageServ;
	
	private PitchHibernate pitchDAO = new PitchHibernate();
	private static Logger log = Logger.getLogger(MessageHibernateTest2.class);
	
	@BeforeAll
	public static void setup() {
		pitchServ = new PitchServiceImpl();
		authorServ = new AuthorServiceImpl();
		
		draftServ = new DraftServiceImpl();
		editorServ = new EditorServiceImpl();
		messageServ = new MessageServiceImpl();
	}
	
//	@Test void testGetPitch() {
//		Pitch pitch= pitchServ.getPitch(1);
//		log.debug(pitch);
//	}
//	
//	@Test
//	public void testCreatePitch() {
//		Author a = authorServ.getByUsername("derrick");
//		Pitch p = new Pitch();
//		StoryType st = new StoryType();
//		st.setStoryType("novel");
//		st.setId(1);
//		st.setPoints(50);
//		p.setStoryType(st);
//		p.setStoryTitle("test");
//		Genre g = new Genre();
//		g.setName("romance");
//		g.setId(2);
//		p.setGenre(g);
//		log.debug(pitchServ.createPitch(a, p));
//		log.debug(p.getStage().getName());
//	}
//	
//	@Test 
//	public void testGetPitchStage(){
//		PitchStage ps = pitchDAO.getPitchStage("assistant_editor");
//		log.debug(ps);
//	}
//	@Test
//	public void testApprovePitch() {
//		Pitch p = pitchServ.getPitch(14);
//		pitchServ.approvePitch(p);
//	}
//	
//	@Test
//	public void testRejectPitch() {
//		Pitch p = pitchServ.getPitch(14);
//		pitchServ.rejectPitch(p);
//	}
//	
//	@Test
//	public void testCancelPitch() {
//		Pitch p = pitchServ.getPitch(15);
//		pitchServ.cancelPitch(p);
//	}
//	
//	@Test
//	public void testupdatePitch() {
//		Pitch pitch = pitchServ.getPitch(id);
//	}
	
	@Test
	public void testSortPitches() {
		Author a = authorServ.getByUsername("derrick");
		List<Pitch> orderedPitches = pitchServ.listPitchesInOrder(a.getPitches());
		for(Pitch p : orderedPitches) {
			log.debug(p.getId());
		}
	}
	
	@Test
	public static void testSendReviewRequestE2E() {
		Editor se = editorServ.getByUsername("kate");
		Editor re = editorServ.getByUsername("brianna");
		
		Pitch p = pitchServ.getPitch(25);
		
		PitchReview pr = new PitchReview();
		pr.setEditor(se);
		pr.setPitch(p);
		
		PitchMessage pm = new PitchMessage();
		pm.setMessage("Hey Brianna, I have some questions about this pitch that you approved. Could you send over some more details to me?");
		pm.setPitch(p);
		
		messageServ.sendReviewRequestE2E(se, re, pr, pm);

		printEditorEPMAndEPR(se);
		printEditorEPMAndEPR(re);
		
	}
	
////
//@Test
//public static void resolvePitchReviewE2E() {
//	Editor se = editorServ.getByUsername("kate");
//	Editor re = editorServ.getByUsername("brianna");
//	messageServ.resolvePitchReviewE2E(se, re);
//	
//	printEditorEPMAndEPR(se);
//	printEditorEPMAndEPR(re);
//}
//
//
//@Test
//public static void testSendReviewRequestE2A() {
//	Editor e = editorServ.getByUsername("elizabeth");
//	Author a = authorServ.getByUsername("bailey");
//	
//	Pitch p = pitchServ.getPitch(20);
//	
//	PitchReview pr = new PitchReview();
//	pr.setEditor(e);
//	pr.setPitch(p);
//	
//	PitchMessage pm = new PitchMessage();
//	pm.setMessage("Hi Bailey. Could you explain more of what the main character's motivation would be?");
//	pm.setPitch(p);
//	
//	messageServ.sendReviewRequestE2A(e, a, pr, pm);
//	
//	printEditorEPMAndEPR(e);
//	printAuthorPMAndPR(a);
//}
//
//
//@Test
//public static void testSendReviewResponseA2E() {
//	Editor e = editorServ.getByUsername("elizabeth");
//	Author a = authorServ.getByUsername("bailey");
//	
//	Pitch p = pitchServ.getPitch(20);
//	
//	PitchMessage pm = new PitchMessage();
//	pm.setMessage("Hi Elizabeth. The main character is driven by the loss of his mother. It isn't explained until the middle chapters.");
//	pm.setPitch(p);
//	
//	messageServ.sendReviewResponseA2E(a, e, pm);
//	
//	printEditorEPMAndEPR(e);
//	printAuthorPMAndPR(a);
//}
//@Test
//public static void testSendReviewResponseE2A() {
//	Editor e = editorServ.getByUsername("elizabeth");
//	Author a = authorServ.getByUsername("bailey");
//	
//	Pitch p = pitchServ.getPitch(20);
//	
//	PitchMessage pm = new PitchMessage();
//	pm.setMessage("Oh I get it now. Thanks.");
//	
//	messageServ.sendReviewResponseE2A(e, a, pm);
//	
//	printEditorEPMAndEPR(e);
//	printAuthorPMAndPR(a);
//}
//@Test
//public static void resolvePitchReviewE2A() {
//	Editor e = editorServ.getByUsername("elizabeth");
//	Author a = authorServ.getByUsername("bailey");
//	messageServ.resolvePitchReviewE2A(e);
//	
//	printEditorEPMAndEPR(e);
//	printAuthorPMAndPR(a);
//}
//
//@Test
//public static void testSendChangeRequest() {
//	Editor e = editorServ.getByUsername("thomas");
//	Author a = authorServ.getByUsername("derrick");
//	
//	Draft d = draftServ.getDraft(12);
//	DraftChange dc = new DraftChange();
//	dc.setEditor(e);
//	dc.setDraft(d);
//	
//	DraftMessage dm = new DraftMessage();
//	dm.setDraft(d);
//	dm.setMessage("Hi Derrick. I think this draft could use some extra pages at the end to give it some more closure.");
//	
//	messageServ.sendChangeRequest(e, a, dc, dm);
//	
//	printEditorDMAndDC(e);
//	printAuthorDMAndDC(a);
//}
//@Test
//public static void testSendChangeResponseA2E() {
//	Editor e = editorServ.getByUsername("thomas");
//	Author a = authorServ.getByUsername("derrick");
//	
//	Draft d = draftServ.getDraft(12);
//	
//	DraftMessage dm = new DraftMessage();
//	dm.setDraft(d);
//	dm.setMessage("Hi Thomas. I extended the ending.");
//	
//	messageServ.sendChangeResponseA2E(a, e, dm);
//	
//	printEditorDMAndDC(e);
//	printAuthorDMAndDC(a);
//}
//@Test
//public static void testSendChangeResponseE2A() {
//	Editor e = editorServ.getByUsername("thomas");
//	Author a = authorServ.getByUsername("derrick");
//	
//	Draft d = draftServ.getDraft(12);
//	
//	DraftMessage dm = new DraftMessage();
//	dm.setDraft(d);
//	dm.setMessage("Thanks Derrick. This will do.");
//	
//	messageServ.sendChangeResponseA2E(a, e, dm);
//	
//	printEditorDMAndDC(e);
//	printAuthorDMAndDC(a);
//}
//@Test
//public static void acceptDraftChange() {
//	Editor e = editorServ.getByUsername("thomas");
//	Author a = authorServ.getByUsername("derrick");
//	
//	messageServ.acceptDraftChange(e);
//	
//	printEditorDMAndDC(e);
//	printAuthorDMAndDC(a);
//}
//@Test
//public static void testListDraftMessagesInOrder() {
//	Editor e = editorServ.getByUsername("thomas");
//	Set<DraftMessage> RDM = e.getReceivedDraftMessages();
//	Set<DraftMessage> SDM = e.getSentDraftMessages();
//	messageServ.listDraftMessagesInOrder(RDM, SDM);
//}
//@Test
//public static void testListPitchMessagesInOrder() {
//	Author a = authorServ.getByUsername("bailey");
//	Set<PitchMessage> RPM = a.getReceivedPitchMessages();
//	Set<PitchMessage> SPM = a.getSentPitchMessages();
//}

public static void printEditorEPMAndEPR(Editor e){
	Set<PitchMessage> SPM = messageServ.getSentEditorPitchMessages(e);
	Set<PitchMessage> RPM = messageServ.getReceivedEditorPitchMessages(e);
	Set<PitchReview> SPR = messageServ.getSentEditorPitchReview(e);
	Set<PitchReview> RPR = messageServ.getReceivedEditorPitchReview(e);
	
	log.debug(e.getUsername() + "'s sent pitch messages");
	for(PitchMessage pm : SPM) {
		log.debug(pm.getMessage());
	}
	log.debug(e.getUsername() + "'s received pitch messages");
	for(PitchMessage pm : RPM) {
		log.debug(pm.getMessage());
	}
	log.debug(e.getUsername() + "'s sent pitch reviews");
	for(PitchReview pr : SPR) {
		log.debug(pr.getEditor().getUsername());
		log.debug(pr.getPitch().getId());
	}
	
	log.debug(e.getUsername() + "'s received pitch reviews");
	for(PitchReview pr : RPR) {
		log.debug(pr.getEditor().getUsername());
		log.debug(pr.getPitch().getId());
	}
}

public static void printEditorAPMAndAPR(Editor e){
	Set<PitchMessage> SPM = messageServ.getSentAuthorPitchMessages(e);
	Set<PitchMessage> RPM = messageServ.getReceivedAuthorPitchMessages(e);
	Set<PitchReview> SPR = messageServ.getSentAuthorPitchReview(e);
	Set<PitchReview> RPR = messageServ.getReceivedAuthorPitchReview(e);
	
	log.debug(e.getUsername() + "'s sent pitch messages");
	for(PitchMessage pm : SPM) {
		log.debug(pm.getMessage());
	}
	log.debug(e.getUsername() + "'s received pitch messages");
	for(PitchMessage pm : RPM) {
		log.debug(pm.getMessage());
	}
	log.debug(e.getUsername() + "'s sent pitch reviews");
	for(PitchReview pr : SPR) {
		log.debug(pr.getEditor().getUsername());
		log.debug(pr.getPitch().getId());
	}
	
	log.debug(e.getUsername() + "'s received pitch reviews");
	for(PitchReview pr : RPR) {
		log.debug(pr.getEditor().getUsername());
		log.debug(pr.getPitch().getId());
	}
}

public static void printAuthorPMAndPR(Author a){
	Set<PitchMessage> SPM = messageServ.getSentPitchMessages(a);
	Set<PitchMessage> RPM = messageServ.getReceivedPitchMessages(a);
	Set<PitchReview> SPR = messageServ.getSentPitchReview(a);
	Set<PitchReview> RPR = messageServ.getReceivedPitchReview(a);
	
	log.debug(a.getUsername() + "'s sent pitch messages");
	for(PitchMessage pm : SPM) {
		log.debug(pm.getMessage());
	}
	log.debug(a.getUsername() + "'s received pitch messages");
	for(PitchMessage pm : RPM) {
		log.debug(pm.getMessage());
	}
	log.debug(a.getUsername() + "'s pitch review requests");
	for(PitchReview pr : SPR) {
		log.debug(pr.getEditor().getUsername());
		log.debug(pr.getPitch().getId());
	}
	
	log.debug(a.getUsername() + "'s pitch review responses");
	for(PitchReview pr : RPR) {
		log.debug(pr.getEditor().getUsername());
		log.debug(pr.getPitch().getId());
	}
}

public static void printEditorDMAndDC(Editor e){
	Set<DraftMessage> SDM = messageServ.getSentDraftMessages(e);
	Set<DraftMessage> RDM = messageServ.getReceivedDraftMessages(e);
	Set<DraftChange> SDC = messageServ.getSentDraftChange(e);
	Set<DraftChange> RDC = messageServ.getReceivedDraftChange(e);
	
	log.debug(e.getUsername() + "'s sent draft messages");
	for(DraftMessage dm : SDM) {
		log.debug(dm.getMessage());
	}
	log.debug(e.getUsername() + "'s received draft messages");
	for(DraftMessage dm : RDM) {
		log.debug(dm.getMessage());
	}
	log.debug(e.getUsername() + "'s draft change requests");
	for(DraftChange dc : SDC) {
		log.debug(dc.getEditor().getUsername());
		log.debug(dc.getDraft().getId());
	}
	
	log.debug(e.getUsername() + "'s draft change responses");
	for(DraftChange dc : RDC) {
		log.debug(dc.getEditor().getUsername());
		log.debug(dc.getDraft().getId());
	}
}

public static void printAuthorDMAndDC(Author a){
	Set<DraftMessage> SDM = messageServ.getSentDraftMessages(a);
	Set<DraftMessage> RDM = messageServ.getReceivedDraftMessages(a);
	Set<DraftChange> SDC = messageServ.getSentDraftChange(a);
	Set<DraftChange> RDC = messageServ.getReceivedDraftChange(a);
	
	log.debug(a.getUsername() + "'s sent draft messages");
	for(DraftMessage dm : SDM) {
		log.debug(dm.getMessage());
	}
	log.debug(a.getUsername() + "'s received draft messages");
	for(DraftMessage dm : RDM) {
		log.debug(dm.getMessage());
	}
	log.debug(a.getUsername() + "'s draft change requests");
	for(DraftChange dc : SDC) {
		log.debug(dc.getEditor().getUsername());
		log.debug(dc.getDraft().getId());
	}
	
	log.debug(a.getUsername() + "'s draft change responses");
	for(DraftChange dc : RDC) {
		log.debug(dc.getEditor().getUsername());
		log.debug(dc.getDraft().getId());
	}
}
}

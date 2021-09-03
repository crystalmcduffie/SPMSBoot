package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.SPMS.beans.Author;
import com.SPMS.beans.DChangeA2E;
import com.SPMS.beans.DChangeE2A;
import com.SPMS.beans.DMessageA2E;
import com.SPMS.beans.DMessageE2A;
import com.SPMS.beans.DraftChange;
import com.SPMS.beans.DraftMessage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.PMessageA2E;
import com.SPMS.beans.PMessageE2A;
import com.SPMS.beans.PMessageE2E;
import com.SPMS.beans.PReviewA2E;
import com.SPMS.beans.PReviewE2A;
import com.SPMS.beans.PReviewE2E;
import com.SPMS.beans.PitchMessage;
import com.SPMS.beans.PitchReview;
import com.SPMS.data.MessageDAO;
import com.SPMS.utils.HibernateUtil;

public class MessageHibernate implements MessageDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	public PitchMessage getPitchMessageById(Integer id) {
		Session s = hu.getSession();
		PitchMessage pm = s.get(PitchMessage.class, id);
		s.close();
		return pm;
	}
	
	@Override 
	public PitchReview getPitchReviewById(Integer id) {
		Session s = hu.getSession();
		PitchReview pr = s.get(PitchReview.class, id);
		s.close();
		return pr;
	}
	
	public DraftChange getDraftChangeById(Integer id) {
		Session s = hu.getSession();
		DraftChange dc = s.get(DraftChange.class, id);
		s.close();
		return dc;
	}
	
	@Override
	public void addPitchMessage(PitchMessage pm) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pm);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToPMessageA2E(PMessageA2E pmae) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pmae);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToPMessageE2A(PMessageE2A pmea) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pmea);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToPMessageE2E(PMessageE2E pmee) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pmee);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addPitchReview(PitchReview pr) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pr);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToPReviewA2E(PReviewA2E prae) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(prae);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToPReviewE2A(PReviewE2A prea) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(prea);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToPReviewE2E(PReviewE2E pree) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pree);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void deletePitchReview(PitchReview pr) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(pr);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void deleteFromPReviewA2E(PReviewA2E prae) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(prae);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void deleteFromPReviewE2A(PReviewE2A prea) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(prea);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void deleteFromPReviewE2E(PReviewE2E pree) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(pree);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void addDraftMessage(DraftMessage dm) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dm);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToDMessageA2E(DMessageA2E dmae) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dmae);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToDMessageE2A(DMessageE2A dmea) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dmea);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addDraftChange(DraftChange dc) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dc);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToDChangeA2E(DChangeA2E dcae) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dcae);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToDChangeE2A(DChangeE2A dcea) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dcea);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void deleteDraftChange(DraftChange dc) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(dc);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void deleteFromDChangeA2E(DChangeA2E dcae) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(dcae);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void deleteFromDChangeE2A(DChangeE2A dcea) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(dcea);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}
		
	}

//	@Override
//	public PitchReview getPitchReview(Editor e) {
//		Session s = hu.getSession();
//		String query = "FROM PitchReview where editor.id = :id";
//		Query<PitchReview> q = s.createQuery(query, PitchReview.class);
//		q.setParameter("id", e.getId());
//		PitchReview pr= q.getSingleResult();
//		s.close();
//		return pr;
//	}

	@Override
	public PReviewA2E getPReviewA2E(PitchReview pr) {
		Session s = hu.getSession();
		String query = "FROM PReviewA2E where pitchReview.id = :id";
		Query<PReviewA2E> q = s.createQuery(query, PReviewA2E.class);
		q.setParameter("id", pr.getId());
		PReviewA2E prae= q.getSingleResult();
		s.close();
		return prae; 
	}

	@Override
	public PReviewE2A getPReviewE2A(PitchReview pr) {
		Session s = hu.getSession();
		String query = "FROM PReviewE2A where pitchReview.id = :id";
		Query<PReviewE2A> q = s.createQuery(query, PReviewE2A.class);
		q.setParameter("id", pr.getId());
		PReviewE2A prea= q.getSingleResult();
		s.close();
		return prea;
	}

	@Override
	public PReviewE2E getPReviewE2E(PitchReview pr) {
		Session s = hu.getSession();
		String query = "FROM PReviewE2E where pitchReview.id = :id";
		Query<PReviewE2E> q = s.createQuery(query, PReviewE2E.class);
		q.setParameter("id", pr.getId());
		PReviewE2E preer = q.getSingleResult();
		s.close();
		return preer;
	}

//	@Override
//	public DraftChange getDraftChange(Editor e) {
//		Session s = hu.getSession();
//		String query = "FROM DraftChange where editor.id = :id";
//		Query<DraftChange> q = s.createQuery(query, DraftChange.class);
//		q.setParameter("id", e.getId());
//		DraftChange dc= q.getSingleResult();
//		s.close();
//		return dc;
//	}

	@Override
	public DChangeA2E getDChangeA2E(DraftChange dc) {
		Session s = hu.getSession();
		String query = "FROM DChangeA2E where draftChange.id = :id";
		Query<DChangeA2E> q = s.createQuery(query, DChangeA2E.class);
		q.setParameter("id",dc.getId());
		DChangeA2E dcae= q.getSingleResult();
		s.close();
		return dcae;
	}

	@Override
	public DChangeE2A getDChangeE2A(DraftChange dc) {
		Session s = hu.getSession();
		String query = "FROM DChangeE2A where draftChange.id = :id";
		Query<DChangeE2A> q = s.createQuery(query, DChangeE2A.class);
		q.setParameter("id",dc.getId());
		DChangeE2A dcea= q.getSingleResult();
		s.close();
		return dcea;
	}

	@Override
	public Set<DraftMessage> getReceivedDraftMessages(Editor e) {
		Session s = hu.getSession();
		String query = "FROM DMessageA2E where editor.id = :id";
		Query<DMessageA2E> q1 = s.createQuery(query, DMessageA2E.class);
		q1.setParameter("id", e.getId());
		List<DMessageA2E> dmaesList = q1.getResultList();
		Set<DMessageA2E> dmaes = new HashSet<>();
		dmaes.addAll(dmaesList);
		
		Set<DraftMessage> DMs = new HashSet<>();
		for(DMessageA2E dmae : dmaes) {
			query = "FROM DraftMessage where id = :id";
			Query<DraftMessage> q2 = s.createQuery(query, DraftMessage.class);
			q2.setParameter("id", dmae.getDraftMessage().getId());
			DraftMessage dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}

	@Override
	public Set<DraftMessage> getReceivedDraftMessages(Author a) {
		Session s = hu.getSession();
		String query = "FROM DMessageE2A where author.id = :id";
		Query<DMessageE2A> q1 = s.createQuery(query, DMessageE2A.class);
		q1.setParameter("id", a.getId());
		List<DMessageE2A> dmeasList = q1.getResultList();
		Set<DMessageE2A> dmeas = new HashSet<>();
		dmeas.addAll(dmeasList);
		
		Set<DraftMessage> DMs = new HashSet<>();
		for(DMessageE2A dmae : dmeas) {
			query = "FROM DraftMessage where id = :id";
			Query<DraftMessage> q2 = s.createQuery(query, DraftMessage.class);
			q2.setParameter("id", dmae.getDraftMessage().getId());
			DraftMessage dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}

	@Override
	public Set<DraftMessage> getSentDraftMessages(Editor e) {
		Session s = hu.getSession();
		String query = "FROM DMessageE2A where editor.id = :id";
		Query<DMessageE2A> q1 = s.createQuery(query, DMessageE2A.class);
		q1.setParameter("id", e.getId());
		List<DMessageE2A> dmeasList = q1.getResultList();
		Set<DMessageE2A> dmeas = new HashSet<>();
		dmeas.addAll(dmeasList);
		
		Set<DraftMessage> DMs = new HashSet<>();
		for(DMessageE2A dmae : dmeas) {
			query = "FROM DraftMessage where id = :id";
			Query<DraftMessage> q2 = s.createQuery(query, DraftMessage.class);
			q2.setParameter("id", dmae.getDraftMessage().getId());
			DraftMessage dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}
	
	@Override
	public Set<DraftMessage> getSentDraftMessages(Author a) {
		Session s = hu.getSession();
		String query = "FROM DMessageA2E where author.id = :id";
		Query<DMessageA2E> q1 = s.createQuery(query, DMessageA2E.class);
		q1.setParameter("id", a.getId());
		List<DMessageA2E> dmaesList = q1.getResultList();
		Set<DMessageA2E> dmaes = new HashSet<>();
		dmaes.addAll(dmaesList);
		
		Set<DraftMessage> DMs = new HashSet<>();
		for(DMessageA2E dmae : dmaes) {
			query = "FROM DraftMessage where id = :id";
			Query<DraftMessage> q2 = s.createQuery(query, DraftMessage.class);
			q2.setParameter("id", dmae.getDraftMessage().getId());
			DraftMessage dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}
	
	public Set<PitchMessage> getReceivedEditorPitchMessages(Editor e){
		Session s = hu.getSession();
		String query = "FROM PMessageE2E where receiverEditor.id = :id";
		Query<PMessageE2E> q1 = s.createQuery(query, PMessageE2E.class);
		q1.setParameter("id", e.getId());
		List<PMessageE2E> pmeersList = q1.getResultList();
		Set<PMessageE2E> pmeers = new HashSet<>();
		pmeers.addAll(pmeersList);
		
		Set<PitchMessage> PMs = new HashSet<>();
		for(PMessageE2E pmeer : pmeers) {
			PitchMessage pm = getPitchMessageById(pmeer.getPitchMessage().getId());
			PMs.add(pm);
		}
		
		return PMs;
	}
	public Set<PitchMessage> getReceivedAuthorPitchMessages(Editor e){
		Session s = hu.getSession();
		String query = "FROM PMessageA2E where editor.id = :id";
		Query<PMessageA2E> q1 = s.createQuery(query, PMessageA2E.class);
		q1.setParameter("id", e.getId());
		List<PMessageA2E> pmaesList = q1.getResultList();
		Set<PMessageA2E> pmaes = new HashSet<>();
		pmaes.addAll(pmaesList);
		
		Set<PitchMessage> PMs = new HashSet<>();
		for(PMessageA2E pmae : pmaes) {
			query = "FROM PitchMessage where id = :id";
			Query<PitchMessage> q2 = s.createQuery(query, PitchMessage.class);
			q2.setParameter("id", pmae.getPitchMessage().getId());
			PitchMessage pm = q2.getSingleResult();
			PMs.add(pm);
		}
		return PMs;
	}
	public Set<PitchMessage> getSentEditorPitchMessages(Editor e){
		Session s = hu.getSession();
		String query = "FROM PMessageE2E where senderEditor.id = :id";
		Query<PMessageE2E> q1 = s.createQuery(query, PMessageE2E.class);
		q1.setParameter("id", e.getId());
		List<PMessageE2E> pmeersList = q1.getResultList();
		Set<PMessageE2E> pmeers = new HashSet<>();
		pmeers.addAll(pmeersList);

		Set<PitchMessage> PMs = new HashSet<>();
		for(PMessageE2E pmeer : pmeers) {
			PitchMessage pm = getPitchMessageById(pmeer.getPitchMessage().getId());
			PMs.add(pm);
		}
		s.close();
		
		return PMs;
	}
	public Set<PitchMessage> getSentAuthorPitchMessages(Editor e){
		Session s = hu.getSession();
		String query = "FROM PMessageE2A where editor.id = :id";
		Query<PMessageE2A> q1 = s.createQuery(query, PMessageE2A.class);
		q1.setParameter("id", e.getId());
		List<PMessageE2A> pmeasList = q1.getResultList();
		Set<PMessageE2A> pmeas = new HashSet<>();
		pmeas.addAll(pmeasList);
		
		Set<PitchMessage> PMs = new HashSet<>();
		for(PMessageE2A pmea : pmeas) {
			query = "FROM PitchMessage where id = :id";
			Query<PitchMessage> q2 = s.createQuery(query, PitchMessage.class);
			q2.setParameter("id", pmea.getPitchMessage().getId());
			PitchMessage pm = q2.getSingleResult();
			PMs.add(pm);
		}
		return PMs;
	}
	
	public Set<PitchMessage> getReceivedPitchMessages(Author a){
		Session s = hu.getSession();
		String query = "FROM PMessageE2A where author.id = :id";
		Query<PMessageE2A> q1 = s.createQuery(query, PMessageE2A.class);
		q1.setParameter("id", a.getId());
		List<PMessageE2A> pmaesList = q1.getResultList();
		Set<PMessageE2A> pmaes = new HashSet<>();
		pmaes.addAll(pmaesList);
		
		Set<PitchMessage> PMs = new HashSet<>();
		for(PMessageE2A pmae : pmaes) {
			query = "FROM PitchMessage where id = :id";
			Query<PitchMessage> q2 = s.createQuery(query, PitchMessage.class);
			q2.setParameter("id", pmae.getPitchMessage().getId());
			PitchMessage pm = q2.getSingleResult();
			PMs.add(pm);
		}
		return PMs;
	}
	public Set<PitchMessage> getSentPitchMessages(Author a){
		Session s = hu.getSession();
		String query = "FROM PMessageA2E where author.id = :id";
		Query<PMessageA2E> q1 = s.createQuery(query, PMessageA2E.class);
		q1.setParameter("id", a.getId());
		List<PMessageA2E> pmaesList = q1.getResultList();
		Set<PMessageA2E> pmaes = new HashSet<>();
		pmaes.addAll(pmaesList);
		
		Set<PitchMessage> PMs = new HashSet<>();
		for(PMessageA2E pmae : pmaes) {
			query = "FROM PitchMessage where id = :id";
			Query<PitchMessage> q2 = s.createQuery(query, PitchMessage.class);
			q2.setParameter("id", pmae.getPitchMessage().getId());
			PitchMessage pm = q2.getSingleResult();
			PMs.add(pm);
		}
		return PMs;
	}
	
	public Set<DraftChange> getReceivedDraftChange(Editor e){
		Session s = hu.getSession();
		String query = "FROM DChangeA2E where editor.id = :id";
		Query<DChangeA2E> q1 = s.createQuery(query, DChangeA2E.class);
		q1.setParameter("id", e.getId());
		List<DChangeA2E> dcaesList = q1.getResultList();
		Set<DChangeA2E> dcaes = new HashSet<>();
		dcaes.addAll(dcaesList);
		
		Set<DraftChange> DMs = new HashSet<>();
		for(DChangeA2E dcae : dcaes) {
			query = "FROM DraftChange where id = :id";
			Query<DraftChange> q2 = s.createQuery(query, DraftChange.class);
			q2.setParameter("id", dcae.getDraftChange().getId());
			DraftChange dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}
	public Set<DraftChange> getSentDraftChange(Editor e){
		Session s = hu.getSession();
		String query = "FROM DChangeE2A where editor.id = :id";
		Query<DChangeE2A> q1 = s.createQuery(query, DChangeE2A.class);
		q1.setParameter("id", e.getId());
		List<DChangeE2A> dceasList = q1.getResultList();
		Set<DChangeE2A> dceas = new HashSet<>();
		dceas.addAll(dceasList);
		
		Set<DraftChange> DMs = new HashSet<>();
		for(DChangeE2A dcea : dceas) {
			query = "FROM DraftChange where id = :id";
			Query<DraftChange> q2 = s.createQuery(query, DraftChange.class);
			q2.setParameter("id", dcea.getDraftChange().getId());
			DraftChange dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}
	public Set<DraftChange> getReceivedDraftChange(Author a){
		Session s = hu.getSession();
		String query = "FROM DChangeE2A where author.id = :id";
		Query<DChangeE2A> q1 = s.createQuery(query, DChangeE2A.class);
		q1.setParameter("id", a.getId());
		List<DChangeE2A> dceasList = q1.getResultList();
		Set<DChangeE2A> dceas = new HashSet<>();
		dceas.addAll(dceasList);
		
		Set<DraftChange> DMs = new HashSet<>();
		for(DChangeE2A dcea : dceas) {
			query = "FROM DraftChange where id = :id";
			Query<DraftChange> q2 = s.createQuery(query, DraftChange.class);
			q2.setParameter("id", dcea.getDraftChange().getId());
			DraftChange dm = q2.getSingleResult();
			DMs.add(dm);
		}
		return DMs;
	}
	public Set<DraftChange> getSentDraftChange(Author a){
		Session s = hu.getSession();
		String query = "FROM DChangeA2E where author.id = :id";
		Query<DChangeA2E> q1 = s.createQuery(query, DChangeA2E.class);
		q1.setParameter("id", a.getId());
		List<DChangeA2E> dcaesList = q1.getResultList();
		Set<DChangeA2E> dcaes = new HashSet<>();
		dcaes.addAll(dcaesList);
		
		Set<DraftChange> DCs = new HashSet<>();
		for(DChangeA2E dcae : dcaes) {
			query = "FROM DraftChange where id = :id";
			Query<DraftChange> q2 = s.createQuery(query, DraftChange.class);
			q2.setParameter("id", dcae.getDraftChange().getId());
			DraftChange dc = q2.getSingleResult();
			DCs.add(dc);
		}
		return DCs;
	}
	
	public Set<PitchReview> getReceivedEditorPitchReview(Editor e){
		Session s = hu.getSession();
		String query = "FROM PReviewE2E where receiverEditor.id = :id";
		Query<PReviewE2E> q1 = s.createQuery(query, PReviewE2E.class);
		q1.setParameter("id", e.getId());
		List<PReviewE2E> preersList = q1.getResultList();
		Set<PReviewE2E> preers = new HashSet<>();
		preers.addAll(preersList);
		
		Set<PitchReview> PRs = new HashSet<>();
		for(PReviewE2E preer : preers) {
			PitchReview pr = getPitchReviewById(preer.getPitchReview().getId());
			PRs.add(pr);
		}
		
		return PRs;
	}
	public Set<PitchReview> getReceivedAuthorPitchReview(Editor e){
		Session s = hu.getSession();
		String query = "FROM PReviewA2E where editor.id = :id";
		Query<PReviewA2E> q1 = s.createQuery(query, PReviewA2E.class);
		q1.setParameter("id", e.getId());
		List<PReviewA2E> praesList = q1.getResultList();
		Set<PReviewA2E> praes = new HashSet<>();
		praes.addAll(praesList);
		
		Set<PitchReview> PRs = new HashSet<>();
		for(PReviewA2E prae : praes) {
			query = "FROM PitchReview where id = :id";
			Query<PitchReview> q2 = s.createQuery(query, PitchReview.class);
			q2.setParameter("id", prae.getPitchReview().getId());
			PitchReview pr = q2.getSingleResult();
			PRs.add(pr);
		}
		return PRs;
	}
	public Set<PitchReview> getSentEditorPitchReview(Editor e){
		Session s = hu.getSession();
		String query = "FROM PReviewE2E where senderEditor.id = :id";
		Query<PReviewE2E> q1 = s.createQuery(query, PReviewE2E.class);
		q1.setParameter("id", e.getId());
		List<PReviewE2E> preessList = q1.getResultList();
		Set<PReviewE2E> preess = new HashSet<>();
		preess.addAll(preessList);
		
		Set<PitchReview> PRs = new HashSet<>();
		for(PReviewE2E prees : preess) {
			PitchReview pr = getPitchReviewById(prees.getPitchReview().getId());
			PRs.add(pr);
		}
		
		return PRs;
	}
	public Set<PitchReview> getSentAuthorPitchReview(Editor e){
		Session s = hu.getSession();
		String query = "FROM PReviewE2A where editor.id = :id";
		Query<PReviewE2A> q1 = s.createQuery(query, PReviewE2A.class);
		q1.setParameter("id", e.getId());
		List<PReviewE2A> preasList = q1.getResultList();
		Set<PReviewE2A> preas = new HashSet<>();
		preas.addAll(preasList);
		
		Set<PitchReview> PRs = new HashSet<>();
		for(PReviewE2A prea : preas) {
			query = "FROM PitchReview where id = :id";
			Query<PitchReview> q2 = s.createQuery(query, PitchReview.class);
			q2.setParameter("id", prea.getPitchReview().getId());
			PitchReview pr = q2.getSingleResult();
			PRs.add(pr);
		}
		return PRs;
	}
	
	public Set<PitchReview> getReceivedPitchReview(Author a){
		Session s = hu.getSession();
		String query = "FROM PReviewE2A where author.id = :id";
		Query<PReviewE2A> q1 = s.createQuery(query, PReviewE2A.class);
		q1.setParameter("id", a.getId());
		List<PReviewE2A> preasList = q1.getResultList();
		Set<PReviewE2A> preas = new HashSet<>();
		preas.addAll(preasList);
		
		Set<PitchReview> PRs = new HashSet<>();
		for(PReviewE2A prea : preas) {
			query = "FROM PitchReview where id = :id";
			Query<PitchReview> q2 = s.createQuery(query, PitchReview.class);
			q2.setParameter("id", prea.getPitchReview().getId());
			PitchReview pr = q2.getSingleResult();
			PRs.add(pr);
		}
		return PRs;
	}
	public Set<PitchReview> getSentPitchReview(Author a){
		Session s = hu.getSession();
		String query = "FROM PReviewA2E where author.id = :id";
		Query<PReviewA2E> q1 = s.createQuery(query, PReviewA2E.class);
		q1.setParameter("id", a.getId());
		List<PReviewA2E> praesList = q1.getResultList();
		Set<PReviewA2E> praes = new HashSet<>();
		praes.addAll(praesList);
		
		Set<PitchReview> PRs = new HashSet<>();
		for(PReviewA2E prae : praes) {
			query = "FROM PitchReview where id = :id";
			Query<PitchReview> q2 = s.createQuery(query, PitchReview.class);
			q2.setParameter("id", prae.getPitchReview().getId());
			PitchReview pr = q2.getSingleResult();
			PRs.add(pr);
		}
		return PRs;
	}


}

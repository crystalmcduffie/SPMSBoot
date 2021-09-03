package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorDraft;
import com.SPMS.beans.Draft;
import com.SPMS.beans.DraftLog;
import com.SPMS.beans.DraftStage;
import com.SPMS.beans.Editor;
import com.SPMS.data.DraftDAO;
import com.SPMS.utils.HibernateUtil;


public class DraftHibernate implements DraftDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public Set<Draft> getByUsername(String username) {
		Session s = hu.getSession();
		String query = "FROM draft where author_id. = :author";
		Query<Draft> d = s.createQuery(query, Draft.class);
		d.setParameter("author_id", username);
		List<Draft> draftsList = d.getResultList();
		Set<Draft> drafts = new HashSet<>();
		drafts.addAll(draftsList);
		s.close();
		return drafts;
	}

	@Override
	public Draft getDraft(Integer id) {
		Session s = hu.getSession();
		Draft d = s.get(Draft.class, id);
		s.close();
		return d;
	}

	@Override
	public void submitDraft(Draft d) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(d);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToAuthorDraft(AuthorDraft ad) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(ad);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public AuthorDraft getAuthorDraft(Draft d) {
		Session s = hu.getSession();
		String query = "FROM AuthorDraft where draft.id = :id";
		Query<AuthorDraft> q = s.createQuery(query, AuthorDraft.class);
		q.setParameter("id", d.getId());
		AuthorDraft ad = q.getSingleResult();
		s.close();
		return ad;
	}

	@Override
	public void updateDraft(Draft d) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(d);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public void addToDraftLog(DraftLog dl) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(dl);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void createChangeRequest(Draft d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<DraftLog> getAllDraftApprovals(Draft d) {
		Session s = hu.getSession();
		String query = "FROM DraftLog where draft.id = :id";
		Query<DraftLog> q = s.createQuery(query, DraftLog.class);
		q.setParameter("id", d.getId());
		List<DraftLog> draftLogsList = q.getResultList();
		Set<DraftLog> draftLogs = new HashSet<>();
		draftLogs.addAll(draftLogsList);
		s.close();
		return draftLogs;
	}

	@Override
	public DraftStage getDraftStage(String name) {
		Session s = hu.getSession();
		String query = "FROM DraftStage where name = :name";
		Query<DraftStage> q = s.createQuery(query, DraftStage.class);
		q.setParameter("name", name);
		DraftStage ds = q.getSingleResult();
		s.close();
		return ds;
	}

	@Override
	public Set<Draft> getAll() {
		Session s = hu.getSession();
		String query = "FROM Draft";
		Query<Draft> q = s.createQuery(query, Draft.class);
		List<Draft> draftsList = q.getResultList();
		Set<Draft> drafts = new HashSet<>();
		drafts.addAll(draftsList);
		s.close();
		return drafts;
	}

	@Override
	public Set<Draft> getAllPendingDrafts() {
		Session s = hu.getSession();
		String query = "FROM Draft where stage.name = :name";
		Query<Draft> q = s.createQuery(query, Draft.class);
		q.setParameter("name", "pending");
		List<Draft> draftsList = q.getResultList();
		Set<Draft> drafts = new HashSet<>();
		drafts.addAll(draftsList);
		s.close();
		return drafts;
	}

	@Override
	public Set<DraftLog> getEditorDraftApprovals(Editor e) {
		Session s = hu.getSession();
		String query = "FROM DraftLog where editor.id = :id";
		Query<DraftLog> q = s.createQuery(query, DraftLog.class);
		q.setParameter("id", e.getId());
		List<DraftLog> draftlogsList = q.getResultList();
		Set<DraftLog> draftlogs = new HashSet<>();
		draftlogs.addAll(draftlogsList);
		s.close();
		return draftlogs;
	}
	
	
}

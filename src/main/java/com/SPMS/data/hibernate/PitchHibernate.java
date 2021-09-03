package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorPitch;
import com.SPMS.beans.Editor;
import com.SPMS.beans.EditorPitch;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchLog;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.PitchDAO;
import com.SPMS.utils.HibernateUtil;

import org.hibernate.Transaction;
public class PitchHibernate implements PitchDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	public Pitch getPitch(Integer id) {
		Session s = hu.getSession();
		Pitch p = s.get(Pitch.class, id);
		s.close();
		return p;
	}
	
	public Pitch createPitch(Pitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		return p;
	}
	
	public void updatePitch(Pitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(p);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public void addToPitchLog(PitchLog pl) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(pl);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToEditorPitch(EditorPitch ep) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(ep);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public void addToAuthorPitch(AuthorPitch ap) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(ap);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public PitchStage getPitchStage(String name) {
		Session s = hu.getSession();
		String query = "FROM PitchStage where name = :name";
		Query<PitchStage> q = s.createQuery(query, PitchStage.class);
		q.setParameter("name", name);
		PitchStage ps = q.getSingleResult();
		s.close();
		return ps;
		
	}

	@Override
	public EditorPitch getEditorPitch(Pitch p) {
		Session s = hu.getSession();
		String query = "FROM EditorPitch where pitch.id = :id";
		Query<EditorPitch> q = s.createQuery(query, EditorPitch.class);
		q.setParameter("id", p.getId());
		EditorPitch ep= q.getSingleResult();
		s.close();
		return ep;
	}

	@Override
	public void deleteEditorPitch(EditorPitch ep) {
		Session s = hu.getSession();
		Transaction tx=null;
		try {
			tx = s.beginTransaction();
			s.delete(ep);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		} finally {
			s.close();
		}

		
	}

	@Override
	public AuthorPitch getAuthorPitch(Pitch p) {
		Session s = hu.getSession();
		String query = "FROM AuthorPitch where pitch.id = :id";
		Query<AuthorPitch> q = s.createQuery(query, AuthorPitch.class);
		q.setParameter("id", p.getId());
		AuthorPitch ap = q.getSingleResult();
		s.close();
		return ap;
	}

	@Override
	public void updateEditorPitch(EditorPitch ep) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(ep);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
		
	}

	@Override
	public StoryType getStoryType(String name) {
		Session s = hu.getSession();
		String query = "FROM StoryType where storyType = :name";
		Query<StoryType> q = s.createQuery(query, StoryType.class);
		q.setParameter("name", name);
		StoryType st = q.getSingleResult();
		s.close();
		return st;
	}
}






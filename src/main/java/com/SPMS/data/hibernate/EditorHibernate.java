package com.SPMS.data.hibernate;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.SPMS.beans.Author;
import com.SPMS.beans.Editor;
import com.SPMS.data.EditorDAO;
import com.SPMS.utils.HibernateUtil;

public class EditorHibernate implements EditorDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	public Editor getByUsername(String username) {
		Session s = hu.getSession();
		String query = "FROM Editor where username = :username";
		Query<Editor> q = s.createQuery(query, Editor.class);
		q.setParameter("username", username);
		Editor e = q.getSingleResult();
		s.close();
		return e;
	}
	
	public void update(Editor ed) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(ed);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
	}
	
	public Set<Editor> getAll(){
		Session s = hu.getSession();
		String query = "From Editor";
		Query<Editor> q = s.createQuery(query, Editor.class);
		List<Editor> editorsList = q.getResultList();
		Set<Editor> editors = new HashSet<>();
		editors.addAll(editorsList);
		s.close();
		return editors;
	}
}

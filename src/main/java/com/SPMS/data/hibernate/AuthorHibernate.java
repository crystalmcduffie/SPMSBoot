package com.SPMS.data.hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.SPMS.beans.Author;
import com.SPMS.data.AuthorDAO;
import com.SPMS.exceptions.NonUniqueUsernameException;
import com.SPMS.utils.HibernateUtil;

public class AuthorHibernate implements AuthorDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	public  Author getByUsername(String username) {
		Session s = hu.getSession();
		String query = "FROM Author where username = :username";
		Query<Author> q = s.createQuery(query, Author.class);
		q.setParameter("username", username);
		Author a = q.getSingleResult();
		s.close();
		return a;
	}

	@Override
	public void update(Author a) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(a);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public Author create(Author a) throws NonUniqueUsernameException{
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(a);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
			return null;
		}finally {
			s.close();
		}
		return a;
	}

}

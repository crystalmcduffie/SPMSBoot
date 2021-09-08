package com.SPMS.data.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Message;
import com.SPMS.data.GenreCommitteeDAO;
import com.SPMS.utils.HibernateUtil;

@Repository
public class GenreCommitteeHibernate extends SimpleJpaRepository<GenreCommittee,Integer> implements GenreCommitteeDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	private EntityManager entityManager;
	
	public GenreCommitteeHibernate(EntityManager em) {
		super(GenreCommittee.class, em);
		this.entityManager = em;
	}

	@Override
	public List<GenreCommittee> findAllByEditorId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM GenreCommittee where editor_id = :id";
		Query<GenreCommittee> q = s.createQuery(query, GenreCommittee.class);
		q.setParameter("id", id);
		List<GenreCommittee> gcs = q.getResultList();
		s.close();
		return gcs;
	}

	@Override
	public List<GenreCommittee> findAllByGenreId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM GenreCommittee where genre_id = :id";
		Query<GenreCommittee> q = s.createQuery(query, GenreCommittee.class);
		q.setParameter("id", id);
		List<GenreCommittee> gcs = q.getResultList();
		s.close();
		return gcs;
	}

}

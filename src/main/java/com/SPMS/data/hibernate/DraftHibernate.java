package com.SPMS.data.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Pitch;
import com.SPMS.data.DraftDAO;
import com.SPMS.utils.HibernateUtil;

@Repository
public class DraftHibernate extends SimpleJpaRepository<Draft,Integer> implements DraftDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	private EntityManager entityManager;
	
	public DraftHibernate(EntityManager em) {
		super(Draft.class, em);
		this.entityManager = em;
	}
	
	/*public List<Draft> getAuthorDrafts(List<Pitch> pitches){
		Session s = hu.getSession();
		List<Draft> drafts = new ArrayList<>();
		for(Pitch p : pitches) {
			String query = "FROM Draft where pitch_id = :id";
			Query<Draft> q = s.createQuery(query, Draft.class);
			q.setParameter("id", p.getId());
			Draft draft = q.getSingleResult();
			drafts.add(draft);
		}
		s.close();
		return drafts;
	}*/
	

}

package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Pitch;
import com.SPMS.data.PitchDAO;
import com.SPMS.utils.HibernateUtil;

import org.hibernate.Transaction;

@Repository
public class PitchHibernate extends SimpleJpaRepository<Pitch, Integer> implements PitchDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
    private EntityManager entityManager;

    public PitchHibernate(EntityManager em) {
        super(Pitch.class, em);
        this.entityManager = em;
    }

	
	/*public List<Pitch> getAuthorPitches(Integer id){
		Session s = hu.getSession();
		String query = "FROM Pitch where author_id = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", id);
		List<Pitch> pitches = q.getResultList();
		s.close();
		return pitches;
	}*/
}






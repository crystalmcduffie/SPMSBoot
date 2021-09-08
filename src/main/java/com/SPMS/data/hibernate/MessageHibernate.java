package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Message;
import com.SPMS.data.MessageDAO;
import com.SPMS.utils.HibernateUtil;

@Repository
public class MessageHibernate extends SimpleJpaRepository<Message, Integer> implements MessageDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	private EntityManager entityManager;

    public MessageHibernate(EntityManager em) {
        super(Message.class, em);
        this.entityManager = em;
    }
	
	public List<Message> getSentMessages(Integer id){
		Session s = hu.getSession();
		String query = "FROM Message where sender_id = :id";
		Query<Message> q = s.createQuery(query, Message.class);
		q.setParameter("id", id);
		List<Message> sentMessages = q.getResultList();
		s.close();
		return sentMessages;
	}
	
	public List<Message> getReceivedMessages(Integer id){
		Session s = hu.getSession();
		String query = "FROM Message where receiver_id = :id";
		Query<Message> q = s.createQuery(query, Message.class);
		q.setParameter("id", id);
		List<Message> sentMessages = q.getResultList();
		s.close();
		return sentMessages;
	}
	
}

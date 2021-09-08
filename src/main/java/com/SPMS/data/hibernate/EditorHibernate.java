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
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Editor;
import com.SPMS.data.EditorDAO;
import com.SPMS.utils.HibernateUtil;

@Repository
public abstract class EditorHibernate implements EditorDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
}

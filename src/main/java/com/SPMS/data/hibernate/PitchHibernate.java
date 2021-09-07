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

import com.SPMS.data.PitchDAO;
import com.SPMS.utils.HibernateUtil;

import org.hibernate.Transaction;
public abstract class PitchHibernate implements PitchDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

}






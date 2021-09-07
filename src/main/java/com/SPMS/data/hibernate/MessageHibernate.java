package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.SPMS.data.MessageDAO;
import com.SPMS.utils.HibernateUtil;

public abstract class MessageHibernate implements MessageDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
}

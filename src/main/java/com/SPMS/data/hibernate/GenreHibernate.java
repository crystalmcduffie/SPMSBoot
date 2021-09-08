package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Genre;
import com.SPMS.data.GenreDAO;
import com.SPMS.utils.HibernateUtil;

@Repository
public abstract class GenreHibernate implements GenreDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

}

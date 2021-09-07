package com.SPMS.data.hibernate;

import com.SPMS.data.DraftDAO;
import com.SPMS.utils.HibernateUtil;

public abstract class DraftHibernate implements DraftDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

}

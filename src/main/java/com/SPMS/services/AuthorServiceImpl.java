package com.SPMS.services;

import com.SPMS.beans.Author;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.hibernate.AuthorHibernate;
import com.SPMS.exceptions.NonUniqueUsernameException;

public class AuthorServiceImpl implements AuthorService{
	private AuthorDAO authorDAO;
	
	public AuthorServiceImpl() {
		authorDAO = DAOFactory.getAuthorDAO();
	}
	
	public Author getByUsername(String username) {
		return authorDAO.getByUsername(username);
	}

	@Override
	public void update(Author a) {
		authorDAO.update(a);
		
	}

	@Override
	public Author create(Author a) throws NonUniqueUsernameException{
		a.setPoints(100);
		return authorDAO.create(a);
	}
}

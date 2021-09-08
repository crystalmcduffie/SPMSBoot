package com.SPMS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Author;
import com.SPMS.data.AuthorDAO;

@Service
public class AuthorServiceImpl implements AuthorService{
	AuthorDAO authorDAO;
	
	@Autowired
	public AuthorServiceImpl(AuthorDAO a) {
		this.authorDAO = a;
	}
	
	public Author getByUsername(String username) {
		return authorDAO.getByUsername(username);
	}

	@Override
	public void update(Author a) {
		authorDAO.save(a);
		
	}

	@Override
	public List<Author> getAll() {
		return authorDAO.findAll();
	}

}

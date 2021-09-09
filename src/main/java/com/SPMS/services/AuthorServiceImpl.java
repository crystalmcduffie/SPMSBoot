package com.SPMS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Author;
import com.SPMS.data.AuthorDAO;
import com.SPMS.exceptions.IncorrectTypeException;

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
	public List<Author> getAll() {
		return authorDAO.findAll();
	}

	@Override
	public Author create(Author a) throws IncorrectTypeException {
		if(a.getRole().getName().equals("author")==false) {
			IncorrectTypeException IncorrectTypeException = new IncorrectTypeException();
			throw IncorrectTypeException;
		}
		return authorDAO.save(a);
	}

	@Override
	public Author update(Author a) {
		return authorDAO.save(a);
	}

}

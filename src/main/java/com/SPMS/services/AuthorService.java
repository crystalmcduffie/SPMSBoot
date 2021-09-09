package com.SPMS.services;

import java.util.List;

import com.SPMS.beans.Author;
import com.SPMS.exceptions.IncorrectTypeException;
import com.SPMS.exceptions.NonUniqueUsernameException;

public interface AuthorService {
	public Author create(Author a) throws IncorrectTypeException;
	public Author getByUsername(String username);
	public Author update(Author a);
	public List<Author> getAll();
}

package com.SPMS.services;

import java.util.List;

import com.SPMS.beans.Author;
import com.SPMS.exceptions.NonUniqueUsernameException;

public interface AuthorService {
	public Author getByUsername(String username);
	public void update(Author a);
	public List<Author> getAll();
}

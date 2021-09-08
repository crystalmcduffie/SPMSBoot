package com.SPMS.services;

import java.util.List;
import java.util.function.BooleanSupplier;

import com.SPMS.beans.Person;
import com.SPMS.exceptions.NonUniqueUsernameException;

public interface PersonService {
	public Person getByUsername(String username);
	public void update(Person p);
	public List<Person> getAll();
	public boolean isAuthor(Person p);
	public Person createUser(Person p) throws NonUniqueUsernameException;
}

package com.SPMS.services;

import java.util.List;
import java.util.function.BooleanSupplier;

import com.SPMS.beans.Person;

public interface PersonService {
	public Person getByUsername(String username);
	public Person update(Person p);
	public List<Person> getAll();
	public boolean isAuthor(Person p);
	public Person createUser(Person p);
	public void deleteUser(Person p);
	public int calculatePoints(Person author);
}


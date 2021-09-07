package com.SPMS.services;

import java.util.List;

import com.SPMS.beans.Person;

public interface PersonService {
	public Person getByUsername(String username);
	public void update(Person p);
	public List<Person> getAll();
}

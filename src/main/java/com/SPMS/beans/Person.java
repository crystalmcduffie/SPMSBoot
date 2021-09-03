package com.SPMS.beans;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	@JoinTable(name="assignment",
			joinColumns=@JoinColumn(name="editor_id"),
			inverseJoinColumns=@JoinColumn(name="task_id"))
	private Set<Task> assignments;
	@JoinTable(name="pitch",
			joinColumns=@JoinColumn(name="author_id"))
	private Set<Pitch> pitches;
	private Set<Draft> drafts;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Task> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<Task> assignments) {
		this.assignments = assignments;
	}
	public Set<Pitch> getPitches() {
		return pitches;
	}
	public void setPitches(Set<Pitch> pitches) {
		this.pitches = pitches;
	}
	public Set<Draft> getDrafts() {
		return drafts;
	}
	public void setDrafts(Set<Draft> drafts) {
		this.drafts = drafts;
	}
	@Override
	public int hashCode() {
		return Objects.hash(drafts, id, password, pitches, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(drafts, other.drafts) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(pitches, other.pitches)
				&& Objects.equals(username, other.username);
	}
	
}

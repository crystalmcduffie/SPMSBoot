package com.SPMS.beans;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	/*@ManyToMany
	@JoinTable(name="assignment",
			joinColumns=@JoinColumn(name="editor_id"),
			inverseJoinColumns=@JoinColumn(name="task_id"))
	private Set<Task> assignments;
	@OneToMany
	@JoinTable(name="pitch",
			joinColumns=@JoinColumn(name="author_id"))
	private Set<Pitch> pitches;
	/*@OneToMany
	@JoinTable(name="drafts",
			joinColumns=@joinColumn(name=))
	private Set<Draft> drafts;*/
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
	/*public Set<Task> getAssignments() {
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
	/*public Set<Draft> getDrafts() {
		return drafts;
	}
	public void setDrafts(Set<Draft> drafts) {
		this.drafts = drafts;
	}*/
	@Override
	public int hashCode() {
		return Objects.hash(id, password, username);
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
		return Objects.equals(id, other.id)
				&& Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
}

package com.SPMS.beans;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Editor extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="assignment",
			joinColumns=@JoinColumn(name="editor_id"),
			inverseJoinColumns=@JoinColumn(name="task_id"))
	private Set<Task> assignments;
	@Override
	public int hashCode() {
		return Objects.hash(assignments, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editor other = (Editor) obj;
		return Objects.equals(assignments, other.assignments) && Objects.equals(id, other.id);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Task> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<Task> assignments) {
		this.assignments = assignments;
	}
}

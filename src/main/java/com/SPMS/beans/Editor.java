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
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="assignment",
			joinColumns=@JoinColumn(name="editor_id"))
	private Set<Assignment> assignments;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(assignments, id);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editor other = (Editor) obj;
		return Objects.equals(assignments, other.assignments) && Objects.equals(id, other.id);
	}
}

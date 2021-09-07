package com.SPMS.beans;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

public class Author extends Person{
	@OneToMany
	@JoinTable(name="pitch",
			joinColumns=@JoinColumn(name="author_id"))
	private Set<Pitch> pitches;

	public Set<Pitch> getPitches() {
		return pitches;
	}

	public void setPitches(Set<Pitch> pitches) {
		this.pitches = pitches;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(pitches);
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
		Author other = (Author) obj;
		return Objects.equals(pitches, other.pitches);
	}
}

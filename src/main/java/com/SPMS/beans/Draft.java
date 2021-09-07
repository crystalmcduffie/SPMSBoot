package com.SPMS.beans;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table (name = "draft")
//@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "task_id")
public class Draft extends Task implements Comparable<Draft>{

	private String draft;
	@ManyToOne
	@JoinColumn (name="stage_id")
	private Stage stage;

	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(draft, stage);
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
		Draft other = (Draft) obj;
		return Objects.equals(draft, other.draft) && Objects.equals(stage, other.stage);
	}
	@Override
	public int compareTo(Draft o) {
		return this.getId().compareTo(o.getId());
	}

}

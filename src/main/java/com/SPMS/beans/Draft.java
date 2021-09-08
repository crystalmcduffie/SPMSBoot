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
@PrimaryKeyJoinColumn(name = "task_id")
public class Draft extends Task implements Comparable<Draft>{

	private String draft;
	@ManyToOne
	@JoinColumn (name="stage_id")
	private Stage stage;
	@OneToOne
	@JoinColumn(name="pitch_id")
	private Pitch pitch;

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
	public Pitch getPitch() {
		return pitch;
	}
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(draft, pitch, stage);
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
		return Objects.equals(draft, other.draft) && Objects.equals(pitch, other.pitch)
				&& Objects.equals(stage, other.stage);
	}
	@Override
	public int compareTo(Draft o) {
		return this.getId().compareTo(o.getId());
	}

}

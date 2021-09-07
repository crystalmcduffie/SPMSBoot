package com.SPMS.beans;

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
	public Integer getId() {
		return task_id;
	}
	public void setId(Integer task_id) {
		this.task_id = task_id;
	}
	public StoryType getStoryType() {
		return storyType;
	}
	public void setStoryType(StoryType storyType) {
		this.storyType = storyType;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Draft [task_id=" + task_id + ", draft=" + draft + ", storyType=" + storyType + ", genre=" + genre + ", stage="
				+ stage + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((draft == null) ? 0 : draft.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((task_id == null) ? 0 : task_id.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((storyType == null) ? 0 : storyType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Draft other = (Draft) obj;
		if (draft == null) {
			if (other.draft != null)
				return false;
		} else if (!draft.equals(other.draft))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (task_id == null) {
			if (other.task_id != null)
				return false;
		} else if (!task_id.equals(other.task_id))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (storyType == null) {
			if (other.storyType != null)
				return false;
		} else if (!storyType.equals(other.storyType))
			return false;
		return true;
	}
	@Override
	public int compareTo(Draft o) {
		return this.getId().compareTo(o.getId());
	}

}

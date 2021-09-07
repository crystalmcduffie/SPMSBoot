package com.SPMS.beans;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "pitch")
@PrimaryKeyJoinColumn(name = "task_id")
public class Pitch extends Task implements Comparable<Pitch> {

	@Column (name ="author_info")
	private String authorInfo;
	@Column (name = "title")
	private String title;
	@Column (name = "tentative_completion_date")
	private java.sql.Date tentativeCompletionDate;
	@ManyToOne
	@JoinColumn (name = "story_type_id")
	private StoryType storyType;
	@ManyToOne
	@JoinColumn (name = "genre_id")
	private Genre genre;
	@Column (name = "tag_line")
	private String tagLine;
	private String description;
	private byte[] attachments;
	@Column (name = "on_hold")
	private boolean onHold;
	@Column (name = "timestamp")
	private java.sql.Date timestamp;
	@ManyToOne
	@JoinColumn (name="stage_id")
	private Stage stage;
	
	
	public String getAuthorInfo() {
		return authorInfo;
	}


	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public java.sql.Date getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}


	public void setTentativeCompletionDate(java.sql.Date tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
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


	public String getTagLine() {
		return tagLine;
	}


	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public byte[] getAttachments() {
		return attachments;
	}


	public void setAttachments(byte[] attachments) {
		this.attachments = attachments;
	}


	public boolean isOnHold() {
		return onHold;
	}


	public void setOnHold(boolean onHold) {
		this.onHold = onHold;
	}


	public java.sql.Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(java.sql.Date timestamp) {
		this.timestamp = timestamp;
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
		result = prime * result + Arrays.hashCode(attachments);
		result = prime * result + Objects.hash(authorInfo, description, genre, onHold, stage, storyType, tagLine,
				tentativeCompletionDate, timestamp, title);
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
		Pitch other = (Pitch) obj;
		return Arrays.equals(attachments, other.attachments) && Objects.equals(authorInfo, other.authorInfo)
				&& Objects.equals(description, other.description) && Objects.equals(genre, other.genre)
				&& onHold == other.onHold && Objects.equals(stage, other.stage)
				&& Objects.equals(storyType, other.storyType) && Objects.equals(tagLine, other.tagLine)
				&& Objects.equals(tentativeCompletionDate, other.tentativeCompletionDate)
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(title, other.title);
	}


	@Override
	public int compareTo(Pitch o) {
		return this.getId().compareTo(o.getId());
	}

}

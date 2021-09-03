package com.SPMS.beans;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Request {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@Column(name="task_id")
	private Task task;
	private boolean draft;
	private boolean complete;
	@Column (name ="author_info")
	private boolean authorInfo;
	@Column (name = "title")
	private boolean title;
	@Column (name = "tentative_completion_date")
	private boolean tentativeCompletionDate;
	@Column (name = "tag_line")
	private boolean tagLine;
	private boolean description;
	private boolean attachments;
	@Column (name = "on_hold")
	private boolean onHold;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public boolean isDraft() {
		return draft;
	}
	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public boolean isAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(boolean authorInfo) {
		this.authorInfo = authorInfo;
	}
	public boolean isTitle() {
		return title;
	}
	public void setTitle(boolean title) {
		this.title = title;
	}
	public boolean isTentativeCompletionDate() {
		return tentativeCompletionDate;
	}
	public void setTentativeCompletionDate(boolean tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
	}
	public boolean isTagLine() {
		return tagLine;
	}
	public void setTagLine(boolean tagLine) {
		this.tagLine = tagLine;
	}
	public boolean isDescription() {
		return description;
	}
	public void setDescription(boolean description) {
		this.description = description;
	}
	public boolean isAttachments() {
		return attachments;
	}
	public void setAttachments(boolean attachments) {
		this.attachments = attachments;
	}
	public boolean isOnHold() {
		return onHold;
	}
	public void setOnHold(boolean onHold) {
		this.onHold = onHold;
	}
	@Override
	public int hashCode() {
		return Objects.hash(attachments, authorInfo, complete, description, draft, id, onHold, tagLine, task,
				tentativeCompletionDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return attachments == other.attachments && authorInfo == other.authorInfo && complete == other.complete
				&& description == other.description && draft == other.draft && Objects.equals(id, other.id)
				&& onHold == other.onHold && tagLine == other.tagLine && Objects.equals(task, other.task)
				&& tentativeCompletionDate == other.tentativeCompletionDate && title == other.title;
	}
	
	
	
}

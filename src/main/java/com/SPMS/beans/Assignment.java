package com.SPMS.beans;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
public class Assignment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn (name = "task_id")
	private Task task;
	@Column(name = "timestamp")
	private java.sql.Date timestamp;
	@ManyToOne
	@JoinColumn (name = "editor_id")
	private Person editor;
	private boolean complete;
	@ManyToOne
	@JoinColumn (name = "editor_status_id")
	private EditorStatus editorStatus;
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
	public java.sql.Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.sql.Date timestamp) {
		this.timestamp = timestamp;
	}
	public Person getEditor() {
		return editor;
	}
	public void setEditor(Person editor) {
		this.editor = editor;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public EditorStatus getEditorStatus() {
		return editorStatus;
	}
	public void setEditorStatus(EditorStatus editorStatus) {
		this.editorStatus = editorStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(complete, editor, editorStatus, id, task, timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		return complete == other.complete && Objects.equals(editor, other.editor)
				&& Objects.equals(editorStatus, other.editorStatus) && Objects.equals(id, other.id)
				&& Objects.equals(task, other.task) && Objects.equals(timestamp, other.timestamp);
	}
	
}

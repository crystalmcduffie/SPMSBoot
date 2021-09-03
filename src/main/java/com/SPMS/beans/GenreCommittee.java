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
import javax.persistence.Table;

@Entity 
@Table (name = "genre_committee")
public class GenreCommittee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn (name = "genre_id")
	private Genre genre;
	@ManyToOne
	@JoinColumn (name = "editor_id")
	private Person editor;
	@ManyToOne
	@JoinColumn (name = "editor_status_id")
	private EditorStatus editorStatus;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Person getEditor() {
		return editor;
	}
	public void setEditor(Person editor) {
		this.editor = editor;
	}
	public EditorStatus getEditorStatus() {
		return editorStatus;
	}
	public void setEditorStatus(EditorStatus editorStatus) {
		this.editorStatus = editorStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(editor, editorStatus, genre, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenreCommittee other = (GenreCommittee) obj;
		return Objects.equals(editor, other.editor) && Objects.equals(editorStatus, other.editorStatus)
				&& Objects.equals(genre, other.genre) && Objects.equals(id, other.id);
	}
	
	
}

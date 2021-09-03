package com.SPMS.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Editor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	@Column (name="psswd")
	private String password;
	@OneToMany (fetch=FetchType.EAGER)
	@JoinTable(name="editor_pitch",
	joinColumns=@JoinColumn(name="editor_id"),
	inverseJoinColumns=@JoinColumn(name="pitch_id"))
	private Set<Pitch> pitches;
//	@OneToMany (fetch=FetchType.EAGER)
//	@JoinTable(name="dmessage_a2e",
//	joinColumns=@JoinColumn(name="receiver_id"),
//	inverseJoinColumns=@JoinColumn(name="message_id_a2e"))
//	private Set<DraftMessage> receivedDraftMessages;
//	@OneToMany (fetch=FetchType.EAGER)
//	@JoinTable(name="dmessage_e2a",
//	joinColumns=@JoinColumn(name="sender_id"),
//	inverseJoinColumns=@JoinColumn(name="message_id_e2a"))
//	private Set<DraftMessage> sentDraftMessages;
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinTable(name = "dchange_a2e",
//	joinColumns=@JoinColumn(name="receiver_id"),
//	inverseJoinColumns=@JoinColumn(name="draft_change_id_a2e"))
//	private Set<DraftChange> draftChangeResponses;
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinTable(name = "dchange_e2a",
//	joinColumns=@JoinColumn(name="sender_id"),
//	inverseJoinColumns=@JoinColumn(name="draft_change_id_e2a"))
//	private Set<DraftChange> draftChangeRequests;
//	@OneToMany (fetch=FetchType.EAGER)
//	@JoinTable(name="pmessage_a2e",
//	joinColumns=@JoinColumn(name="receiver_id"),
//	inverseJoinColumns=@JoinColumn(name="message_id"))
//	private Set<PitchMessage> receivedAuthorPitchMessages;
//	@OneToMany (fetch=FetchType.EAGER)
//	@JoinTable(name="pmessage_e2a",
//	joinColumns=@JoinColumn(name="sender_id"),
//	inverseJoinColumns=@JoinColumn(name="message_id_e2a"))
//	private Set<PitchMessage> sentAuthorPitchMessages;
//	@OneToMany (fetch=FetchType.EAGER)
//	@JoinTable(name="pmessage_e2e_received",
//	joinColumns=@JoinColumn(name="receiver_id"),
//	inverseJoinColumns=@JoinColumn(name="message_id_e2e_r"))
//	private Set<PitchMessage> receivedEditorPitchMessages;
//	@OneToMany (fetch=FetchType.EAGER)
//	@JoinTable(name="pmessage_e2e_sent",
//	joinColumns=@JoinColumn(name="sender_id"),
//	inverseJoinColumns=@JoinColumn(name="message_id_e2e_s"))
//	private Set<PitchMessage> sentEditorPitchMessages;
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinTable(name = "p_review_a2e",
//	joinColumns=@JoinColumn(name="receiver_id"),
//	inverseJoinColumns=@JoinColumn(name="pitch_review_id_a2e"))
//	private Set<PitchReview> receivedAuthorPitchReviews;
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinTable(name = "p_review_e2a",
//	joinColumns=@JoinColumn(name="sender_id"),
//	inverseJoinColumns=@JoinColumn(name="pitch_review_id_e2a"))
//	private Set<PitchReview> sentAuthorPitchReviews;
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinTable(name = "p_review_e2e_received",
//	joinColumns=@JoinColumn(name="receiver_id"),
//	inverseJoinColumns=@JoinColumn(name="pitch_review_id_e2e_r"))
//	private Set<PitchReview> receivedEditorPitchReviews;
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinTable(name = "p_review_e2e_sent",
//	joinColumns=@JoinColumn(name="sender_id"),
//	inverseJoinColumns=@JoinColumn(name="pitch_review_id_e2e_s"))
//	private Set<PitchReview> sentEditorPitchReviews;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Pitch> getPitches() {
		return pitches;
	}
	public void setPitches(Set<Pitch> pitches) {
		this.pitches = pitches;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pitches == null) ? 0 : pitches.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Editor other = (Editor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pitches == null) {
			if (other.pitches != null)
				return false;
		} else if (!pitches.equals(other.pitches))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Editor [id=" + id + ", username=" + username + ", password=" + password + ", pitches=" + pitches + "]";
	}

}

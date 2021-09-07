package com.SPMS.beans;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="sender_id")
	private Person sender;
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private Person receiver;
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	@Column(name="timestamp")
	private java.sql.Date timestamp;
	@OneToOne
	@JoinColumn(name="request_id")
	private Request request;
	private boolean read;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Person getSender() {
		return sender;
	}
	public void setSender(Person sender) {
		this.sender = sender;
	}
	public Person getReceiver() {
		return receiver;
	}
	public void setReceiver(Person receiver) {
		this.receiver = receiver;
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
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, read, receiver, request, sender, task, timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(id, other.id) && read == other.read && Objects.equals(receiver, other.receiver)
				&& Objects.equals(request, other.request) && Objects.equals(sender, other.sender)
				&& Objects.equals(task, other.task) && Objects.equals(timestamp, other.timestamp);
	}
	
	

}

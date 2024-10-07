package com.gmail.Gmail.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//mails table entity class
@Entity
@Table(name="mails")
public class Mail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)      //It will auto generate the primary key
	private int id;
	private String senderId;
	private List<String> receiversId;
	private String subject;
	private String message;
	private LocalDate date;          //It will generate the local date
	private LocalTime time;          //It will generate the local time
	private boolean isRead;
	private String type;
	private boolean isImportant;

	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public List<String> getReceiversId() {
		return receiversId;
	}
	public void setReceiversId(List<String> receiversId) {
		this.receiversId = receiversId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate() {
		this.date = LocalDate.now();
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime() {
		this.time = LocalTime.now();
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getisImportant() {
		return isImportant;
	}
	public void setImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//to string
	@Override
	public String toString() {
		return "Mail [id=" + id + ", senderId=" + senderId + ", receiversId=" + receiversId + ", subject=" + subject
				+ ", message=" + message + ", date=" + date + ", time=" + time + ", isRead=" + isRead + ", type=" + type
				+ ", isImportant=" + isImportant + ", user=" + user + "]";
	}
	
}
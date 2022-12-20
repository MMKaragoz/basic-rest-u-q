package com.project.ama.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "by_who_user_id", nullable = false)
	private User byWhoUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_whom_user_id", nullable = false)
	private User toWhomUser;
	
	@Column(columnDefinition = "text")
	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	public Question() {
		
	}

	public Question(User byWhoUser, User toWhomUser, String text, Date createDate) {
		this.byWhoUser = byWhoUser;
		this.toWhomUser = toWhomUser;
		this.text = text;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getbyWhoUser() {
		return byWhoUser;
	}

	public void setbyWhoUser(User byWhoUser) {
		this.byWhoUser = byWhoUser;
	}

	public User gettoWhomUser() {
		return toWhomUser;
	}

	public void settoWhomUser(User toWhomUser) {
		this.toWhomUser = toWhomUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(byWhoUser, createDate, id, text, toWhomUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(byWhoUser, other.byWhoUser) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(id, other.id) && Objects.equals(text, other.text)
				&& Objects.equals(toWhomUser, other.toWhomUser);
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", byWhoUser=" + byWhoUser + ", toWhomUser=" + toWhomUser + ", text=" + text
				+ ", createDate=" + createDate + "]";
	}
	
}

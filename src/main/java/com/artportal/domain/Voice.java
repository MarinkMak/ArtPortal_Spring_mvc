package com.artportal.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
@Entity
@NamedQueries({
	@NamedQuery(name="Voice.findAll", query="SELECT v FROM Voice AS v"),
	@NamedQuery(name="Voice.findByUser", query="SELECT v FROM Voice v WHERE v.user = :user"),
	@NamedQuery(name="Voice.findByWork", query="SELECT v FROM Voice v WHERE v.work = :work")
})
@Table(name="voices")
public class Voice {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer voiceValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date loadDate;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="work_id")
	private ArtWork work;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
//-------------------------------------
	public Voice() {
	}
	
	
public Voice(ArtWork work, User user) {
	this.voiceValue = 1;
	this.work = work;
	this.user = user;
}

//-------------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVoiceValue() {
		return voiceValue;
	}

	public void setVoiceValue(Integer voiceValue) {
		this.voiceValue = voiceValue;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public ArtWork getWork() {
		return work;
	}

	public void setWork(ArtWork work) {
		this.work = work;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((work == null) ? 0 : work.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Voice))
			return false;
		Voice other = (Voice) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (work == null) {
			if (other.work != null)
				return false;
		} else if (!work.equals(other.work))
			return false;
		return true;
	}



}

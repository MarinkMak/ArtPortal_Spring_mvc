package com.artportal.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
@Entity
@NamedQueries({
	@NamedQuery(name="ArtWork.findAll", query="SELECT w FROM ArtWork AS w"),
	@NamedQuery(name="ArtWork.findAllApprovedDescDate", 
		query="SELECT w FROM ArtWork AS w WHERE w.approved = true AND w.user.active = true ORDER BY w.loadDate DESC"),
	@NamedQuery(name="ArtWork.getCountAll", query="SELECT COUNT (w.id) FROM ArtWork AS w"),
	@NamedQuery(name="ArtWork.getCountOfApproved", query="SELECT COUNT (w.id) FROM ArtWork AS w WHERE w.approved = true AND w.user.active = true"),
	@NamedQuery(name="ArtWork.getCountOfCompWorks", query="SELECT COUNT (w.id) FROM ArtWork AS w WHERE w.approved = true AND w.user.active = true AND w.competition= :competition"),
	@NamedQuery(name="ArtWork.findByUser", query="SELECT w FROM ArtWork w WHERE w.user = :user ORDER BY w.loadDate DESC"),
	@NamedQuery(name="ArtWork.findByType", query="SELECT w FROM ArtWork w WHERE w.type = :type ORDER BY w.loadDate DESC"),
	@NamedQuery(name="ArtWork.findByName", query="SELECT w FROM ArtWork w WHERE w.name = :name"),
	@NamedQuery(name="ArtWork.findByCompetition", 
		query="SELECT w FROM ArtWork w WHERE w.approved = true AND w.competition = :competition ORDER BY w.loadDate DESC")
	
})
@Table(name="art_works")
public class ArtWork {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String name;
	private String path;
	private Boolean approved;
	
	@Transient
	private boolean viewComment;
	
	@Temporal(TemporalType.DATE)
	private Date loadDate;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="type_id")
	private ArtType type;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="competition_id")
	private Competition competition;
	
	@OneToMany(mappedBy="work",cascade={CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy="work",cascade={CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	private List<Voice> voices = new ArrayList<Voice>(); 
	
	
//-----------------------------------
	public ArtWork() {
		this.loadDate = new Date();//
	}
	
	public ArtWork(String name, ArtType type, String path, User user) {
		this.name = name;
		this.type = type;
		this.path = path;
		this.user = user;
	}

//-----------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArtType getType() {
		return type;
	}

	public void setType(ArtType type) {
		this.type = type;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Voice> getVoices() {
		return voices;
	}

	public void setVoices(List<Voice> voices) {
		this.voices = voices;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public boolean isViewComment() {
		return viewComment;
	}

	public void setViewComment(boolean viewComment) {
		this.viewComment = viewComment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((approved == null) ? 0 : approved.hashCode());
		result = prime * result
				+ ((loadDate == null) ? 0 : loadDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ArtWork))
			return false;
		ArtWork other = (ArtWork) obj;
		if (approved == null) {
			if (other.approved != null)
				return false;
		} else if (!approved.equals(other.approved))
			return false;
		if (loadDate == null) {
			if (other.loadDate != null)
				return false;
		} else if (!loadDate.equals(other.loadDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Art_work [id=" + id + ", name=" + name + ", path=" + path
				+ ", user=" + user + ", type=" + type + "]";
	}

	
	
	

	

	
}

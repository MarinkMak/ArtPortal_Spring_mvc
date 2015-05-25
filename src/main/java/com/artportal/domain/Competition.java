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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
@Entity
@NamedQueries({
	@NamedQuery(name="Competition.findByName", query="SELECT c FROM Competition c WHERE c.name = :name"),
	@NamedQuery(name="Competition.findAll", query="SELECT c FROM Competition AS c")
})
@Table(name="competitions")
public class Competition {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date loadDeadline;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="winner_id")
	private User winner;
	
//	@OneToMany(fetch=FetchType.EAGER,mappedBy="competition",cascade=CascadeType.ALL)
	@OneToMany(mappedBy="competition",cascade=CascadeType.ALL)
	private List<ArtWork> works = new ArrayList<ArtWork>();
	
//	@OneToMany(fetch=FetchType.EAGER,mappedBy="competition",cascade=CascadeType.ALL)
	@OneToMany(mappedBy="competition",cascade=CascadeType.ALL)
	private List<Present> presents = new ArrayList<Present>();
	
//--------------------------------------

	public Competition() {
	}

//--------------------------------------
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLoadDeadline() {
		return loadDeadline;
	}

	public void setLoadDeadline(Date loadDeadline) {
		this.loadDeadline = loadDeadline;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public List<ArtWork> getWorks() {
		return works;
	}

	public void setWorks(List<ArtWork> works) {
		this.works = works;
	}

	public List<Present> getPresents() {
		return presents;
	}

	public void setPresents(List<Present> presents) {
		this.presents = presents;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((loadDeadline == null) ? 0 : loadDeadline.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((presents == null) ? 0 : presents.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
		result = prime * result + ((works == null) ? 0 : works.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Competition))
			return false;
		Competition other = (Competition) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (loadDeadline == null) {
			if (other.loadDeadline != null)
				return false;
		} else if (!loadDeadline.equals(other.loadDeadline))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (presents == null) {
			if (other.presents != null)
				return false;
		} else if (!presents.equals(other.presents))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		if (works == null) {
			if (other.works != null)
				return false;
		} else if (!works.equals(other.works))
			return false;
		return true;
	}


	
	
}

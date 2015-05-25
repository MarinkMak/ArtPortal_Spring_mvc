package com.artportal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
@Entity
@NamedQueries({
	@NamedQuery(name="User.findByLogin", query="SELECT u FROM User u WHERE u.login = :login"),
	@NamedQuery(name="User.findUsersByLoginPart", query="SELECT u FROM User u WHERE u.login LIKE :loginPart"),
	@NamedQuery(name="User.getCountOfAllUsers", query="SELECT COUNT (u.id) FROM User u"),
	@NamedQuery(name="User.findAll", query="SELECT u FROM User AS u")
})
@Table(name="users")
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=20,message="Your name must be no more 20 characters long")
	//@Pattern(regexp="[а-яА-Яa-zA-Z- ёЁ]*",message="Just letters, please")
	@Pattern(regexp="[^0-9,./*+@#$%^&]*",message="Just letters, please")
	private String name;
	
	@Size(max=30,message="Your surname must be no more 30 characters long.")
	//@Pattern(regexp="[а-яА-Яa-zA-Z- ёЁ]*",message="Just letters, please")
	@Pattern(regexp="[^0-9,./*+@#$%^&]*",message="Just letters, please")
	private String surname;
	
	@Size(min=3, max=20,message="Login must be between 3 and 20 characters long")
	//@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$",message="Login must be alphanumeric with no spaces")
	private String login;
	
	@Column(name="psw")
	@Size(min=4,max=30,message="Password must be between 4 and 30 characters long")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Email(message="Invalid email address")
	@Size(min=1,message="Email can not be empty!")
	private String email;
	
	
	private String photoPath;
	private Boolean commentAble;
	private Boolean active;
	
	
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="role_id")
	private Role role;
	
//	@OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<ArtWork> works = new ArrayList<ArtWork>();
	
	@OneToMany(mappedBy="user")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy="user")
	private List<Voice> voices = new ArrayList<Voice>();
	
	@OneToMany(mappedBy="winner")
	private List<Competition> competitions = new ArrayList<Competition>();
//----------------------		
	public User() {
		this.photoPath = "noavatar.jpg";
	}

	public User(String login) {
		this.login = login;
	}

	public User(String login, String email, String password) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.commentAble = true;
		this.active = true;
		this.photoPath = "noavatar.jpg";
	}

//---------------------------	
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getCommentAble() {
		return commentAble;
	}

	public void setCommentAble(Boolean commentAble) {
		this.commentAble = commentAble;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	

	public List<ArtWork> getWorks() {
		return works;
	}

	public void setWorks(List<ArtWork> works) {
		this.works = works;
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

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	//---------------------------------------------------
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", psw=" + password
				+ ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result
				+ ((commentAble == null) ? 0 : commentAble.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((photoPath == null) ? 0 : photoPath.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (commentAble == null) {
			if (other.commentAble != null)
				return false;
		} else if (!commentAble.equals(other.commentAble))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photoPath == null) {
			if (other.photoPath != null)
				return false;
		} else if (!photoPath.equals(other.photoPath))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	


	
	
	
	
}

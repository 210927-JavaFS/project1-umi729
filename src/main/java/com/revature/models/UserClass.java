package com.revature.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class UserClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String username;

	private String password;

	private String fname;

	private String lname;

	private String email;
	@Enumerated(EnumType.STRING)
	private UserRoles urole;

	//@JoinColumn(name = "rid")
	@OneToMany(mappedBy = "rid", fetch = FetchType.EAGER)
	private List<Reimbursment> Reimbursment;

	public UserClass(int userId, String username, String password, String fname, String lname, String email,
			UserRoles urole, List<com.revature.models.Reimbursment> reimbursment) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.urole = urole;
		Reimbursment = reimbursment;
	}

	public UserClass(String username, String password, String fname, String lname, String email, UserRoles urole,
			List<com.revature.models.Reimbursment> reimbursment) {
		super();
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.urole = urole;
		Reimbursment = reimbursment;
	}

	public UserClass() {
		super();
	}

	@Override
	public String toString() {
		return "UserClass [userId=" + userId + ", username=" + username + ", password=" + password + ", fname=" + fname
				+ ", lname=" + lname + ", email=" + email + ", urole=" + urole + ", Reimbursment=" + Reimbursment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Reimbursment == null) ? 0 : Reimbursment.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((urole == null) ? 0 : urole.hashCode());
		result = prime * result + userId;
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
		UserClass other = (UserClass) obj;
		if (Reimbursment == null) {
			if (other.Reimbursment != null)
				return false;
		} else if (!Reimbursment.equals(other.Reimbursment))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (urole != other.urole)
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	

}

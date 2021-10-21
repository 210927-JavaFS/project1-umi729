package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	 @Column(nullable = false, unique = true)
	private String role;
	
	@OneToMany(mappedBy = "rid", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	private List<Reimbursment> rec;

	public UserRoles(int roleId, String role, List<Reimbursment> rec) {
		super();
		this.roleId = roleId;
		this.role = role;
		this.rec = rec;
	}

	public UserRoles(String role, List<Reimbursment> rec) {
		super();
		this.role = role;
		this.rec = rec;
	}

	public UserRoles() {
		super();
	}

	@Override
	public String toString() {
		return "UserRoles [roleId=" + roleId + ", role=" + role + ", rec=" + rec + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rec == null) ? 0 : rec.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleId;
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
		UserRoles other = (UserRoles) obj;
		if (rec == null) {
			if (other.rec != null)
				return false;
		} else if (!rec.equals(other.rec))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}

	
}

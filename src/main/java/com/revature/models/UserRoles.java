package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	 @Column(nullable = false, unique = true)
	private String role;
	
	 @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	 @JsonIgnore
	private List<UserClass> uc;
	 
	

	public UserRoles(int roleId, String role, List<Reimbursment> rec) {
		super();
		this.roleId = roleId;
		this.role = role;
		
	}

	public UserRoles(String role, List<Reimbursment> rec) {
		super();
		this.role = role;
		
	}

	public UserRoles(String role) {
		super();
		this.role = role;
	}

	public UserRoles() {
		super();
	}
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserClass> getUc() {
		return uc;
	}

	public void setUc(List<UserClass> uc) {
		this.uc = uc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleId;
		result = prime * result + ((uc == null) ? 0 : uc.hashCode());
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		if (uc == null) {
			if (other.uc != null)
				return false;
		} else if (!uc.equals(other.uc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRoles [roleId=" + roleId + ", role=" + role + ", uc=" + uc + "]";
	}

	

	
}

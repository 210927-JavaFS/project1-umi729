package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(unique = true)
	private String username;

	private String password;

	private String fname;

	private String lname;
	
	@Column(unique = true)
	private String email;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="roleId")
	private UserRoles role;

	
	@OneToMany(mappedBy = "rid", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Reimbursment> Reimbursment;

	

}

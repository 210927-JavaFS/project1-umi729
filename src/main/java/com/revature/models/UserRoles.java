package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
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
	
	private String role;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UserClass usr;
	
	@OneToMany(mappedBy = "rid", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	private List<Reimbursment> Reimbursment;


}

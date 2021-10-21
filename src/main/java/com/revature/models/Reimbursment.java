package com.revature.models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table
public class Reimbursment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rid;
	
	private double amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolved;
	
	private String description;
	
	private int recipt;
	@Enumerated(EnumType.STRING)
	
	private R_Type rtype;
	
	@Enumerated(EnumType.STRING)
	private R_Status rstatus;
	
	@OneToMany ( fetch=FetchType.EAGER)
	@JoinColumn(name="roleId")
	@ManyToOne
	 private UserRoles role;
	
	@OneToMany ( fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	@ManyToOne
	 private UserClass usr;


	
	
}

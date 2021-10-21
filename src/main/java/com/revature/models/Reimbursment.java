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
	
	private double ammount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolved;
	
	private String Description;
	
	private int recipt;
	@Enumerated(EnumType.STRING)
	
	private R_Type rtype;
	
	@Enumerated(EnumType.STRING)
	private R_Status rstatus;
	
	@OneToMany ( fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	@ManyToOne
	 private UserClass usr;

	public Reimbursment(int rid, double ammount, Date submitted, Date resolved, String description, int recipt,
			R_Type rtype, R_Status rstatus, UserClass usr) {
		super();
		this.rid = rid;
		this.ammount = ammount;
		this.submitted = submitted;
		this.resolved = resolved;
		Description = description;
		this.recipt = recipt;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.usr = usr;
	}

	public Reimbursment(double ammount, Date submitted, Date resolved, String description, int recipt, R_Type rtype,
			R_Status rstatus, UserClass usr) {
		super();
		this.ammount = ammount;
		this.submitted = submitted;
		this.resolved = resolved;
		Description = description;
		this.recipt = recipt;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.usr = usr;
	}

	public Reimbursment() {
		super();
	}

	@Override
	public String toString() {
		return "Reimbursment [rid=" + rid + ", ammount=" + ammount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", Description=" + Description + ", recipt=" + recipt + ", rtype=" + rtype + ", rstatus="
				+ rstatus + ", usr=" + usr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + recipt;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + rid;
		result = prime * result + ((rstatus == null) ? 0 : rstatus.hashCode());
		result = prime * result + ((rtype == null) ? 0 : rtype.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((usr == null) ? 0 : usr.hashCode());
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
		Reimbursment other = (Reimbursment) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (recipt != other.recipt)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (rid != other.rid)
			return false;
		if (rstatus != other.rstatus)
			return false;
		if (rtype != other.rtype)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (usr == null) {
			if (other.usr != null)
				return false;
		} else if (!usr.equals(other.usr))
			return false;
		return true;
	}

	
	
}

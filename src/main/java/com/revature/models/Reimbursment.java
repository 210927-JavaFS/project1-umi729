package com.revature.models;


import java.util.Date;

import javax.persistence.Column;
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
	 @Column(nullable = false)
	private double amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date submitted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolved;
	
	private String description;
	
	private String copyRec;
	
	private int recipt;
	@Enumerated(EnumType.STRING)
	
	private R_Type rtype;
	
	@Enumerated(EnumType.STRING)
	private R_Status rstatus;
	
	
	@JoinColumn(name="roleId")
	@ManyToOne( fetch=FetchType.EAGER)
	 private UserRoles role;
	
	
	@JoinColumn(name="userId")
	@ManyToOne( fetch=FetchType.EAGER)
	 private UserClass usr;

	public Reimbursment(int rid, double amount, Date submitted, Date resolved, String description, String copyRec,
			int recipt, R_Type rtype, R_Status rstatus, UserRoles role, UserClass usr) {
		super();
		this.rid = rid;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.copyRec = copyRec;
		this.recipt = recipt;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.role = role;
		this.usr = usr;
	}

	public Reimbursment(double amount, Date submitted, Date resolved, String description, String copyRec, int recipt,
			R_Type rtype, R_Status rstatus, UserRoles role, UserClass usr) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.copyRec = copyRec;
		this.recipt = recipt;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.role = role;
		this.usr = usr;
	}

	public Reimbursment() {
		super();
	}

	@Override
	public String toString() {
		return "Reimbursment [rid=" + rid + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", copyRec=" + copyRec + ", recipt=" + recipt + ", rtype=" + rtype
				+ ", rstatus=" + rstatus + ", role=" + role + ", usr=" + usr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((copyRec == null) ? 0 : copyRec.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + recipt;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + rid;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (copyRec == null) {
			if (other.copyRec != null)
				return false;
		} else if (!copyRec.equals(other.copyRec))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCopyRec() {
		return copyRec;
	}

	public void setCopyRec(String copyRec) {
		this.copyRec = copyRec;
	}

	public int getRecipt() {
		return recipt;
	}

	public void setRecipt(int recipt) {
		this.recipt = recipt;
	}

	public R_Type getRtype() {
		return rtype;
	}

	public void setRtype(R_Type rtype) {
		this.rtype = rtype;
	}

	public R_Status getRstatus() {
		return rstatus;
	}

	public void setRstatus(R_Status rstatus) {
		this.rstatus = rstatus;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	public UserClass getUsr() {
		return usr;
	}

	public void setUsr(UserClass usr) {
		this.usr = usr;
	}

	
	
}

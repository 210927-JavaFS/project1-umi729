package com.revature.models;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Date dateOfSubmit;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfResolve;
	
	private String description;
	
	private String copyOfReceipt;
	
	private int reciptNo;
	
	private String rtype;
	
	private String rstatus;
	
	
	@JoinColumn(name="roleId")
	@ManyToOne( fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	 private UserRoles role;
	
	
	@JoinColumn(name="userId")
	@ManyToOne( fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	 private UserClass usr;


	public Reimbursment(int rid, double amount, Date dateOfSubmit, Date dateOfResolve, String description,
			String copyOfReceipt, int reciptNo, String rtype, String rstatus, UserRoles role, UserClass usr) {
		super();
		this.rid = rid;
		this.amount = amount;
		this.dateOfSubmit = dateOfSubmit;
		this.dateOfResolve = dateOfResolve;
		this.description = description;
		this.copyOfReceipt = copyOfReceipt;
		this.reciptNo = reciptNo;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.role = role;
		this.usr = usr;
	}


	public Reimbursment(double amount, Date dateOfSubmit, Date dateOfResolve, String description, String copyOfReceipt,
			int reciptNo, String rtype, String rstatus, UserRoles role, UserClass usr) {
		super();
		this.amount = amount;
		this.dateOfSubmit = dateOfSubmit;
		this.dateOfResolve = dateOfResolve;
		this.description = description;
		this.copyOfReceipt = copyOfReceipt;
		this.reciptNo = reciptNo;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.role = role;
		this.usr = usr;
	}


	public Reimbursment(double amount, String description, String copyOfReceipt, int reciptNo, String rtype,
			String rstatus) {
		super();
		this.amount = amount;
		this.description = description;
		this.copyOfReceipt = copyOfReceipt;
		this.reciptNo = reciptNo;
		this.rtype = rtype;
		this.rstatus = rstatus;
	}


	public Reimbursment() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((copyOfReceipt == null) ? 0 : copyOfReceipt.hashCode());
		result = prime * result + ((dateOfResolve == null) ? 0 : dateOfResolve.hashCode());
		result = prime * result + ((dateOfSubmit == null) ? 0 : dateOfSubmit.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + reciptNo;
		result = prime * result + rid;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((rstatus == null) ? 0 : rstatus.hashCode());
		result = prime * result + ((rtype == null) ? 0 : rtype.hashCode());
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
		if (copyOfReceipt == null) {
			if (other.copyOfReceipt != null)
				return false;
		} else if (!copyOfReceipt.equals(other.copyOfReceipt))
			return false;
		if (dateOfResolve == null) {
			if (other.dateOfResolve != null)
				return false;
		} else if (!dateOfResolve.equals(other.dateOfResolve))
			return false;
		if (dateOfSubmit == null) {
			if (other.dateOfSubmit != null)
				return false;
		} else if (!dateOfSubmit.equals(other.dateOfSubmit))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reciptNo != other.reciptNo)
			return false;
		if (rid != other.rid)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (rstatus == null) {
			if (other.rstatus != null)
				return false;
		} else if (!rstatus.equals(other.rstatus))
			return false;
		if (rtype == null) {
			if (other.rtype != null)
				return false;
		} else if (!rtype.equals(other.rtype))
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


	public Date getDateOfSubmit() {
		return dateOfSubmit;
	}


	public void setDateOfSubmit(Date dateOfSubmit) {
		this.dateOfSubmit = dateOfSubmit;
	}


	public Date getDateOfResolve() {
		return dateOfResolve;
	}


	public void setDateOfResolve(Date dateOfResolve) {
		this.dateOfResolve = dateOfResolve;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCopyOfReceipt() {
		return copyOfReceipt;
	}


	public void setCopyOfReceipt(String copyOfReceipt) {
		this.copyOfReceipt = copyOfReceipt;
	}


	public int getReciptNo() {
		return reciptNo;
	}


	public void setReciptNo(int reciptNo) {
		this.reciptNo = reciptNo;
	}


	public String getRtype() {
		return rtype;
	}


	public void setRtype(String rtype) {
		this.rtype = rtype;
	}


	public String getRstatus() {
		return rstatus;
	}


	public void setRstatus(String rstatus) {
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


	@Override
	public String toString() {
		return "Reimbursment [rid=" + rid + ", amount=" + amount + ", dateOfSubmit=" + dateOfSubmit + ", dateOfResolve="
				+ dateOfResolve + ", description=" + description + ", copyOfReceipt=" + copyOfReceipt + ", reciptNo="
				+ reciptNo + ", rtype=" + rtype + ", rstatus=" + rstatus + ", role=" + role + ", usr=" + usr + "]";
	}


	
}

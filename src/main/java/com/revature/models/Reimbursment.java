package com.revature.models;


import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
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
	
	@Lob
	private byte[] fileimage;
	
	private int reciptNo;
	
	private String rtype;
	@OrderBy("rid ASC, rstatus DESC")
	private String rstatus;
	
	 private int approverId;
	
	@JoinColumn(name="userId")
	@ManyToOne( fetch=FetchType.EAGER)
	 private UserClass usr;

	public Reimbursment(int rid, double amount, Date dateOfSubmit, Date dateOfResolve, String description,
			byte[] fileimage, int reciptNo, String rtype, String rstatus, int approverId, UserClass usr) {
		super();
		this.rid = rid;
		this.amount = amount;
		this.dateOfSubmit = dateOfSubmit;
		this.dateOfResolve = dateOfResolve;
		this.description = description;
		this.fileimage = fileimage;
		this.reciptNo = reciptNo;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.approverId = approverId;
		this.usr = usr;
	}

	public Reimbursment(double amount, Date dateOfSubmit, Date dateOfResolve, String description, byte[] fileimage,
			int reciptNo, String rtype, String rstatus, int approverId, UserClass usr) {
		super();
		this.amount = amount;
		this.dateOfSubmit = dateOfSubmit;
		this.dateOfResolve = dateOfResolve;
		this.description = description;
		this.fileimage = fileimage;
		this.reciptNo = reciptNo;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.approverId = approverId;
		this.usr = usr;
	}

	public Reimbursment(double amount, String description, byte[] fileimage, int reciptNo, String rtype, String rstatus,
			UserClass usr) {
		super();
		this.amount = amount;
		this.description = description;
		this.fileimage = fileimage;
		this.reciptNo = reciptNo;
		this.rtype = rtype;
		this.rstatus = rstatus;
		this.usr = usr;
	}

	public Reimbursment(double amount, String description, int reciptNo) {
		super();
		this.amount = amount;
		this.description = description;
		this.reciptNo = reciptNo;
	}

	public Reimbursment() {
		super();
	}

	@Override
	public String toString() {
		return "Reimbursment [rid=" + rid + ", amount=" + amount + ", dateOfSubmit=" + dateOfSubmit + ", dateOfResolve="
				+ dateOfResolve + ", description=" + description + ", fileimage=" + Arrays.toString(fileimage)
				+ ", reciptNo=" + reciptNo + ", rtype=" + rtype + ", rstatus=" + rstatus + ", approverId=" + approverId
				+ ", usr=" + usr + "]";
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

	public byte[] getFileimage() {
		return fileimage;
	}

	public void setFileimage(byte[] fileimage) {
		this.fileimage = fileimage;
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

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public UserClass getUsr() {
		return usr;
	}

	public void setUsr(UserClass usr) {
		this.usr = usr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + approverId;
		result = prime * result + ((dateOfResolve == null) ? 0 : dateOfResolve.hashCode());
		result = prime * result + ((dateOfSubmit == null) ? 0 : dateOfSubmit.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(fileimage);
		result = prime * result + reciptNo;
		result = prime * result + rid;
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
		if (approverId != other.approverId)
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
		if (!Arrays.equals(fileimage, other.fileimage))
			return false;
		if (reciptNo != other.reciptNo)
			return false;
		if (rid != other.rid)
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


	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moses Nwaeze
 */
@Entity
@Table(name = "activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByActivityID", query = "SELECT a FROM Activity a WHERE a.activityID = :activityID"),
    @NamedQuery(name = "Activity.findBySalaryPaid", query = "SELECT a FROM Activity a WHERE a.salaryPaid = :salaryPaid"),
    @NamedQuery(name = "Activity.findByDatePaid", query = "SELECT a FROM Activity a WHERE a.datePaid = :datePaid"),
    @NamedQuery(name = "Activity.findByDob", query = "SELECT a FROM Activity a WHERE a.dob = :dob"),
    @NamedQuery(name = "Activity.findByDatePromoted", query = "SELECT a FROM Activity a WHERE a.datePromoted = :datePromoted")})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ActivityID")
    private Integer activityID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SalaryPaid")
    private BigDecimal salaryPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatePaid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatePromoted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePromoted;

    public Activity() {
    }

    public Activity(Integer activityID) {
        this.activityID = activityID;
    }

    public Activity(Integer activityID, BigDecimal salaryPaid, Date datePaid, Date dob, Date datePromoted) {
        this.activityID = activityID;
        this.salaryPaid = salaryPaid;
        this.datePaid = datePaid;
        this.dob = dob;
        this.datePromoted = datePromoted;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public BigDecimal getSalaryPaid() {
        return salaryPaid;
    }

    public void setSalaryPaid(BigDecimal salaryPaid) {
        this.salaryPaid = salaryPaid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDatePromoted() {
        return datePromoted;
    }

    public void setDatePromoted(Date datePromoted) {
        this.datePromoted = datePromoted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityID != null ? activityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.activityID == null && other.activityID != null) || (this.activityID != null && !this.activityID.equals(other.activityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Activity[ activityID=" + activityID + " ]";
    }
    
}

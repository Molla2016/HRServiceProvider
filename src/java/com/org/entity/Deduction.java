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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moses Nwaeze
 */
@Entity
@Table(name = "deduction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deduction.findAll", query = "SELECT d FROM Deduction d"),
    @NamedQuery(name = "Deduction.findByDeductionID", query = "SELECT d FROM Deduction d WHERE d.deductionID = :deductionID"),
    @NamedQuery(name = "Deduction.findByEmployeeName", query = "SELECT d FROM Deduction d WHERE d.employeeName = :employeeName"),
    @NamedQuery(name = "Deduction.findByAmountDeducted", query = "SELECT d FROM Deduction d WHERE d.amountDeducted = :amountDeducted"),
    @NamedQuery(name = "Deduction.findByOffenses", query = "SELECT d FROM Deduction d WHERE d.offenses = :offenses"),
    @NamedQuery(name = "Deduction.findByDateOffenseCommitted", query = "SELECT d FROM Deduction d WHERE d.dateOffenseCommitted = :dateOffenseCommitted")})
public class Deduction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DeductionID")
    private Integer deductionID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EmployeeName")
    private String employeeName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AmountDeducted")
    private BigDecimal amountDeducted;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Offenses")
    private String offenses;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateOffenseCommitted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOffenseCommitted;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne(optional = false)
    private Employee employeeID;

    public Deduction() {
    }

    public Deduction(Integer deductionID) {
        this.deductionID = deductionID;
    }

    public Deduction(Integer deductionID, String employeeName, BigDecimal amountDeducted, String offenses, Date dateOffenseCommitted) {
        this.deductionID = deductionID;
        this.employeeName = employeeName;
        this.amountDeducted = amountDeducted;
        this.offenses = offenses;
        this.dateOffenseCommitted = dateOffenseCommitted;
    }

    public Integer getDeductionID() {
        return deductionID;
    }

    public void setDeductionID(Integer deductionID) {
        this.deductionID = deductionID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getAmountDeducted() {
        return amountDeducted;
    }

    public void setAmountDeducted(BigDecimal amountDeducted) {
        this.amountDeducted = amountDeducted;
    }

    public String getOffenses() {
        return offenses;
    }

    public void setOffenses(String offenses) {
        this.offenses = offenses;
    }

    public Date getDateOffenseCommitted() {
        return dateOffenseCommitted;
    }

    public void setDateOffenseCommitted(Date dateOffenseCommitted) {
        this.dateOffenseCommitted = dateOffenseCommitted;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deductionID != null ? deductionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deduction)) {
            return false;
        }
        Deduction other = (Deduction) object;
        if ((this.deductionID == null && other.deductionID != null) || (this.deductionID != null && !this.deductionID.equals(other.deductionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Deduction[ deductionID=" + deductionID + " ]";
    }
    
}

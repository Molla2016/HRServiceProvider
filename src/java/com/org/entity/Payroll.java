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
import javax.persistence.OneToOne;
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
@Table(name = "payroll")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payroll.findAll", query = "SELECT p FROM Payroll p"),
    @NamedQuery(name = "Payroll.findByPayrollID", query = "SELECT p FROM Payroll p WHERE p.payrollID = :payrollID"),
    @NamedQuery(name = "Payroll.findByEmployeeName", query = "SELECT p FROM Payroll p WHERE p.employeeName = :employeeName"),
    @NamedQuery(name = "Payroll.findByPayStatus", query = "SELECT p FROM Payroll p WHERE p.payStatus = :payStatus"),
    @NamedQuery(name = "Payroll.findByDatePaid", query = "SELECT p FROM Payroll p WHERE p.datePaid = :datePaid"),
    @NamedQuery(name = "Payroll.findByAmountDeducted", query = "SELECT p FROM Payroll p WHERE p.amountDeducted = :amountDeducted"),
    @NamedQuery(name = "Payroll.findByAmountPaid", query = "SELECT p FROM Payroll p WHERE p.amountPaid = :amountPaid"),
    @NamedQuery(name = "Payroll.findByNetAmount", query = "SELECT p FROM Payroll p WHERE p.netAmount = :netAmount")})
public class Payroll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PayrollID")
    private Integer payrollID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EmployeeName")
    private String employeeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PayStatus")
    private String payStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatePaid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AmountDeducted")
    private BigDecimal amountDeducted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AmountPaid")
    private BigDecimal amountPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NetAmount")
    private BigDecimal netAmount;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @OneToOne(optional = false)
    private Employee employeeID;
    @JoinColumn(name = "gradelevel", referencedColumnName = "LevelID")
    @ManyToOne
    private LevelPosition gradelevel;

    public Payroll() {
    }

    public Payroll(Integer payrollID) {
        this.payrollID = payrollID;
    }

    public Payroll(Integer payrollID, String employeeName, String payStatus, Date datePaid, BigDecimal amountDeducted, BigDecimal amountPaid, BigDecimal netAmount) {
        this.payrollID = payrollID;
        this.employeeName = employeeName;
        this.payStatus = payStatus;
        this.datePaid = datePaid;
        this.amountDeducted = amountDeducted;
        this.amountPaid = amountPaid;
        this.netAmount = netAmount;
    }

    public Integer getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(Integer payrollID) {
        this.payrollID = payrollID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public BigDecimal getAmountDeducted() {
        return amountDeducted;
    }

    public void setAmountDeducted(BigDecimal amountDeducted) {
        this.amountDeducted = amountDeducted;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public LevelPosition getGradelevel() {
        return gradelevel;
    }

    public void setGradelevel(LevelPosition gradelevel) {
        this.gradelevel = gradelevel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payrollID != null ? payrollID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payroll)) {
            return false;
        }
        Payroll other = (Payroll) object;
        if ((this.payrollID == null && other.payrollID != null) || (this.payrollID != null && !this.payrollID.equals(other.payrollID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Payroll[ payrollID=" + payrollID + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Moses Nwaeze
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmployeeID", query = "SELECT e FROM Employee e WHERE e.employeeID = :employeeID"),
    @NamedQuery(name = "Employee.findByEmployeeFullName", query = "SELECT e FROM Employee e WHERE e.employeeFullName = :employeeFullName"),
    @NamedQuery(name = "Employee.findByEmployeeDOB", query = "SELECT e FROM Employee e WHERE e.employeeDOB = :employeeDOB"),
    @NamedQuery(name = "Employee.findByEmployeeAge", query = "SELECT e FROM Employee e WHERE e.employeeAge = :employeeAge"),
    @NamedQuery(name = "Employee.findByEmployeeGender", query = "SELECT e FROM Employee e WHERE e.employeeGender = :employeeGender")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EmployeeID")
    private String employeeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "EmployeeFullName")
    private String employeeFullName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EmployeeDOB")
    @Temporal(TemporalType.DATE)
    private Date employeeDOB;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EmployeeAge")
    private int employeeAge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "EmployeeGender")
    private String employeeGender;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "EmployeeAddress")
    private String employeeAddress;
    @JoinColumn(name = "EmployeeDepartment", referencedColumnName = "DepartmentID")
    @ManyToOne(optional = false)
    private Department employeeDepartment;
    @JoinColumn(name = "EmployeeLevel", referencedColumnName = "LevelID")
    @ManyToOne(optional = false)
    private LevelPosition employeeLevel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Hcudepartment> hcudepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Hrdepartment> hrdepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Ictdepartment> ictdepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeID")
    private Collection<Deduction> deductionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID")
    private Collection<Facdepartment> facdepartmentCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeID")
    private Registration registration;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeID")
    private Payroll payroll;

    public Employee() {
    }

    public Employee(String employeeID) {
        this.employeeID = employeeID;
    }

    public Employee(String employeeID, String employeeFullName, Date employeeDOB, int employeeAge, String employeeGender, String employeeAddress) {
        this.employeeID = employeeID;
        this.employeeFullName = employeeFullName;
        this.employeeDOB = employeeDOB;
        this.employeeAge = employeeAge;
        this.employeeGender = employeeGender;
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public Date getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(Date employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Department getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(Department employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public LevelPosition getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(LevelPosition employeeLevel) {
        this.employeeLevel = employeeLevel;
    }

    @XmlTransient
    public Collection<Hcudepartment> getHcudepartmentCollection() {
        return hcudepartmentCollection;
    }

    public void setHcudepartmentCollection(Collection<Hcudepartment> hcudepartmentCollection) {
        this.hcudepartmentCollection = hcudepartmentCollection;
    }

    @XmlTransient
    public Collection<Hrdepartment> getHrdepartmentCollection() {
        return hrdepartmentCollection;
    }

    public void setHrdepartmentCollection(Collection<Hrdepartment> hrdepartmentCollection) {
        this.hrdepartmentCollection = hrdepartmentCollection;
    }

    @XmlTransient
    public Collection<Ictdepartment> getIctdepartmentCollection() {
        return ictdepartmentCollection;
    }

    public void setIctdepartmentCollection(Collection<Ictdepartment> ictdepartmentCollection) {
        this.ictdepartmentCollection = ictdepartmentCollection;
    }

    @XmlTransient
    public Collection<Deduction> getDeductionCollection() {
        return deductionCollection;
    }

    public void setDeductionCollection(Collection<Deduction> deductionCollection) {
        this.deductionCollection = deductionCollection;
    }

    @XmlTransient
    public Collection<Facdepartment> getFacdepartmentCollection() {
        return facdepartmentCollection;
    }

    public void setFacdepartmentCollection(Collection<Facdepartment> facdepartmentCollection) {
        this.facdepartmentCollection = facdepartmentCollection;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeID != null ? employeeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeID == null && other.employeeID != null) || (this.employeeID != null && !this.employeeID.equals(other.employeeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Employee[ employeeID=" + employeeID + " ]";
    }
    
}

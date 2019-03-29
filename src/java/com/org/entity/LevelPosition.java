/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Moses Nwaeze
 */
@Entity
@Table(name = "level_position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LevelPosition.findAll", query = "SELECT l FROM LevelPosition l"),
    @NamedQuery(name = "LevelPosition.findByLevelID", query = "SELECT l FROM LevelPosition l WHERE l.levelID = :levelID"),
    @NamedQuery(name = "LevelPosition.findByLevelAmount", query = "SELECT l FROM LevelPosition l WHERE l.levelAmount = :levelAmount"),
    @NamedQuery(name = "LevelPosition.findByLevelType", query = "SELECT l FROM LevelPosition l WHERE l.levelType = :levelType")})
public class LevelPosition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LevelID")
    private Integer levelID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "LevelAmount")
    private BigDecimal levelAmount;
    @Size(max = 50)
    @Column(name = "LevelType")
    private String levelType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeLevel")
    private Collection<Employee> employeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberLevel")
    private Collection<Hcudepartment> hcudepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberLevel")
    private Collection<Hrdepartment> hrdepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberLevel")
    private Collection<Ictdepartment> ictdepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberLevel")
    private Collection<Facdepartment> facdepartmentCollection;
    @OneToMany(mappedBy = "gradelevel")
    private Collection<Payroll> payrollCollection;

    public LevelPosition() {
    }

    public LevelPosition(Integer levelID) {
        this.levelID = levelID;
    }

    public LevelPosition(Integer levelID, BigDecimal levelAmount) {
        this.levelID = levelID;
        this.levelAmount = levelAmount;
    }

    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    public BigDecimal getLevelAmount() {
        return levelAmount;
    }

    public void setLevelAmount(BigDecimal levelAmount) {
        this.levelAmount = levelAmount;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
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
    public Collection<Facdepartment> getFacdepartmentCollection() {
        return facdepartmentCollection;
    }

    public void setFacdepartmentCollection(Collection<Facdepartment> facdepartmentCollection) {
        this.facdepartmentCollection = facdepartmentCollection;
    }

    @XmlTransient
    public Collection<Payroll> getPayrollCollection() {
        return payrollCollection;
    }

    public void setPayrollCollection(Collection<Payroll> payrollCollection) {
        this.payrollCollection = payrollCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelID != null ? levelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelPosition)) {
            return false;
        }
        LevelPosition other = (LevelPosition) object;
        if ((this.levelID == null && other.levelID != null) || (this.levelID != null && !this.levelID.equals(other.levelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.LevelPosition[ levelID=" + levelID + " ]";
    }
    
}

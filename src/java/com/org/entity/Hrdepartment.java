/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moses Nwaeze
 */
@Entity
@Table(name = "hrdepartment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hrdepartment.findAll", query = "SELECT h FROM Hrdepartment h"),
    @NamedQuery(name = "Hrdepartment.findById", query = "SELECT h FROM Hrdepartment h WHERE h.id = :id"),
    @NamedQuery(name = "Hrdepartment.findByMemberName", query = "SELECT h FROM Hrdepartment h WHERE h.memberName = :memberName")})
public class Hrdepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MemberName")
    private String memberName;
    @JoinColumn(name = "MemberLevel", referencedColumnName = "LevelID")
    @ManyToOne(optional = false)
    private LevelPosition memberLevel;
    @JoinColumn(name = "MemberID", referencedColumnName = "EmployeeID")
    @ManyToOne(optional = false)
    private Employee memberID;

    public Hrdepartment() {
    }

    public Hrdepartment(Integer id) {
        this.id = id;
    }

    public Hrdepartment(Integer id, String memberName) {
        this.id = id;
        this.memberName = memberName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LevelPosition getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(LevelPosition memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Employee getMemberID() {
        return memberID;
    }

    public void setMemberID(Employee memberID) {
        this.memberID = memberID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hrdepartment)) {
            return false;
        }
        Hrdepartment other = (Hrdepartment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Hrdepartment[ id=" + id + " ]";
    }
    
}

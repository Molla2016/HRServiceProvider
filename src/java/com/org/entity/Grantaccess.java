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
@Table(name = "grantaccess")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grantaccess.findAll", query = "SELECT g FROM Grantaccess g"),
    @NamedQuery(name = "Grantaccess.findByGrantID", query = "SELECT g FROM Grantaccess g WHERE g.grantID = :grantID"),
    @NamedQuery(name = "Grantaccess.findByGrantLevel", query = "SELECT g FROM Grantaccess g WHERE g.grantLevel = :grantLevel"),
    @NamedQuery(name = "Grantaccess.findByGrantName", query = "SELECT g FROM Grantaccess g WHERE g.grantName = :grantName")})
public class Grantaccess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GrantID")
    private Integer grantID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GrantLevel")
    private String grantLevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 17)
    @Column(name = "GrantName")
    private String grantName;

    public Grantaccess() {
    }

    public Grantaccess(Integer grantID) {
        this.grantID = grantID;
    }

    public Grantaccess(Integer grantID, String grantLevel, String grantName) {
        this.grantID = grantID;
        this.grantLevel = grantLevel;
        this.grantName = grantName;
    }

    public Integer getGrantID() {
        return grantID;
    }

    public void setGrantID(Integer grantID) {
        this.grantID = grantID;
    }

    public String getGrantLevel() {
        return grantLevel;
    }

    public void setGrantLevel(String grantLevel) {
        this.grantLevel = grantLevel;
    }

    public String getGrantName() {
        return grantName;
    }

    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grantID != null ? grantID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grantaccess)) {
            return false;
        }
        Grantaccess other = (Grantaccess) object;
        if ((this.grantID == null && other.grantID != null) || (this.grantID != null && !this.grantID.equals(other.grantID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Grantaccess[ grantID=" + grantID + " ]";
    }
    
}

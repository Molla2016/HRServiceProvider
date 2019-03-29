/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moses Nwaeze
 */
@Entity
@Table(name = "promotion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
    @NamedQuery(name = "Promotion.findByPromotionID", query = "SELECT p FROM Promotion p WHERE p.promotionID = :promotionID"),
    @NamedQuery(name = "Promotion.findByDatePromoted", query = "SELECT p FROM Promotion p WHERE p.datePromoted = :datePromoted"),
    @NamedQuery(name = "Promotion.findByEmployeePromoted", query = "SELECT p FROM Promotion p WHERE p.employeePromoted = :employeePromoted"),
    @NamedQuery(name = "Promotion.findByOldGradePositon", query = "SELECT p FROM Promotion p WHERE p.oldGradePositon = :oldGradePositon"),
    @NamedQuery(name = "Promotion.findByNewGradePosition", query = "SELECT p FROM Promotion p WHERE p.newGradePosition = :newGradePosition")})
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PromotionID")
    private Integer promotionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatePromoted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePromoted;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EmployeePromoted")
    private String employeePromoted;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "OldGradePositon")
    private String oldGradePositon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NewGradePosition")
    private String newGradePosition;

    public Promotion() {
    }

    public Promotion(Integer promotionID) {
        this.promotionID = promotionID;
    }

    public Promotion(Integer promotionID, Date datePromoted, String employeePromoted, String oldGradePositon, String newGradePosition) {
        this.promotionID = promotionID;
        this.datePromoted = datePromoted;
        this.employeePromoted = employeePromoted;
        this.oldGradePositon = oldGradePositon;
        this.newGradePosition = newGradePosition;
    }

    public Integer getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Integer promotionID) {
        this.promotionID = promotionID;
    }

    public Date getDatePromoted() {
        return datePromoted;
    }

    public void setDatePromoted(Date datePromoted) {
        this.datePromoted = datePromoted;
    }

    public String getEmployeePromoted() {
        return employeePromoted;
    }

    public void setEmployeePromoted(String employeePromoted) {
        this.employeePromoted = employeePromoted;
    }

    public String getOldGradePositon() {
        return oldGradePositon;
    }

    public void setOldGradePositon(String oldGradePositon) {
        this.oldGradePositon = oldGradePositon;
    }

    public String getNewGradePosition() {
        return newGradePosition;
    }

    public void setNewGradePosition(String newGradePosition) {
        this.newGradePosition = newGradePosition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionID != null ? promotionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.promotionID == null && other.promotionID != null) || (this.promotionID != null && !this.promotionID.equals(other.promotionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.org.entity.Promotion[ promotionID=" + promotionID + " ]";
    }
    
}

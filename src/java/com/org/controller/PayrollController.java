package com.org.controller;

import com.org.entity.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import javax.ejb.Stateful;
import javax.persistence.PersistenceContext;

@Stateful
public class PayrollController {

    @PersistenceContext(unitName = "HRServicesProviderPU")
    private EntityManager em;

    public PayrollController() {

    }

    public EntityManager getEntityManager() {
        return em;
    }

    // A property that capture the possible offenses 
    private static Map<String, BigDecimal> OFFENSES = new HashMap<String, BigDecimal>() {
        {
            put("Lateness", BigDecimal.valueOf(100));
            put("Fighting", BigDecimal.valueOf(5000));
            put("Stealing", BigDecimal.valueOf(3000));
            put("Destruction of Property", BigDecimal.valueOf(10000));
            put("Abuse of working hour", BigDecimal.valueOf(2000));
            put("Gossiping", BigDecimal.valueOf(1000));
            put("Malice", BigDecimal.valueOf(500));
            put("Task left undo or late submission of task", BigDecimal.valueOf(1000));
            put("Fighting", BigDecimal.valueOf(5000));
            put("Leaving office when not dismiss", BigDecimal.valueOf(1000));
            put("Sleeping during working hour", BigDecimal.valueOf(500));
            put("Using phone during working hour", BigDecimal.valueOf(1000));
        }
    };

    public List<Payroll> findAll() {
        TypedQuery<Payroll> tq = em.createNamedQuery("Payroll.findAll", Payroll.class);
        List<Payroll> payroll = tq.getResultList();
        if (!payroll.isEmpty()) {
            return payroll;
        } else {
            throw new EntityNotFoundException("Entity set is empty");
        }
    }

    public Payroll find(int id) {
        Payroll payroll = em.find(Payroll.class, id);
        if (payroll != null) {
            return payroll;
        }
        throw new EntityNotFoundException("Cannot retrieve null entity");
    }

    public boolean create(Payroll payroll) throws SQLException {
        if (payroll != null) {
            em.persist(payroll);
            return true;
        }
        return false;

    }

    public boolean remove(int id) {
        Payroll payroll = em.find(Payroll.class, id);
        if (payroll != null) {
            em.remove(payroll);
            return true;
        }
        return false;
    }

    public boolean update(Payroll pay) throws SQLException {
        //Employee employee = em.find(Employee.class, id);
        if (pay != null) {
            em.merge(pay);
            return true;
        }
        return false;

    }

    public static BigDecimal getOFFENSE(String offense) {
        return OFFENSES.get(offense);
    }

    public static void setOFFENSES(String offenseTitle, BigDecimal amount) {
        OFFENSES.put(offenseTitle, amount);
    }

    public Map<String, BigDecimal> getAmountDeducted() {
        Map<String, BigDecimal> cashRemoved = new HashMap<>();
        List<Deduction> deductions = getDeductions();
        
        if(deductions.isEmpty()){
            throw new RuntimeException("Deduction is empty");
        }

        for (Deduction deduct : deductions) {

            if (cashRemoved.containsKey(deduct.getEmployeeID().getEmployeeID())) {
                BigDecimal initialAmount = cashRemoved.get(deduct.getEmployeeID().getEmployeeID());
                cashRemoved.remove(deduct.getEmployeeID().getEmployeeID());
                cashRemoved.put(deduct.getEmployeeID().getEmployeeID(), deduct.getAmountDeducted().add(initialAmount));

            } else {
                cashRemoved.put(deduct.getEmployeeID().getEmployeeID(), deduct.getAmountDeducted());
            }

        }

        return cashRemoved;
    }

    public List<Deduction> getDeductions() {
        TypedQuery<Deduction> tq = em.createNamedQuery("Deduction.findAll", Deduction.class);
        List<Deduction> deductions = tq.getResultList();
        return deductions;
    }
    
}

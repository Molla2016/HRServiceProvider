package com.org.controller;

import com.org.entity.*;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import javax.ejb.Stateful;
import javax.persistence.PersistenceContext;

@Stateful
public class EmployeeController {

    @PersistenceContext(unitName = "HRServicesProviderPU")
    private EntityManager em;

    public EmployeeController() {

    }

    public EntityManager getEntityManager() {
        return em;
    }

    public List<Employee> findAll() {
        TypedQuery<Employee> tq = em.createNamedQuery("Employee.findAll", Employee.class);
        List<Employee> employees = tq.getResultList();
        if (!employees.isEmpty()) {
            return employees;
        } else {
            throw new EntityNotFoundException("Entity set is empty");
        }
    }

    public Employee find(String id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            return employee;
        }
        throw new EntityNotFoundException("Cannot retrieve null entity");
    }

    public boolean create(Employee employee) throws SQLException {
        if (employee != null) {
            em.persist(employee);
            return true;
        }
        return false;

    }

    public boolean remove(String id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
            return true;
        }
        return false;
    }

    public boolean update(Employee employee) throws SQLException {
        if (employee != null) {
            em.merge(employee);
            return true;
        }
        return false;

    }

}

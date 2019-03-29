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
public class DepartmentController {

    @PersistenceContext(unitName = "HRServicesProviderPU")
    private EntityManager em;

    public DepartmentController() {

    }

    public EntityManager getEntityManager() {
        return em;
    }

    public List<Department> findAll() {
        TypedQuery<Department> tq = em.createNamedQuery("Department.findAll", Department.class);
        List<Department> department = tq.getResultList();
        if (!department.isEmpty()) {
            return department;
        } else {
            throw new EntityNotFoundException("Entity set is empty");
        }
    }

    public Department find(int id) {
        Department department = em.find(Department.class, id);
        if (department != null) {
            return department;
        }
        throw new EntityNotFoundException("Cannot retrieve null entity");
    }

    public boolean create(Department department) throws SQLException {
        if (department != null) {
            em.persist(department);
            return true;
        }
        return false;

    }

    public boolean remove(int id) {
        Department department = em.find(Department.class, id);
        if (department != null) {
            em.remove(department);
            return true;
        }
        return false;
    }

    public boolean update(Department depart) throws SQLException {
        //Employee employee = em.find(Employee.class, id);
        if (depart != null) {
            em.merge(depart);
            return true;
        }
        return false;

    }

}

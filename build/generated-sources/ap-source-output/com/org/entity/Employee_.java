package com.org.entity;

import com.org.entity.Deduction;
import com.org.entity.Department;
import com.org.entity.Facdepartment;
import com.org.entity.Hcudepartment;
import com.org.entity.Hrdepartment;
import com.org.entity.Ictdepartment;
import com.org.entity.LevelPosition;
import com.org.entity.Payroll;
import com.org.entity.Registration;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> employeeFullName;
    public static volatile CollectionAttribute<Employee, Deduction> deductionCollection;
    public static volatile SingularAttribute<Employee, String> employeeID;
    public static volatile SingularAttribute<Employee, Integer> employeeAge;
    public static volatile SingularAttribute<Employee, String> employeeGender;
    public static volatile CollectionAttribute<Employee, Hrdepartment> hrdepartmentCollection;
    public static volatile SingularAttribute<Employee, String> employeeAddress;
    public static volatile SingularAttribute<Employee, Department> employeeDepartment;
    public static volatile CollectionAttribute<Employee, Facdepartment> facdepartmentCollection;
    public static volatile CollectionAttribute<Employee, Hcudepartment> hcudepartmentCollection;
    public static volatile SingularAttribute<Employee, LevelPosition> employeeLevel;
    public static volatile SingularAttribute<Employee, Date> employeeDOB;
    public static volatile CollectionAttribute<Employee, Ictdepartment> ictdepartmentCollection;
    public static volatile SingularAttribute<Employee, Registration> registration;
    public static volatile SingularAttribute<Employee, Payroll> payroll;

}
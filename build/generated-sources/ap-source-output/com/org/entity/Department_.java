package com.org.entity;

import com.org.entity.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> departmentName;
    public static volatile SingularAttribute<Department, String> departmentHeadName;
    public static volatile SingularAttribute<Department, String> departmentAddress;
    public static volatile SingularAttribute<Department, Integer> departmentID;
    public static volatile CollectionAttribute<Department, Employee> employeeCollection;
    public static volatile SingularAttribute<Department, String> departmentDescription;

}
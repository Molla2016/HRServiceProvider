package com.org.entity;

import com.org.entity.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Registration.class)
public class Registration_ { 

    public static volatile SingularAttribute<Registration, String> password;
    public static volatile SingularAttribute<Registration, Integer> regID;
    public static volatile SingularAttribute<Registration, Employee> employeeID;
    public static volatile SingularAttribute<Registration, String> email;

}
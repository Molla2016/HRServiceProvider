package com.org.entity;

import com.org.entity.Employee;
import com.org.entity.LevelPosition;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Hrdepartment.class)
public class Hrdepartment_ { 

    public static volatile SingularAttribute<Hrdepartment, LevelPosition> memberLevel;
    public static volatile SingularAttribute<Hrdepartment, String> memberName;
    public static volatile SingularAttribute<Hrdepartment, Integer> id;
    public static volatile SingularAttribute<Hrdepartment, Employee> memberID;

}
package com.org.entity;

import com.org.entity.Employee;
import com.org.entity.LevelPosition;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Hcudepartment.class)
public class Hcudepartment_ { 

    public static volatile SingularAttribute<Hcudepartment, LevelPosition> memberLevel;
    public static volatile SingularAttribute<Hcudepartment, String> memberName;
    public static volatile SingularAttribute<Hcudepartment, Integer> id;
    public static volatile SingularAttribute<Hcudepartment, Employee> memberID;

}
package com.org.entity;

import com.org.entity.Employee;
import com.org.entity.Facdepartment;
import com.org.entity.Hcudepartment;
import com.org.entity.Hrdepartment;
import com.org.entity.Ictdepartment;
import com.org.entity.Payroll;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(LevelPosition.class)
public class LevelPosition_ { 

    public static volatile CollectionAttribute<LevelPosition, Payroll> payrollCollection;
    public static volatile SingularAttribute<LevelPosition, String> levelType;
    public static volatile SingularAttribute<LevelPosition, Integer> levelID;
    public static volatile CollectionAttribute<LevelPosition, Ictdepartment> ictdepartmentCollection;
    public static volatile CollectionAttribute<LevelPosition, Employee> employeeCollection;
    public static volatile CollectionAttribute<LevelPosition, Hrdepartment> hrdepartmentCollection;
    public static volatile SingularAttribute<LevelPosition, BigDecimal> levelAmount;
    public static volatile CollectionAttribute<LevelPosition, Facdepartment> facdepartmentCollection;
    public static volatile CollectionAttribute<LevelPosition, Hcudepartment> hcudepartmentCollection;

}
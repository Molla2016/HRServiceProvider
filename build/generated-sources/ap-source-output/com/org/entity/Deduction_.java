package com.org.entity;

import com.org.entity.Employee;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Deduction.class)
public class Deduction_ { 

    public static volatile SingularAttribute<Deduction, String> employeeName;
    public static volatile SingularAttribute<Deduction, Date> dateOffenseCommitted;
    public static volatile SingularAttribute<Deduction, BigDecimal> amountDeducted;
    public static volatile SingularAttribute<Deduction, String> offenses;
    public static volatile SingularAttribute<Deduction, Employee> employeeID;
    public static volatile SingularAttribute<Deduction, Integer> deductionID;

}
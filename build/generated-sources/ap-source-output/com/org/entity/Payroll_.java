package com.org.entity;

import com.org.entity.Employee;
import com.org.entity.LevelPosition;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Payroll.class)
public class Payroll_ { 

    public static volatile SingularAttribute<Payroll, String> employeeName;
    public static volatile SingularAttribute<Payroll, Date> datePaid;
    public static volatile SingularAttribute<Payroll, BigDecimal> amountDeducted;
    public static volatile SingularAttribute<Payroll, BigDecimal> amountPaid;
    public static volatile SingularAttribute<Payroll, BigDecimal> netAmount;
    public static volatile SingularAttribute<Payroll, Employee> employeeID;
    public static volatile SingularAttribute<Payroll, Integer> payrollID;
    public static volatile SingularAttribute<Payroll, String> payStatus;
    public static volatile SingularAttribute<Payroll, LevelPosition> gradelevel;

}
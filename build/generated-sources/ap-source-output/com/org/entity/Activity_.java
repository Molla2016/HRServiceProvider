package com.org.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Activity.class)
public class Activity_ { 

    public static volatile SingularAttribute<Activity, Integer> activityID;
    public static volatile SingularAttribute<Activity, Date> datePaid;
    public static volatile SingularAttribute<Activity, Date> dob;
    public static volatile SingularAttribute<Activity, Date> datePromoted;
    public static volatile SingularAttribute<Activity, BigDecimal> salaryPaid;

}
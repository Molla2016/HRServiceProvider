package com.org.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T15:41:27")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, String> employeePromoted;
    public static volatile SingularAttribute<Promotion, Date> datePromoted;
    public static volatile SingularAttribute<Promotion, String> oldGradePositon;
    public static volatile SingularAttribute<Promotion, Integer> promotionID;
    public static volatile SingularAttribute<Promotion, String> newGradePosition;

}
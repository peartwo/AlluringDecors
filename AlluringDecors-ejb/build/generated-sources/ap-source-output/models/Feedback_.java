package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, String> content;
    public static volatile SingularAttribute<Feedback, String> email;
    public static volatile SingularAttribute<Feedback, Date> dateCreated;
    public static volatile SingularAttribute<Feedback, String> firstname;
    public static volatile SingularAttribute<Feedback, Integer> idFeedback;

}
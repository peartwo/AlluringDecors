package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Client;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> email;
    public static volatile CollectionAttribute<User, Client> clientCollection;
    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, Date> dateCreated;
    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> userType;

}
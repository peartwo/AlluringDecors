package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.ServiceRequest;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> adressStreet;
    public static volatile SingularAttribute<Client, String> adressCity;
    public static volatile SingularAttribute<Client, String> phoneNumber;
    public static volatile SingularAttribute<Client, String> addressNumber;
    public static volatile CollectionAttribute<Client, ServiceRequest> serviceRequestCollection;
    public static volatile SingularAttribute<Client, Integer> idClient;
    public static volatile SingularAttribute<Client, User> idUser;
    public static volatile SingularAttribute<Client, String> addressZip;

}
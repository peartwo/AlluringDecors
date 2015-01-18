package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Client;
import models.Service;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(ServiceRequest.class)
public class ServiceRequest_ { 

    public static volatile SingularAttribute<ServiceRequest, Integer> idServiceRequest;
    public static volatile CollectionAttribute<ServiceRequest, Service> serviceCollection;
    public static volatile SingularAttribute<ServiceRequest, Date> dateCreated;
    public static volatile SingularAttribute<ServiceRequest, Client> idClient;
    public static volatile SingularAttribute<ServiceRequest, String> invoiceNumber;

}
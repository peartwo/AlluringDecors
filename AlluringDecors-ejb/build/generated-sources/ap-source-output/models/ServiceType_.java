package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Service;
import models.ServiceDomain;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(ServiceType.class)
public class ServiceType_ { 

    public static volatile CollectionAttribute<ServiceType, ServiceDomain> serviceDomainCollection;
    public static volatile SingularAttribute<ServiceType, String> name;
    public static volatile CollectionAttribute<ServiceType, Service> serviceCollection;
    public static volatile SingularAttribute<ServiceType, Integer> idServiceType;

}
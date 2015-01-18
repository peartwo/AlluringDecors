package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Service;
import models.ServiceType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(ServiceDomain.class)
public class ServiceDomain_ { 

    public static volatile SingularAttribute<ServiceDomain, Integer> idServiceDomain;
    public static volatile CollectionAttribute<ServiceDomain, ServiceType> serviceTypeCollection;
    public static volatile SingularAttribute<ServiceDomain, String> name;
    public static volatile CollectionAttribute<ServiceDomain, Service> serviceCollection;

}
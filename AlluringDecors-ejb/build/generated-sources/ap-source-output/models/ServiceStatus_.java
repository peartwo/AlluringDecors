package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Service;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(ServiceStatus.class)
public class ServiceStatus_ { 

    public static volatile SingularAttribute<ServiceStatus, String> name;
    public static volatile CollectionAttribute<ServiceStatus, Service> serviceCollection;
    public static volatile SingularAttribute<ServiceStatus, Integer> idServiceStatus;

}
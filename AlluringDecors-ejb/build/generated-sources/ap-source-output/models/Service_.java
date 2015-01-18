package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Project;
import models.ServiceDomain;
import models.ServiceRequest;
import models.ServiceStatus;
import models.ServiceType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, String> content;
    public static volatile SingularAttribute<Service, ServiceDomain> idServiceDomain;
    public static volatile SingularAttribute<Service, String> address;
    public static volatile SingularAttribute<Service, Integer> idService;
    public static volatile SingularAttribute<Service, Date> datePaid;
    public static volatile SingularAttribute<Service, ServiceRequest> idServiceRequest;
    public static volatile SingularAttribute<Service, Float> billedAmount;
    public static volatile SingularAttribute<Service, ServiceStatus> idServiceStatus;
    public static volatile CollectionAttribute<Service, Project> projectCollection;
    public static volatile SingularAttribute<Service, ServiceType> idServiceType;

}
package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Service;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-18T02:28:43")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, String> description;
    public static volatile SingularAttribute<Project, Service> idService;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, Date> dateEnd;
    public static volatile SingularAttribute<Project, Integer> idProject;
    public static volatile SingularAttribute<Project, Date> dateStart;

}
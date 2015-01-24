/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.ServiceType;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class ServiceTypeFacade extends AbstractFacade<ServiceType> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceTypeFacade() {
        super(ServiceType.class);
    }
    
    public List<ServiceType> findServicesByDomainId(int domainId) {
        List<ServiceType> serviceTypes = (List<ServiceType>) em.createNativeQuery("SELECT b.id_service_type, b.name "
                + "FROM (SELECT * FROM r_service_domain_service_type WHERE id_service_domain = ?) AS a\n" 
                + "INNER JOIN service_type as b ON b.id_service_type = a.id_service_type", ServiceType.class)
                .setParameter(1, domainId).getResultList();
        
        return serviceTypes;
    }
    
    public List<ServiceType> findServicesOutOfDomain(int domainId) {
        List<ServiceType> serviceTypes = (List<ServiceType>) em.createNativeQuery(
                "SELECT * FROM service_type AS s LEFT JOIN "
                +"(SELECT id_service_type FROM alluring_decors.r_service_domain_service_type "
                +"WHERE id_service_domain = ?) AS d "
                +"ON s.id_service_type = d.id_service_type WHERE d.id_service_type IS NULL", ServiceType.class)
                .setParameter(1, domainId).getResultList();
        return serviceTypes;
    }
}
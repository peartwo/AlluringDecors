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
import models.Service;
import models.ServiceRequest;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class ServiceFacade extends AbstractFacade<Service> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }
    
    public List<Service> findByServiceRequestId(ServiceRequest sr) {
        List<Service> services = (List<Service>) em.createNamedQuery("Service.findByIdServiceRequest", Service.class).setParameter("idServiceRequest", sr).getResultList();
        return services;
    }
    
    public List<Service> findServicesByStatusID(int id) {
    List<Service> services = (List<Service>) em.createNativeQuery(
            "SELECT * FROM service WHERE id_service_type = ?", 
            Service.class).setParameter(1, id).getResultList();
    return services;
    }
}

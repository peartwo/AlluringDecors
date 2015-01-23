/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.Client;
import models.ServiceRequest;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class ServiceRequestFacade extends AbstractFacade<ServiceRequest> {    
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceRequestFacade() {
        super(ServiceRequest.class);
    }
    
    public ServiceRequest getCartByClient(Client client){
        List<ServiceRequest> requests = (List<ServiceRequest>) em.createNativeQuery(
                "SELECT * FROM service_request WHERE id_client = ?", 
                ServiceRequest.class).setParameter(1, client.getIdClient()).getResultList(); 
        if (!requests.isEmpty()) {           
            return requests.get(0);
        } else {
            ServiceRequest sr = new ServiceRequest();
            sr.setIdClient(client);
            sr.setDateCreated(new Date());
            create(sr);
            return sr;
        }
    }
}

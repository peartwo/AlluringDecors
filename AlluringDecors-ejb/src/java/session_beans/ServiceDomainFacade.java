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
import models.ServiceDomain;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class ServiceDomainFacade extends AbstractFacade<ServiceDomain> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceDomainFacade() {
        super(ServiceDomain.class);
    }
    
    public ServiceDomain findByName(String name) {
        List<ServiceDomain> domains = (List<ServiceDomain>) em.createNamedQuery("ServiceDomain.findByName", ServiceDomain.class).setParameter("name", name).getResultList();
        return domains.isEmpty() ? null : domains.get(0);
    }
    
}

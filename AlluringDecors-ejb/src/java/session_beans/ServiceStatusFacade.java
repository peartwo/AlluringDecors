/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.ServiceStatus;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class ServiceStatusFacade extends AbstractFacade<ServiceStatus> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceStatusFacade() {
        super(ServiceStatus.class);
    }
    
}

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
import models.Client;
import models.User;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    public Client findByUserId(User idUser) {
        List<Client> clients = (List<Client>) em.createNamedQuery("Client.findByIdUser", Client.class).setParameter("idUser", idUser).getResultList();
        return clients.isEmpty() ? null : clients.get(0);
    }
    
}

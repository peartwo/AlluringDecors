/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.Feedback;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class FeedbackFacade extends AbstractFacade<Feedback> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
    }
    
}

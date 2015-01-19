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
import models.Faq;

/**
 *
 * @author zuzanahruskova
 */
@Stateless
public class FaqFacade extends AbstractFacade<Faq> {
    @PersistenceContext(unitName = "AlluringDecors-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaqFacade() {
        super(Faq.class);
    }

    public Faq findByQuestion(String question) {
        List<Faq> allfaq = (List<Faq>) em.createNamedQuery("Faq.findByQuestion", Faq.class).setParameter("question", question).getResultList();
        return allfaq.isEmpty() ? null : allfaq.get(0);
    }
    
}

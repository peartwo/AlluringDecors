/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Client;
import models.Service;
import models.ServiceRequest;
import models.User;
import session_beans.ClientFacade;
import session_beans.ServiceDomainFacade;
import session_beans.ServiceFacade;
import session_beans.ServiceRequestFacade;
import session_beans.ServiceStatusFacade;
import session_beans.ServiceTypeFacade;
import session_beans.UserFacade;

/**
 *
 * @author Milos Varga
 */
public class LoadClientServiceOrders extends HttpServlet {
    @EJB
    private ServiceRequestFacade serviceRequestObj;
    @EJB
    private UserFacade userObj;
    @EJB
    private ClientFacade clientObj;
    @EJB 
    private ServiceDomainFacade serviceDomainObj;
    @EJB 
    private ServiceTypeFacade serviceTypeObj;
    @EJB
    private ServiceFacade serviceObj;
    @EJB
    private ServiceStatusFacade serviceSatusObj;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("currentUserId");
        System.out.println(userId);
        User user = userObj.find(userId);
        Client client = clientObj.findByUserId(user);
        ServiceRequest sr = serviceRequestObj.getCartByClient(client);
        Collection<Service> serviceCollection = sr.getServiceCollection();
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            for (Service s : serviceCollection) {
                out.println(" -> "+s.getIdService()+": " 
                        + s.getIdServiceDomain().getName() + " / "
                        + s.getIdServiceType().getName() + " / "
                        + s.getIdServiceStatus().getName()+ "<br>");
            }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

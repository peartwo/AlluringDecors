/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Client;
import models.Service;
import models.ServiceDomain;
import models.ServiceRequest;
import models.ServiceStatus;
import models.ServiceType;
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
 * @author zuzanahruskova
 */
public class AddOrderToRequest extends HttpServlet {

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
        User user = userObj.find(userId);
        Client client = clientObj.findByUserId(user);
        ServiceDomain domain = serviceDomainObj.find(Integer.parseInt(request.getParameter("currentDomainID")));
        ServiceType serviceType = serviceTypeObj.find(Integer.parseInt(request.getParameter("currentServiceID")));
        ServiceStatus serviceStatus = serviceSatusObj.find(1);
        
        ServiceRequest sr = serviceRequestObj.getCartByClient(client);        
        Service service = new Service();    
            service.setIdServiceRequest(sr);
            service.setIdServiceDomain(domain);
            service.setIdServiceType(serviceType);
            service.setIdServiceStatus(serviceStatus);
            service.setAddress(request.getParameter("serviceAddress"));
            service.setContent(request.getParameter("serviceContent"));
            service.setBilledAmount(0.00f);
        serviceObj.create(service);
        System.out.println("Service created.");
        
        response.sendRedirect("client-orders.jsp");
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"col-md-8 col-md-offset-2\">Service " + serviceType + " was successfully added to your <a href='client-orders.jsp'>service requests</a>.</div>");
        }*/
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

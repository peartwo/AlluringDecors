/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Client;
import models.ServiceDomain;
import models.ServiceType;
import models.User;
import session_beans.ClientFacade;
import session_beans.ServiceDomainFacade;
import session_beans.ServiceRequestFacade;
import session_beans.ServiceTypeFacade;
import session_beans.UserFacade;

/**
 *
 * @author zuzanahruskova
 */
public class CreateServiceOrder extends HttpServlet {
    @EJB
    private ServiceTypeFacade serviceTypeObj;
    @EJB
    private UserFacade userObj;
    @EJB
    private ServiceDomainFacade serviceDomainObj;
    @EJB
    private ClientFacade clientObj;
    @EJB
    private ServiceRequestFacade serviceRequestObj;

    
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
        int domainID = Integer.parseInt(request.getParameter("currentDomainID"));
        ServiceDomain domain = serviceDomainObj.find(domainID);       
        int userID = (int) session.getAttribute("currentUserId");
        User user = userObj.find(userID);
        Client client = clientObj.findByUserId(user);
        session.setAttribute("currentClientId", client.getIdClient());
        
        ServiceType service;
        int serviceID = Integer.parseInt(request.getParameter("currentServiceID"));
        service = serviceTypeObj.find(serviceID);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<h4 class=\"light\"><strong class='text-warning'>Client Name: </strong>" + user.getFirstname() + " " + user.getSurname() + "</h4>");
            out.println("<h4 class=\"light\"><strong class='text-warning'>Requested Service: </strong>" + domain.getName() + " / " + service.getName() + "</h4>");
            out.println("<h4 class=\"text-success\">Please, fill in the information needed to process your request</h4>");
            out.println("<form class=\"form-horizontal\" action='AddOrderToRequest' method=\"post\">");
            out.println("<fieldset>");
            out.println("<div class='col-md-12 form-group has-warning'><textarea class='form-control' name='serviceAddress' placeholder='Addres, where the service is to be provided:'></textarea></div>");
            out.println("<div class='col-md-12 form-group has-warning'><textarea class='form-control' name='serviceContent' placeholder='Please enter any additional comments here:'></textarea></div>");
            out.println("<h4 class=\"light\">Phone number: "+ client.getPhoneNumber() + "</h4>");
            out.println("<h4><strong class='text-warning'>Billing Address:</strong></h4>");    
            out.println("<h4 class=\"light\">" + client.getAdressStreet()+ ", " +client.getAddressNumber()+ "</h4>");
            out.println("<h4 class=\"light\">" + client.getAddressZip()+ " - " +client.getAdressCity()+ "</h4>");   
            out.println("<button type='submit' class=\"btn btn-warning btn-lg\" value='Submit'>Order the above service</button>");
            out.println("</fieldset>");
            out.println("</form>");
            
            RequestDispatcher rd = request.getRequestDispatcher("client-newserviceorder.jsp");
            rd.forward(request, response);
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

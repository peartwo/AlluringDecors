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
import models.ServiceDomain;
import models.ServiceType;
import session_beans.ServiceDomainFacade;
import session_beans.ServiceTypeFacade;

/**
 *
 * @author zuzanahruskova
 */
public class AddServiceTypeToDomain extends HttpServlet {

    @EJB
    private ServiceDomainFacade serviceDomainObj;
    @EJB
    private ServiceTypeFacade serviceTypeObj;

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
        int domainId = Integer.parseInt(request.getParameter("domainId"));
        String name = request.getParameter("name");
        ServiceDomain sd = serviceDomainObj.find(domainId);
        ServiceType st = serviceTypeObj.findServiceTypeByName(name);

        Collection<ServiceType> services = sd.getServiceTypeCollection();
        for (ServiceType service : services) {
            System.out.println(service.getName());
        }
        services.add(st);
        sd.setServiceTypeCollection(services);
        for (ServiceType serv : sd.getServiceTypeCollection()) {
            System.out.println(serv.getName());
        }
        serviceDomainObj.edit(sd);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"col-md-3 yellowbox\" id=" + st.getIdServiceType() + ">");
            out.println("<h3>" + st.getName() + "</h3>");
            out.println("<center><img src=\"images/domains/" + st.getIdServiceType() + "-2.jpg\" alt=\"" + st.getName() + "\" class=\"img-circle\" /></center>");
            out.println("<button class=\"btn btn-default editst\"><span class=\"fa fa-edit\">Edit</span></button>");
            out.println("<button class=\"btn btn-danger deletest\"><span class=\"fa fa-minus-square\">Delete</span></button>");
            out.println("<button id='" + domainId + "' class=\"btn btn-default removest\"><span class=\"fa fa-minus-square\">Remove from Domain</span></button>");
            //System.out.println("<center><img src=\"images/domains/" + domainId + ".jpg\" alt=\"" + sd.getName() + "\" class=\"img-circle\" /></center>");
            out.println("</div>");
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

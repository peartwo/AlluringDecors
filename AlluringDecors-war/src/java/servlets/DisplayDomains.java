/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ServiceDomain;
import session_beans.ServiceDomainFacade;

/**
 *
 * @author zuzanahruskova
 */
public class DisplayDomains extends HttpServlet {

    @EJB
    private ServiceDomainFacade domainObj;

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
        List<ServiceDomain> domains = domainObj.findAll();
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        // If current user is admin display buttons to update or delete domain
        if (session.getAttribute("userRole") == null) {
            out.println("<h2><a class=\"text-danger\" href=\"registration.jsp\">Register here</a> to order services</h2>");
        }
        for (ServiceDomain sd : domains) {
            out.println("<div class=\"col-md-6 domainbox\" id=" + sd.getIdServiceDomain() + " >");
            // If current user is admin display buttons to update or delete domain
            if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                out.println("<button type=\"submit\" class=\"btn btn-default pull-right edit\">Edit</button>"
                        + "<button class=\"btn btn-danger pull-right delete\">Delete</button>");
            }
            out.println("<h3 id=\"name" + sd.getIdServiceDomain() + "\">" + sd.getName() + "</h3>");
            out.println("<center><img src=\"images/domains/" + sd.getIdServiceDomain() + ".jpg\" alt=" + sd.getName() + " class=\"img-circle\" /></center>");
            out.println("</div>");
        }
        // If current user is admin display a button to add new domain
        if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
            out.println("<div id=\"newDomain\" class=\"col-md-6 newdomainbox\">");
            out.println("<button id=\"addDomain\" class=\"btn btn-large btn-success\"><span class=\"fa fa-plus-square\">Add New Domain</span></button>");
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

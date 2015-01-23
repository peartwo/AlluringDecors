/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ServiceType;
import session_beans.ServiceTypeFacade;

/**
 *
 * @author zuzanahruskova
 */
public class DisplayServices extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        List<ServiceType> foundServices;
        foundServices = serviceTypeObj.findServicesByDomainId(id);
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            if (!foundServices.isEmpty()) {

                out.println("<a class=\"col-md-12 text-center\" href=\"services.jsp\">"
                        + "<span class=\"fa fa-arrow-circle-left bigicon\">Return to domains</span></a>");
                // out.println("<h1>Services for Domain " + id + "</h1>");                   
                Iterator i = foundServices.iterator();
                ServiceType st;
                while (i.hasNext()) {
                    st = (ServiceType) i.next();
                    out.println("<div class=\"col-md-3 yellowbox\">");
                    out.println("<img src=\"images/services/" + st.getIdServiceType() + "-2.jpg\" alt=" + st.getName() + " class=\"img-circle center-block\" />");
                    out.println("<h3>" + st.getName() + "</h3>");

                    // If current user is client display a button to order services
                    if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("client"))) {
                        out.println("<button id=" + st.getIdServiceType() + " class=\"btn btn-warning center-block\">Order Services</button>");
                    }

                    // If current user is admin display buttons to order, update or delete services
                    if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                        out.println("<button id=" + st.getIdServiceType() + " class=\"btn btn-warning\">Order</button>"
                                + "<button id=" + st.getIdServiceType() + "-update\" class=\"btn btn-default\"><span class=\"fa fa-edit\">Edit</span></button>"
                                + "<button id=" + st.getIdServiceType() + "-delete\" class=\"btn btn-danger\"><span class=\"fa fa-minus-square\">Delete</span></button>");
                    }

                    out.println("</div>");
                }
                
                // If current user is admin display a button to add new service
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<div id=\"newService\" class=\"col-md-3 yellowbox\">");
                    out.println("<button id=\"addService\" class=\"btn btn-large btn-success\"><span class=\"fa fa-plus-square\">Add New Service</span></button>");
                    out.println("</div>");
                }
                
                out.println("<a class=\"col-md-12 text-center\" href=\"services.jsp\">"
                        + "<span class=\"fa fa-arrow-circle-left bigicon\">Return to domains</span></a>");
            } else {
                out.println("<h3>There are no services at this time. </h3>");
                // If current user is admin display a button to add new service
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<div id=\"newService\" class=\"col-md-3 yellowbox\">");
                    out.println("<button id=\"addService\" class=\"btn btn-large btn-success\"><span class=\"fa fa-plus-square\">Add New Service</span></button>");
                    out.println("</div>");
                }
                out.println("<a class=\"col-md-12 text-center\" href=\"services.jsp\">"
                        + "<span class=\"fa fa-arrow-circle-left bigicon\">Return to domains</span></a>");
            }
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

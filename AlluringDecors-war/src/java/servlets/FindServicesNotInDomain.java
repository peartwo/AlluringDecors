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
import models.ServiceType;
import session_beans.ServiceTypeFacade;

/**
 *
 * @author zuzanahruskova
 */
public class FindServicesNotInDomain extends HttpServlet {

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
        
        int domainID = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html;charset=UTF-8");

        List<ServiceType> noServices = serviceTypeObj.findServicesOutOfDomain(domainID);

        try (PrintWriter out = response.getWriter()) {
            out.println("<select id='did' class='form-control' name='serviceName'>");
            for (ServiceType st : noServices) {
                out.println("<option value='" + st.getName() + "'>" + st.getName() + "</option>");
            }
            out.println("</select><br>");
            out.println("<button id=" + domainID + " class=\"btn btn-success pull-right addexistingservice\">Add Service Type</button>");
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

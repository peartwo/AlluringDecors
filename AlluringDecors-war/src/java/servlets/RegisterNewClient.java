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
import models.Client;
import models.User;
import session_beans.ClientFacade;

/**
 *
 * @author zuzanahruskova
 */
public class RegisterNewClient extends HttpServlet {

    @EJB
    private ClientFacade clientObj;

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
        User user = (User) request.getAttribute("user");

        Client client = new Client();
        client.setIdClient(clientObj.count() + 1);
        client.setIdUser(user);
        client.setAdressStreet(request.getParameter("street"));
        client.setAddressNumber(request.getParameter("number"));
        client.setAdressCity(request.getParameter("city"));
        client.setAddressZip(request.getParameter("zip"));
        client.setPhoneNumber(request.getParameter("phone"));

        clientObj.create(client);
        System.out.println("New Client created");

        if (request.getAttribute("from").equals("admin")) {
            // New Client registered by admin
            try (PrintWriter out = response.getWriter()) {
                out.println("<h5 class=\"question\">" +user.getFirstname()+ " " +user.getSurname()+ " was successfully registered as client.</h5>");
                out.println("<p><strong>Username: </strong>  " + user.getEmail() + "</p>");
            }
        } else {
            // New Client registration
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyLogin?username=" + user.getEmail() + "&password=" + user.getPassword());
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

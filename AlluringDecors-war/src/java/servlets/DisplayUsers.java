/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Client;
import models.User;
import session_beans.ClientFacade;
import session_beans.UserFacade;

/**
 *
 * @author zuzanahruskova
 */
public class DisplayUsers extends HttpServlet {
    
    @EJB
    private ClientFacade clientObj;
    @EJB
    private UserFacade userObj;

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
        
        User user;
        Client client;
        List<User> foundUsers;
        foundUsers = userObj.findAll();
        List<Integer> clientIds = new ArrayList();
        List<Integer> adminIds = new ArrayList();
        response.setContentType("text/html;charset=UTF-8");
        
        Iterator i = foundUsers.iterator();
        while (i.hasNext()) {
            user = (User) i.next();
            if(user.getUserType().equals("client")){
                clientIds.add(user.getIdUser());
            } else {
                adminIds.add(user.getIdUser());
            }
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<div class=\"row\">");
            out.println("<button class=\"btn btn-warning goBack\"><span class=\"fa fa-arrow-circle-left bigicon\">Back To New Admin/Client Registration</span></button>");
            out.println("</div>");
            
            out.println("<h3 class=\"text-warning\">Existing Admins</h3>");
            out.println("<div class=\"row\">");
            for (Integer adminId : adminIds) {
                user = userObj.find(adminId);
                
                out.println("<div class=\"col-md-4 record\" id=\"user" + adminId + "\">");
                out.println("<strong id=\"email" + adminId + "\">" + user.getEmail() + "</strong> <span id=\"role" + adminId + "\">admin</span>");
                out.println("<p><span id=\"fname" + adminId + "\">" + user.getFirstname() + "</span> <span id=\"lname" + adminId + "\">" + user.getSurname() + "</span></p>");
                out.println("<button id=" + adminId + " class=\"btn btn-warning edit\">Edit User</button>");
                out.println("<button id=" + adminId + " class=\"btn btn-danger delete\">Delete User</button>");
                out.println("</div>");
            }
            out.println("</div>");
            
            out.println("<h3 class=\"text-warning\">Existing Clients</h3>");
            out.println("<div class=\"row\">");
            for (Integer clientId : clientIds) {
                user = userObj.find(clientId);
                client = clientObj.findByUserId(user);
                
                out.println("<div class=\"col-md-4 record\" id=\"user" + clientId + "\">");
                out.println("<strong id=\"email" + clientId + "\">" + user.getEmail() + "</strong> <span id=\"role" + clientId + "\">client</span>");
                out.println("<p><span id=\"fname" + clientId + "\">" + user.getFirstname() + "</span> <span id=\"lname" + clientId + "\">" + user.getSurname() + "</span></p>");
                out.println("<p><strong id=\"label1-" + clientId + "\">Address: </strong><span id=\"address" + clientId + "\">" + client.getAdressStreet() + "</span> <span id=\"nr" + clientId + "\">" + client.getAddressNumber() + "</span></p>");
                out.println("<p><span id=\"zip" + clientId + "\">" + client.getAddressZip() + "</span> <span id=\"city" + clientId + "\">" + client.getAdressCity() + "</span></p>");
                out.println("<p><strong id=\"label2-" + clientId + "\">Phone: </strong><span id=\"phone" + clientId + "\">" + client.getPhoneNumber() + "</span></p>");
                out.println("<button id=" + clientId + " class=\"btn btn-warning edit\">Edit User</button>");
                out.println("<button id=" + clientId + " class=\"btn btn-danger delete\">Delete User</button>");
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<div class=\"row\">");
            out.println("<button class=\"btn btn-warning goBack\"><span class=\"fa fa-arrow-circle-left bigicon\">Back To New Admin/Client Registration</span></button>");
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

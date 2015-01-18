/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import session_beans.UserFacade;

/**
 *
 * @author zuzanahruskova
 */
public class RegisterNewUser extends HttpServlet {

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
        Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        boolean newUser = true;
        String email = request.getParameter("email");
        
        List<User> currentUsers = userObj.findAll();
        for (User currentUser : currentUsers) {
            if (currentUser.getEmail().equals(email)) {
                System.out.println("User " + email + " is already registered.");
                newUser = false;
            }
        }
        if (newUser == true) {
            user = new User();
            user.setEmail(email);
            user.setPassword(request.getParameter("password"));
            user.setFirstname(request.getParameter("fname"));
            user.setSurname(request.getParameter("lname"));
            user.setIdUser(1);
            user.setUserType("client");
            user.setDateCreated(currentTimestamp);
            userObj.create(user);
            System.out.println("User Created");
        }

        user = userObj.findByEmail(email);
        System.out.println(user.getIdUser() + " / " +user.getUserType());

        if (user.getUserType().equals("client")) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterNewClient");
            request.setAttribute("user", user);
            rd.forward(request, response);
        } else {
            response.sendRedirect(".");
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

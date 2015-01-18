/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import session_beans.UserFacade;

/**
 *
 * @author zuzanahruskova
 */
public class VerifyLogin extends HttpServlet {

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
        
        Boolean loginValid = false;
        HashMap currentUser = new HashMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        List<User> allUsers = userObj.findAll();
        
        for (User user : allUsers) {
            if(user.getEmail().equals(username)) {
                if(user.getPassword().equals(password)) {
                    System.out.println("user authenticated");
                    loginValid = true;
                    currentUser.put("id", user.getIdUser());
                    currentUser.put("user_type", user.getUserType());
                    currentUser.put("email", user.getEmail());
                    currentUser.put("name", user.getFirstname());
                    System.out.println(currentUser.get("name"));
                    
                }
            }
        }
        if((loginValid==true) && (currentUser.get("user_type").equals("admin"))) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(900);
            session.setAttribute("userRole", "admin");
            session.setAttribute("name", currentUser.get("name"));
            response.sendRedirect(".");
        } else if((loginValid==true) && (currentUser.get("user_type").equals("client"))) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(900);
            session.setAttribute("userRole", "client");
            session.setAttribute("name", currentUser.get("name"));
            response.sendRedirect(".");
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

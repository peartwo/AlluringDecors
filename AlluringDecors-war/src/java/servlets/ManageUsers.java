/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ManageUsers extends HttpServlet {

    @EJB
    private UserFacade userObj;
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

        User user;
        Client client;
        String email;
        String role = request.getParameter("role");
        System.out.println(role);
        String fname;
        String lname;
        String street;
        String nr;
        String city;
        String zip;
        String phone;
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html;charset=UTF-8");

        switch (action) {
            case "update":
                user = userObj.find(id);
                email = request.getParameter("email");
                fname = request.getParameter("fname");
                lname = request.getParameter("lname");
                if (role.equals("client")) {
                    client = clientObj.findByUserId(user);
                    street = request.getParameter("street");
                    nr = request.getParameter("nr");
                    city = request.getParameter("city");
                    zip = request.getParameter("zip");
                    phone = request.getParameter("phone");
                    client.setAdressStreet(street);
                    client.setAddressNumber(nr);
                    client.setAdressCity(city);
                    client.setAddressZip(zip);
                    client.setPhoneNumber(phone);
                    clientObj.edit(client);
                }
                user.setEmail(email);
                user.setFirstname(fname);
                user.setSurname(lname);
                userObj.edit(user);
                user = userObj.find(id);
                try (PrintWriter out = response.getWriter()) {
                    client = clientObj.findByUserId(user);
                    out.println("<strong id=\"email" + id + "\">" + user.getEmail() + "</strong> <span id=\"role" + id + "\">admin</span>");
                    out.println("<p><span id=\"fname" + id + "\">" + user.getFirstname() + "</span> <span id=\"lname" + id + "\">" + user.getSurname() + "</span></p>");
                    if (role.equals("client")) {
                        out.println("<p><strong id=\"label1-" + id + "\">Address: </strong><span id=\"address" + id + "\">" + client.getAdressStreet() + "</span> <span id=\"nr" + id + "\">" + client.getAddressNumber() + "</span></p>");
                        out.println("<p><span id=\"zip" + id + "\">" + client.getAddressZip() + "</span> <span id=\"city" + id + "\">" + client.getAdressCity() + "</span></p>");
                        out.println("<p><strong id=\"label2-" + id + "\">Phone: </strong><span id=\"phone" + id + "\">" + client.getPhoneNumber() + "</span></p>");
                    }
                    out.println("<button id=" + id + " class=\"btn btn-warning edit\">Edit User</button>");
                    out.println("<button id=" + id + " class=\"btn btn-danger delete\">Delete User</button>");
                }
                break;
            case "delete":
                user = userObj.find(id);
                if (role.equals("client")) {
                    client = clientObj.findByUserId(user);
                    clientObj.remove(client);
                }
                userObj.remove(user);
                try (PrintWriter out = response.getWriter()) {
                    out.println("<h4>User deleted successfully.</h4>");
                }

                break;
            default:
                break;
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

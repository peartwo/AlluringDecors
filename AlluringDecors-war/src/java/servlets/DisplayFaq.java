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
import models.Faq;
import session_beans.FaqFacade;

/**
 *
 * @author zuzanahruskova
 */
public class DisplayFaq extends HttpServlet {

    @EJB
    private FaqFacade faqObj;

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
        List<Faq> faq = faqObj.findAll();
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");

        if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<div id=\"addNewFaq\" class=\"row controls\">");
                out.println("<button class=\"btn btn-xs btn-success showform\"><span class=\"fa fa-plus-square\">Add new Question & Answer</span></button><br /><br />");
                out.println("</div>");
                for (Faq f : faq) {
                    out.println("<div id=\"faqForm" + f.getIdFaq()+ "\" class=\"row\">");
                    out.println("<strong id=\"q" + f.getIdFaq()+ "\" class=\"question\">" + f.getQuestion() + "</strong>");
                    if (f.getAnswer() != null) {
                        out.println("<p id=\"a" + f.getIdFaq()+ "\" class=\"answer\">" + f.getAnswer() + "</p>");
                    } else {
                        out.println("<p id=\"a" + f.getIdFaq()+ "\" class=\"answer\">Answer not provided.</p>");
                    }
                    out.println("<div class=\"col-md-6 col-md-offset-6 controls\">");
                    out.println("<button id=" + f.getIdFaq()+ " class=\"btn btn-xs btn-default edit\"><span class=\"fa fa-edit\">Edit Question/Answer</span></button>");
                    out.println("<button id=" + f.getIdFaq()+ " class=\"btn btn-xs btn-danger delete\"><span class=\"fa fa-minus-square\">Delete Question/Answer</span></button>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<hr>");
                }
            }
        } else {
            try (PrintWriter out = response.getWriter()) {

                for (Faq f : faq) {
                    out.println("<strong class=\"question\">" + f.getQuestion() + "</strong>");
                    if (f.getAnswer() != null) {
                        out.println("<p class=\"answer\">" + f.getAnswer() + "</p>");
                    } else {
                        out.println("<p class=\"answer\">Answer not provided.</p>");
                    }
                    out.println("<p class=\"divider\"><span class=\"glyphicon glyphicon-asterisk\"></span></p>");
                }
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

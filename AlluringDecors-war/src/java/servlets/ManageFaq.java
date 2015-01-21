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
import models.Faq;
import session_beans.FaqFacade;

/**
 *
 * @author zuzanahruskova
 */
public class ManageFaq extends HttpServlet {

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

        Faq faq;
        String question;
        String answer;
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html;charset=UTF-8");

        switch (action) {
            case "update":
                question = request.getParameter("question");
                answer = request.getParameter("answer");
                faq = faqObj.find(id);
                faq.setQuestion(question);
                faq.setAnswer(answer);
                faqObj.edit(faq);
                faq = faqObj.find(id);
                try (PrintWriter out = response.getWriter()) {
                    out.println("<strong id=\"q" + faq.getIdFaq() + "\" class=\"question\">" + faq.getQuestion() + "</strong>");
                    if (faq.getAnswer() != null) {
                        out.println("<p id=\"a" + faq.getIdFaq() + "\" class=\"answer\">" + faq.getAnswer() + "</p>");
                    } else {
                        out.println("<p id=\"a" + faq.getIdFaq() + "\" class=\"answer\">Answer not provided.</p>");
                    }
                    out.println("<div class=\"col-md-6 col-md-offset-6 controls\">");
                    out.println("<button id=" + faq.getIdFaq() + " class=\"btn btn-xs btn-default edit\"><span class=\"fa fa-edit\">Edit Question/Answer</span></button>");
                    out.println("<button id=" + faq.getIdFaq() + " class=\"btn btn-xs btn-danger delete\"><span class=\"fa fa-minus-square\">Delete Question/Answer</span></button>");
                    out.println("</div>");
                }
                break;
            case "delete":
                faq = faqObj.find(id);
                faqObj.remove(faq);
                try (PrintWriter out = response.getWriter()) {
                    out.println("<h4>Frequently asked question deleted successfully.</h4>");
                }
                break;
            case "addnew":
                question = request.getParameter("question");
                answer = request.getParameter("answer");
                faq = new Faq();
                faq.setIdFaq(id);
                faq.setQuestion(question);
                faq.setAnswer(answer);
                faqObj.create(faq);
                faq = faqObj.findByQuestion(question);
                try (PrintWriter out = response.getWriter()) {
                    out.println("<div id=\"faqForm" + faq.getIdFaq()+ "\" class=\"row\">");
                    out.println("<strong id=\"q" + faq.getIdFaq() + "\" class=\"question\">" + faq.getQuestion() + "</strong>");
                    if (faq.getAnswer() != null) {
                        out.println("<p id=\"a" + faq.getIdFaq() + "\" class=\"answer\">" + faq.getAnswer() + "</p>");
                    } else {
                        out.println("<p id=\"a" + faq.getIdFaq() + "\" class=\"answer\">Answer not provided.</p>");
                    }
                    out.println("<div class=\"col-md-6 col-md-offset-6 controls\">");
                    out.println("<button id=" + faq.getIdFaq() + " class=\"btn btn-xs btn-default edit\"><span class=\"fa fa-edit\">Edit Question/Answer</span></button>");
                    out.println("<button id=" + faq.getIdFaq() + " class=\"btn btn-xs btn-danger delete\"><span class=\"fa fa-minus-square\">Delete Question/Answer</span></button>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<hr>");
                }
                break;
            default:
                // some error output here
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

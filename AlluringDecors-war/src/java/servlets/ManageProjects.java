/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Project;
import models.Service;
import session_beans.ProjectFacade;
import session_beans.ServiceFacade;

/**
 *
 * @author zuzanahruskova
 */
public class ManageProjects extends HttpServlet {

    @EJB
    private ServiceFacade serviceObj;
    @EJB
    private ProjectFacade projectObj;

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

        HttpSession session = request.getSession();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // if a new project added by admin, create new project
        if (request.getParameter("projectName") != null) {
            Project newProject = new Project();
            newProject.setIdProject(1);
            newProject.setIdService(serviceObj.find(Integer.parseInt(request.getParameter("addedService"))));
            newProject.setName(request.getParameter("projectName"));
            newProject.setDescription(request.getParameter("projectDescription"));
            out.println();
            try {
                newProject.setDateStart(dateFormat.parse(request.getParameter("projectStart").replace(',', '.')));
            } catch (ParseException ex) {
                newProject.setDateStart(null);
            }
            try {
                newProject.setDateEnd(dateFormat.parse(request.getParameter("projectEnd").replace(',', '.')));
            } catch (ParseException ex) {
                newProject.setDateEnd(null);
            }
            out.println();
            projectObj.create(newProject);
            out.println();
        }

        //out.println("<form action='projects.jsp'>");
        List<Project> allProjects = projectObj.findAll();

        out.println("<div class='col-md-4 redbox'><h2><span class='glyphicon glyphicon-hand-up'></span>&nbsp; Upcoming Projects</h2>");
        for (Project p : allProjects) {
            if (p.getDateStart() == null) {
                out.println("<div class='col-md-12 responseText'>");
                out.println("<img src=\"images/projects/" + p.getIdProject() + ".jpg\" alt=" + p.getName() + " style='width:100%;border:1px solid red; margin-top: 10px; margin-bottom: 10px'/>");
                out.println("<p><strong class='text-danger'>Name: </strong>" + p.getName() + "</p>");
                out.println("<p><strong class='text-danger'>Description: </strong>" + p.getDescription() + "</p>");
                // If current user is admin allow editing
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<button class='btn btn-warning' type='submit' name='selectedProject' value="
                            + p.getIdProject() + ">Edit </button><br>");
                }
                out.println("</div>");
            }
        }
        out.println("</div>");
        out.println("<div class='col-md-4 redbox'><h2><span class='glyphicon glyphicon-hand-right'></span>&nbsp; Ongoing Projects</h2>");
        for (Project p : allProjects) {
            if ((p.getDateStart() != null) && (p.getDateEnd() == null)) {
                out.println("<div class='col-md-12 responseText'>");
                out.println("<img src=\"images/projects/" + p.getIdProject() + ".jpg\" alt=" + p.getName() + " style='width:100%;border:1px solid red; margin-top: 10px; margin-bottom: 10px'/>");
                out.println("<p><strong class='text-danger'>Name: </strong>" + p.getName() + "</p>");
                out.println("<p><strong class='text-danger'>Started: </strong>" + p.getDateStart() + "</p>");
                out.println("<p><strong class='text-danger'>Description: </strong>" + p.getDescription() + "</p>");
                // If current user is admin allow editing
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<button class='btn btn-warning' type='submit' name='selectedProject' value="
                            + p.getIdProject() + ">Edit </button><br>");
                }
                out.println("</div>");
            }
        }
        out.println("</div>");
        out.println("<div class='col-md-4 redbox'><h2><span class='glyphicon glyphicon-thumbs-up'></span>&nbsp; Completed Projects</h2>");
        for (Project p : allProjects) {
            if ((p.getDateStart() != null) && (p.getDateEnd() != null)) {
                out.println("<div class='col-md-12 responseText'>");
                out.println("<img src=\"images/projects/" + p.getIdProject() + ".jpg\" alt=" + p.getName() + " style='width:100%;border:1px solid red; margin-top: 10px; margin-bottom: 10px'/>");
                out.println("<p><strong class='text-danger'>Name: </strong>" + p.getName() + "</p>");
                out.println("<p><strong class='text-danger'>Started: </strong>" + p.getDateStart() + "</p>");
                out.println("<p><strong class='text-danger'>Finished: </strong>" + p.getDateEnd() + "</p>");
                out.println("<p><strong class='text-danger'>Description: </strong>" + p.getDescription() + "</p>");
                // If current user is admin allow editing
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<button class='btn btn-warning' type='submit' name='selectedProject' value="
                            + p.getIdProject() + ">Edit </button><br>");
                }
                out.println("</div>");
            }
        }
        out.println("</div>");
        //out.println("</form><br><br>");

        // If current user is admin display form to add new project
        if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
            out.println("<div class='row'>");
            out.println("<div id='projectform' class='col-md-4 col-md-offset-4'>");
            out.println("<legend class='text-center header'>New Project</legend>");
            out.println("<form class='form-horizontal' action='projects.jsp' method='POST'>");
            out.println("<input type='text' name='projectName' placeholder='Project Name' class='form-control'><br>");
            //choose from available services (having status "Accepted" or "Paiment received")
            out.println("<span class='text-warning'>Choose service to project: </span><select name='addedService' class='form-control'>");
            for (int i = 3; i < 5; i++) {
                List<Service> services = serviceObj.findServicesByStatusID(i);
                for (Service s : services) {
                    out.println("<option value='" + s.getIdService() + "'>"
                            + s.getAddress() + " / " + s.getIdServiceType().getName() + "</option>");
                }
            }
            out.println("</select>");
            out.println("<textarea name='projectDescription' placeholder='Project Decsription' class='form-control'></textarea><br>");
            out.println("<input type='date' name='projectStart' placeholder='Start Date' class='form-control'>");
            out.println("<input type='date' name='projectEnd' placeholder='End Date' class='form-control'>");
            out.println("<button type='submit' class='btn btn-warning'>Add Project</button></form>");
            out.println("</div>");
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

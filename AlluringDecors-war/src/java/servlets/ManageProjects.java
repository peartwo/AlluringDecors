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
import java.util.Enumeration;
import java.util.HashMap;
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
        HashMap<String, String> req = new HashMap<>();
        Enumeration er = request.getParameterNames();
        while (er.hasMoreElements()) {
            String key = (String) er.nextElement();
            req.put(key, (String) request.getParameter(key));
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /* Handle request parameters create/edit/delete project
         * and prepare data for form filling 
         */
        Project project;
        if (!req.containsKey("job")) {
            req.put("job", "display");
        }
        Boolean readyToSave;
        ProjectFormData pfd;
        switch (req.get("job")) {
            case "new":
                project = new Project();
                pfd = new ProjectFormData(req);
                readyToSave = pfd.setProjectValues(project);
                if (readyToSave) {
                    projectObj.create(project);
                }
                pfd = new ProjectFormData();
                break;
            case "edit":
                project = projectObj.find(Integer.parseInt(req.get("selectedProject")));
                pfd = new ProjectFormData(req);
                readyToSave = pfd.setProjectValues(project);
                if (readyToSave) {
                    projectObj.edit(project);
                }
                break;
            case "delete":
                project = projectObj.find(Integer.parseInt(req.get("selectedProject")));
                projectObj.remove(project);
                req.remove("selectedProject");
                pfd = new ProjectFormData();
                break;
            case "select":
                project = projectObj.find(Integer.parseInt(req.get("selectedProject")));
                pfd = new ProjectFormData(project);
                break;
            default:
                pfd = new ProjectFormData();
                req.remove("selectedProject");
                break;
        }

        out.println("<form action='projects.jsp' method='POST'>");
        out.println("<input type='hidden' name='job' value='select'>");
        List<Project> allProjects = projectObj.findAll();

        out.println("<div class='col-md-4 redbox'><h2><span class='glyphicon glyphicon-hand-up'></span>&nbsp; Upcoming Projects</h2>");
        for (Project p : allProjects) {
            if (p.getDateStart() == null) {
                out.println("<div class='col-md-12 responseText'>");
                out.println("<img src=\"images/projects/" + p.getIdProject() + ".jpg\" alt=" + p.getName() + " style='width:100%;border:1px solid red; margin-top: 10px; margin-bottom: 10px'/>");
                out.println("<p><strong class='text-danger'>Name: </strong>" + p.getName() + "</p>");
                out.println("<p><strong class='text-danger'>Description: </strong>" + p.getDescription() + "</p>");
                // If current user is admin display button
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<button type='submit' class='btn btn-danger' name='selectedProject' value="
                            + p.getIdProject() + ">Select this project</button><br>");
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
                // If current user is admin display button
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<button type='submit' class='btn btn-danger' name='selectedProject' value="
                            + p.getIdProject() + ">Select this project</button><br>");
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
                // If current user is admin display button
                if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
                    out.println("<button type='submit' class='btn btn-danger' name='selectedProject' value="
                            + p.getIdProject() + ">Select this project</button><br>");
                }
                out.println("</div>");
            }
        }
        out.println("</div>");
        out.println("</form>");

        // If current user is admin display form to add new project
        if ((session.getAttribute("userRole") != null) && (session.getAttribute("userRole").equals("admin"))) {
            out.println("<form action='projects.jsp' method='POST'>");
            out.println("<div class='row'>");
            out.println("<div id='projectform' class='col-md-4 col-md-offset-4'>");
            out.println("<legend class='text-center header'>Manage Project</legend>");
            out.println("<form class='form-horizontal' action='projects.jsp' method='POST'>");
            out.println("<input type='text' name='projectName' placeholder='Project Name' value='" + pfd.pName + "' class='form-control'><br>");
            //choose from available services (having status "Accepted" or "Paiment received")
            out.println("<span class='text-warning'>Choose service to project: </span><select name='addedService' class='form-control'>");
            for (int i = 3; i < 5; i++) {
                List<Service> services = serviceObj.findServicesByStatusID(i);
                for (Service s : services) {
                    out.println("<option value='" + s.getIdService()
                            + ((s.getIdService() == pfd.pServiceID) ? "' selected>" : "'>")
                            + s.getAddress() + " / " + s.getIdServiceType().getName() + "</option>");
                }
            }
            out.println("</select>");
            out.println("<textarea name='projectDescription' placeholder='Project Decsription' class='form-control'>" + pfd.pDescription + "</textarea><br>");
            out.println("<input type='text' name='projectStart' placeholder='Start Date' value='" + pfd.pStart + "' class='form-control'>");
            out.println("<input type='text' name='projectEnd' placeholder='End Date' value='" + pfd.pEnd + "' class='form-control'><br>");
            if (req.containsKey("selectedProject")) {
                out.println("<input type='hidden' name='selectedProject' value='" + req.get("selectedProject") + "'>");
                out.println("<button class='btn btn-warning' name='job' value='edit' type='submit'>Save Changes</button>");
                out.println("<button class='btn btn-danger' name='job' value='delete' type='submit'>Delete Project</button>");
            } else {
                out.println("<button class='btn btn-success' name='job' value='new' type='submit'>Add new Project</button>");
            }
            out.println("<button class='btn btn-custom' name='job' value='display' type='submit'>Cancel</button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</form>");
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

    class ProjectFormData {

        String pName;
        String pNameErr = "";
        int pServiceID;
        String pServiceIDErr = "";
        String pDescription;
        String pStart;
        String pEnd;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        public ProjectFormData() {
            this.pName = "";
            this.pDescription = "";
            this.pStart = "";
            this.pEnd = "";
        }

        public ProjectFormData(Project project) {
            this.pName = project.getName();
            this.pServiceID = project.getIdService().getIdService();
            this.pDescription = project.getDescription();
            this.pStart = (project.getDateStart() == null) ? "" : dateFormat.format(project.getDateStart());
            this.pEnd = (project.getDateEnd() == null) ? "" : dateFormat.format(project.getDateEnd());
        }

        public ProjectFormData(HashMap<String, String> req) {
            //this.pName = req.getOrDefault("projectName", "");
            this.pName = req.containsKey("projectName") ? req.get("projectName") : "";
            if (pName.length() < 1) {
                pNameErr = "<font color='red'>Project name is Mandatory!></font><br>";
            }
            //this.pServiceID = Integer.parseInt(req.getOrDefault("addedService", "0"));
            this.pServiceID = Integer.parseInt(req.containsKey("addedService") ? req.get("addedService") : "0");
            if (pServiceID == 0) {
                pServiceIDErr = "<font color='red'>Project name is Mandatory!></font><br>";
            }
            //this.pDescription = req.getOrDefault("projectDescription", "");
            this.pDescription = req.containsKey("projectDescription") ? req.get("projectDescription") : "";
            //this.pStart = req.getOrDefault("projectStart", "");
            this.pStart = req.containsKey("projectStart") ? req.get("projectStart") : "";
            //this.pEnd = req.getOrDefault("projectEnd", "");
            this.pEnd = req.containsKey("projectEnd") ? req.get("projectEnd") : "";
        }

        public Boolean setProjectValues(Project project) {
            if (pName.length() < 1) {
                return false;
            }
            if (pServiceID == 0) {
                return false;
            }
            project.setName(pName);
            project.setIdService(serviceObj.find(pServiceID));
            project.setDescription(pDescription);
            try {
                project.setDateStart(dateFormat.parse(pStart));
            } catch (ParseException ex) {
                project.setDateStart(null);
            }
            try {
                project.setDateEnd(dateFormat.parse(pEnd));
            } catch (ParseException ex) {
                project.setDateEnd(null);
            }
            return true;
        }
    }
}

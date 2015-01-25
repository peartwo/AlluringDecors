/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.ServiceType;
import session_beans.ServiceTypeFacade;

/**
 *
 * @author zuzanahruskova
 */
@WebServlet(name = "ManageServiceTypes", urlPatterns = {"/ManageServiceTypes"})
@MultipartConfig
public class ManageServiceTypes extends HttpServlet {
    
    @EJB
    private ServiceTypeFacade serviceTypeObj;
    
    private final static Logger LOGGER
            = Logger.getLogger(AddNewDomain.class.getCanonicalName());

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
        
        ServiceType st = new ServiceType();
        String name;
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        final String path = "/Users/zuzanahruskova/NetBeansProjects/AlluringDecors/AlluringDecors-war/web/images/services";
        final Part filePart;
        boolean imgDeleted = false;
        boolean imgUpdated = false;
        
        response.setContentType("text/html;charset=UTF-8");
        
        switch (action) {
            case "update":
                name = request.getParameter("name");
                st = serviceTypeObj.find(id);
                st.setName(name);
                serviceTypeObj.edit(st);
                st = serviceTypeObj.find(id);

                filePart = request.getPart("file");
                // If new image was selected
                System.out.println(filePart.getHeader("content-disposition"));
                if (!"form-data; name=\"file\"; filename=\"\"".equals(filePart.getHeader("content-disposition"))) {
                    String fileName = id + "-2.jpg";
                    OutputStream stream = null;
                    InputStream filecontent = null;
                    try {
                        stream = new FileOutputStream(new File(path + File.separator
                                + fileName));
                        filecontent = filePart.getInputStream();
                        int read = 0;
                        final byte[] bytes = new byte[1024];

                        while ((read = filecontent.read(bytes)) != -1) {
                            stream.write(bytes, 0, read);
                        }
                        imgUpdated = true;
                        System.out.println("New file " + fileName + " created at " + path);
                        LOGGER.log(Level.INFO, "File{0} being uploaded to {1}",
                                new Object[]{fileName, path});
                    } catch (FileNotFoundException fne) {
                        System.out.println(fne.getMessage());
                        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                                new Object[]{fne.getMessage()});
                    } finally {
                        if (stream != null) {
                            stream.close();
                        }
                        filecontent.close();
                    }
                }

                try (PrintWriter out = response.getWriter()) {
                    if (imgUpdated == false) {
                        out.println("Service Type updated successfully. No new image chosen.");
                        out.println("<button type=\"submit\" class=\"btn btn-default pull-right edit\">Edit</button>");
                        out.println("<button class=\"btn btn-danger pull-right delete\">Delete</button>");
                        out.println("<h3>" + st.getName() + "</h3>");
                        out.println("<center><img src=\"images/domains/" + id + "-2.jpg\" alt=" + st.getName() + " class=\"img-circle\" /></center>");
                    } else {
                        out.println("Service Type and image updated successfully.");
                        out.println("<button type=\"submit\" class=\"btn btn-default pull-right edit\">Edit</button>");
                        out.println("<button class=\"btn btn-danger pull-right delete\">Delete</button>");
                        out.println("<h3>" + st.getName() + "</h3>");
                        out.println("<center><img src=\"images/domains/" + id + "-2.jpg\" alt=" + st.getName() + " class=\"img-circle\" /></center>");
                    }
                }
                break;
            case "delete":
                st = serviceTypeObj.find(id);
                serviceTypeObj.remove(st);
                try {
                    File file = new File("/Users/zuzanahruskova/NetBeansProjects/AlluringDecors/AlluringDecors-war/web/images/services/" + id + "-2.jpg");
                    if (file.delete()) {
                        imgDeleted = true;
                        System.out.println(file.getName() + " was deleted.");
                    } else {
                        System.out.println("Delete operation failed");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                try (PrintWriter out = response.getWriter()) {
                    if (imgDeleted == true) {
                        out.println("<h4>Service type " + st.getName() + " and image deleted successfully.</h4>");
                    } else {
                        out.println("<h4>Service type image was not deleted.</h4>");
                    }
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

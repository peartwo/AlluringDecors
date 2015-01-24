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
import java.util.Collection;
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
import models.ServiceDomain;
import models.ServiceType;
import session_beans.ServiceDomainFacade;
import session_beans.ServiceTypeFacade;

/**
 *
 * @author zuzanahruskova
 */
@WebServlet(name = "AddNewServiceType", urlPatterns = {"/AddNewServiceType"})
@MultipartConfig
public class AddNewServiceType extends HttpServlet {

    @EJB
    private ServiceDomainFacade serviceDomainObj;
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
        
        boolean imgUploaded = false;
        //System.out.println(request.getParameter("currentDomainID"));
        int domainId = Integer.parseInt(request.getParameter("currentDomainID"));
        ServiceDomain sd = serviceDomainObj.find(domainId);
        ServiceType st = new ServiceType();
        st.setIdServiceType(1);
        st.setName(request.getParameter("name"));
        serviceTypeObj.create(st);
        int servTypeId = st.getIdServiceType();
        
        Collection<ServiceType> services = sd.getServiceTypeCollection();
        for (ServiceType service : services) {
            System.out.println(service.getName());    
        }
        services.add(st);
        sd.setServiceTypeCollection(services);
        for (ServiceType serv : sd.getServiceTypeCollection()) {
            System.out.println(serv.getName());   
        }
        serviceDomainObj.edit(sd);
        
        // Create path components to save the image
        final String path = "/Users/zuzanahruskova/NetBeansProjects/AlluringDecors/AlluringDecors-war/web/images/services";
        final Part filePart = request.getPart("file");
        final String fileName = servTypeId + "-2.jpg";

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
            imgUploaded = true;
            // out.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            /*out.println("You either did not specify a file to upload or are "
             + "trying to upload a file to a protected or nonexistent "
             + "location.");
             out.println("<br/> ERROR: " + fne.getMessage());*/
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});
        } finally {
            stream.close();
            filecontent.close();
        }
        
        try (PrintWriter out = response.getWriter()) {
            if (imgUploaded == true) {
                out.println("<div class=\"col-md-6 yellowbox\" id=" + servTypeId + " >");
                out.println("<button type=\"submit\" class=\"btn btn-default pull-right edit\">Edit</button>");
                out.println("<button class=\"btn btn-danger pull-right delete\">Delete</button>");
                out.println("<h3>" + st.getName() + "</h3>");
                out.println("<center><img src=\"images/domains/" + servTypeId + "-2.jpg\" alt=\"" + st.getName() + "\" class=\"img-circle\" /></center>");
                //System.out.println("<center><img src=\"images/domains/" + domainId + ".jpg\" alt=\"" + sd.getName() + "\" class=\"img-circle\" /></center>");
                out.println("</div>");
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

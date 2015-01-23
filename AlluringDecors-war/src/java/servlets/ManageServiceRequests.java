/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Client;
import models.Service;
import models.ServiceRequest;
import models.ServiceStatus;
import session_beans.ClientFacade;
import session_beans.ServiceFacade;
import session_beans.ServiceRequestFacade;
import session_beans.ServiceStatusFacade;

/**
 *
 * @author Milos Varga
 */
public class ManageServiceRequests extends HttpServlet {

    @EJB
    private ClientFacade clientObj;
    @EJB
    private ServiceRequestFacade serviceRequestObj;
    @EJB
    private ServiceFacade serviceObj;
    @EJB
    private ServiceStatusFacade serviceStatusObj;

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

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //out.println("<!DOCTYPE html>");
            //out.println("<html>");
            //out.println("<head>");
            //out.println("<title>Alluring Decors - Order Management</title>");            
            //out.println("</head>");
            //out.println("<body>");
            
            //check, if a client has been already choosen or changed
            //if yes, set his ID to session
            int targetClientID = 0;
            if (request.getParameter("targetClientID") != null) {
                session.setAttribute("targetClientID", request.getParameter("targetClientID"));
            }
            if (session.getAttribute("targetClientID") != null) {
                targetClientID = Integer.parseInt((String) session.getAttribute("targetClientID"));
            }
            //check, if a service is choosen for editation
            int editedServiceID = 0;
            if (request.getParameter("editedServiceID") != null) {
                editedServiceID = Integer.parseInt(request.getParameter("editedServiceID"));
            }
            //update or delete record
            if (request.getParameter("update") != null) {
                int updatedServiceID = Integer.parseInt(request.getParameter("update"));
                Service service = serviceObj.find(updatedServiceID);
                service.setAddress(request.getParameter("serviceAddress"));
                service.setContent(request.getParameter("serviceContent"));
                service.setBilledAmount(Float.valueOf(request.getParameter("billedAmount")));
                String str = request.getParameter("datePaid");
                if (!(request.getParameter("datePaid") == null)) {
                    service.setDatePaid(null);
                } else {
                    service.setDatePaid(Date.valueOf(request.getParameter("datePaid")));
                }
                service.setIdServiceStatus(serviceStatusObj.find(
                        Integer.parseInt(request.getParameter("serviceStatus"))));
                serviceObj.edit(service);
            }
            if (request.getParameter("delete") != null) {
                int deletedServiceID = Integer.parseInt(request.getParameter("delete"));
                Service service = serviceObj.find(deletedServiceID);
                serviceObj.remove(service);

            }

            //choose a client from combobox
            out.println("<form action='admin-orders.jsp'>");
            List<Client> clients = clientObj.findAll();
            out.println("Select client:");
            out.println("<select name='targetClientID'>");
            out.println("<option value=0></option>");
            for (Client c : clients) {
                // out.println("targetClientID= "+targetClientID);    
                out.println("<option value=" + c.getIdClient()
                        + ((targetClientID == c.getIdClient()) ? " selected>" : ">")
                        + c.getIdUser().getFirstname() + " "
                        + c.getIdUser().getSurname() + "</option>");
            }
            out.println("</select>");
            out.println("<button type='submit'>Submit</button>");
            out.println("</form>");
            //when a client is choosen, list services in his cart
            if (targetClientID > 0) {
                Client targetClient = clientObj.find(targetClientID);
                ServiceRequest sr = serviceRequestObj.getCartByClient(targetClient);
                Collection<Service> services = sr.getServiceCollection();
                out.println("<form action='admin-orders.jsp'><table>");
                out.println("<tr><th>Locality</th><th>Domain</th><th>Service</th><th>Status</th><th></th></tr>");
                for (Service s : services) {
                    out.println("<tr><td>" + s.getAddress() + "</td><td>"
                            + s.getIdServiceDomain().getName() + "</td><td>"
                            + s.getIdServiceType().getName() + "</td><td>"
                            + s.getIdServiceStatus().getName() + "</td><td>"
                            + "<button type='submit' name='editedServiceID' value='" + s.getIdService() + "'>View details</button>"
                            + "</td></tr>");

                    //if a service has been choosen, show form for editation
                    if (editedServiceID == s.getIdService()) {
                        //out.println("<table>");
                        out.println("<tr><td></td><td>Locality:</td>"
                                + "<td ><textarea name='serviceAddress'>"
                                + s.getAddress() + "</textarea></td></tr>");
                        out.println("<tr><td></td><td>Content:</td>"
                                + "<td><textarea name='serviceContent'>"
                                + s.getContent() + "</textarea></td><td></td>"
                                + "<td><button type='Submit' name='update' value='"
                                + s.getIdService() + "'>Change Record</button></td></tr>");
                        out.println("<tr><td></td><td>Billed Amount:</td>"
                                + "<td><input type='text' name='billedAmount' value='"
                                + s.getBilledAmount() + "'></td><td></td>"
                                + "<td><button type='submit' name='delete' value='"
                                + s.getIdService() + "'>Delete Record</button></td></tr>");
                        out.println("<tr><td></td><td>Date Paid:</td>"
                                + "<td><input type='text' name='datePaid' value='"
                                + s.getDatePaid() + "'></td></tr>");
                        out.println("<tr><td></td><td>Service Status:</td><td>"
                                + "<select name='serviceStatus'>");
                        List<ServiceStatus> statuses = serviceStatusObj.findAll();
                        for (ServiceStatus sts : statuses) {
                            out.println("<option value='" + sts.getIdServiceStatus() + "' "
                                    + ((editedServiceID == sts.getIdServiceStatus()) ? " selected>" : ">")
                                    + sts.getName() + "</option>");
                        }
                        out.println("</select></td></tr>");
                    }
                }
                out.println("</table></form>");
            }
            //out.println("</body>");
            //out.println("</html>");
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

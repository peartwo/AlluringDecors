/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import models.User;
import session_beans.ClientFacade;
import session_beans.ServiceFacade;
import session_beans.ServiceRequestFacade;
import session_beans.ServiceStatusFacade;
import session_beans.UserFacade;

/**
 *
 * @author zuzanahruskova
 */
public class ManageOrdersForClient extends HttpServlet {

    @EJB
    private ClientFacade clientObj;
    @EJB
    private UserFacade userObj;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatShort = new SimpleDateFormat("MM/yy");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("currentUserId");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*check, if a service is choosen for editation
         */
        int editedServiceID = 0;
        if (request.getParameter("editedServiceID") != null) {
            editedServiceID = Integer.parseInt(request.getParameter("editedServiceID"));
        }
        /*update record
         */
        if (request.getParameter("update") != null) {
            int updatedServiceID = Integer.parseInt(request.getParameter("update"));
            Service service = serviceObj.find(updatedServiceID);
            service.setAddress(request.getParameter("serviceAddress"));
            service.setContent(request.getParameter("serviceContent"));
            serviceObj.edit(service);
        }
        /*delete record
         */
        if (request.getParameter("delete") != null) {
            int deletedServiceID = Integer.parseInt(request.getParameter("delete"));
            Service service = serviceObj.find(deletedServiceID);
            serviceObj.remove(service);
            //service = serviceObj.find(deletedServiceID);
        }
        //ServiceRequest sr = serviceRequestObj.getCartByClient(targetClient);
        //Collection<Service> services = sr.getServiceCollection();
        User user = userObj.find(userId);
        Client client = clientObj.findByUserId(user);
        ServiceRequest sr = serviceRequestObj.getCartByClient(client);
        List<Service> services = serviceObj.findByServiceRequestId(sr);
        out.println("<br><hr><form action='client-orders.jsp'>");
        out.println("<table class='light' style='width: 100%'>");
        out.println("<tr class='text-warning'><th>Locality</th><th>Domain</th><th>Service</th><th>Status</th><th>Billed</th><th>Paid</th><th></th></tr>");
        Float totalBilled = 0f;
        Float totalPaid = 0f;
        for (Service s : services) {
            totalBilled += s.getBilledAmount();
            if (!(s.getDatePaid() == null)) {
                totalPaid += s.getBilledAmount();
            }
            out.println("<tr><td>" + s.getAddress() + "</td><td>"
                    + s.getIdServiceDomain().getName() + "</td><td>"
                    + s.getIdServiceType().getName() + "</td><td>"
                    + s.getIdServiceStatus().getName() + "</td><td>"
                    + s.getBilledAmount() + "</td><td>");
            String dateP;
            try {
                dateP = dateFormatShort.format(s.getDatePaid());
            } catch (Exception ex) {
                dateP = "";
            }
            out.println(dateP + "</td><td>"
                    + "<button class='btn btn-default' type='submit' name='editedServiceID' value='"
                    + s.getIdService() + "'>View details</button>"
                    + "</td></tr>");
            /*if a service has been choosen, show form for editation
             */
            if (editedServiceID == s.getIdService()) {
                out.println("<tr><td colspan='4'>&nbsp;</td></tr><tr><td></td><td class='text-warning'>Locality:</td>"
                        + "<td colspan='2'><textarea class='form-control' name='serviceAddress'>"
                        + s.getAddress() + "</textarea></td></tr>");
                out.println("<tr><td></td><td class='text-warning'>Content:</td>"
                        + "<td colspan='2'><textarea class='form-control' name='serviceContent'>"
                        + s.getContent() + "</textarea></td>"
                        + "<td><button class='btn btn-warning' type='Submit' name='update' value='"
                        + s.getIdService() + "'>Change Record</button></td></tr>");
                out.println("<tr><td></td><td class='text-warning'>Billed Amount:</td>"
                        + "<td colspan='2'>" + s.getBilledAmount() + "</td>");
                if (s.getDatePaid() == null) {
                    out.println("<td><button class='btn btn-danger' type='submit' name='delete' value='"
                            + s.getIdService() + "'>Delete Record</button></td></tr>");
                }
                try {
                    dateP = dateFormat.format(s.getDatePaid());
                } catch (Exception ex) {
                    dateP = "";
                }
                out.println("<tr><td></td><td class='text-warning'>Date Paid:</td>"
                        + "<td colspan='2'>" + dateP + "</td></tr>");
                out.println("<tr><td></td><td class='text-warning'>Service Status:</td><td colspan='2'>"
                        + s.getIdServiceStatus().getName() + "</td></tr><tr><td colspan='4'>&nbsp;</td><tr>");
            }
        }
        out.println("</table><hr></form>");
        out.println("<b class='text-warning'>TOTAL BILLED: " + totalBilled
                + ", TOTAL PAID: " + totalPaid
                + ", BALANCE (REST) = " + (totalBilled - totalPaid) + "</b><br>");

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

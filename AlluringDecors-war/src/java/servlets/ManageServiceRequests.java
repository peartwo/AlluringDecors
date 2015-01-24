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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatShort = new SimpleDateFormat("MM/yy");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*check, if a client has been already choosen or changed
         *if yes, set his ID to session
         */
        int targetClientID = 0;
        if (request.getParameter("targetClientID") != null) {
            session.setAttribute("targetClientID", request.getParameter("targetClientID"));
        }
        if (session.getAttribute("targetClientID") != null) {
            targetClientID = Integer.parseInt((String) session.getAttribute("targetClientID"));
        }
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
            service.setBilledAmount(Float.valueOf(request.getParameter("billedAmount").replace(',', '.')));
            try {
                service.setDatePaid(dateFormat.parse(request.getParameter("datePaid").replace(',', '.')));
            } catch (ParseException ex) {
                service.setDatePaid(null);
            }
            service.setIdServiceStatus(serviceStatusObj.find(
                    Integer.parseInt(request.getParameter("serviceStatus"))));
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

        /*Start printing GUI
         *choose a client from combobox
         */
        out.println("<form action='admin-orders.jsp'>");
        List<Client> clients = clientObj.findAll();
        out.println("<h4 class=\"text-success\">Select client:<h4>");
        out.println("<select class='form-control' name='targetClientID'>");
        out.println("<option value=0></option>");
        for (Client c : clients) {
            // out.println("targetClientID= "+targetClientID);    
            out.println("<option value=" + c.getIdClient()
                    + ((targetClientID == c.getIdClient()) ? " selected>" : ">")
                    + c.getIdUser().getFirstname() + " "
                    + c.getIdUser().getSurname() + "</option>");
        }
        out.println("</select>");
        out.println("<br><div class='col-md-4 col-md-offset-8'><button class='btn btn-success' type='submit' style='width: 30%'>Submit</button></div>");
        out.println("</form>");

        /*when a client is choosen, list services in his cart
         */
        if (targetClientID > 0) {
            Client targetClient = clientObj.find(targetClientID);
            //ServiceRequest sr = serviceRequestObj.getCartByClient(targetClient);
            //Collection<Service> services = sr.getServiceCollection();
            ServiceRequest sr = serviceRequestObj.getCartByClient(targetClient);
            List<Service> services = serviceObj.findByServiceRequestId(sr);
            out.println("<br><hr><form action='admin-orders.jsp'>");
            out.println("<table class='light admintable' style='width: 100%'>");
            out.println("<tr class='text-warning'><th>Locality</th><th>Domain</th><th>Service</th><th>Status</th><th>Billed</th><th>Paid</th><th></th></tr>");

            Float totalBilled = 0f;
            Float totalPaid = 0f;
            for (Service s : services) {
                totalBilled += s.getBilledAmount();
                if (!(s.getDatePaid() == null)) {
                    totalPaid += s.getBilledAmount();
                }
                out.println("<tr><td class='smallfont'>" + s.getAddress() + "</td><td class='smallfont'>"
                        + s.getIdServiceDomain().getName() + "</td><td class='smallfont'>"
                        + s.getIdServiceType().getName() + "</td><td class='smallfont'>"
                        + s.getIdServiceStatus().getName() + "</td><td class='smallfont'>"
                        + s.getBilledAmount() + "</td><td class='smallfont'>");
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
                    out.println("<tr><td class='smallfont' colspan='4'>&nbsp;</td></tr><tr><td></td><td class='text-warning smallfont'>Locality:</td>"
                            + "<td class='smallfont' colspan='2'><textarea class='form-control' name='serviceAddress'>"
                            + s.getAddress() + "</textarea></td></tr>");
                    out.println("<tr><td class='smallfont'></td><td class='text-warning smallfont'>Content:</td>"
                            + "<td class='smallfont' colspan='2'><textarea class='form-control' name='serviceContent'>"
                            + s.getContent() + "</textarea></td>"
                            + "<td><button class='btn btn-warning' type='Submit' name='update' value='"
                            + s.getIdService() + "'>Change Record</button></td></tr>");
                    out.println("<tr><td></td><td class='text-warning smallfont'>Billed Amount:</td>"
                            + "<td class='smallfont' colspan='2'><input class='form-control' type='text' name='billedAmount' value='"
                            + s.getBilledAmount() + "'></td>"
                            + "<td><button class='btn btn-danger' type='submit' name='delete' value='"
                            + s.getIdService() + "'>Delete Record</button></td></tr>");
                    try {
                        dateP = dateFormat.format(s.getDatePaid());
                    } catch (Exception ex) {
                        dateP = "";
                    }
                    out.println("<tr><td></td><td class='text-warning smallfont'>Date Paid:</td>"
                            + "<td colspan='2'><input class='form-control' type='text' name='datePaid' value='"
                            + dateP + "'></td></tr>");
                    out.println("<tr><td></td><td class='text-warning smallfont'>Service Status:</td><td class='smallfont' colspan='2'>"
                            + "<select class='form-control' name='serviceStatus'>");
                    List<ServiceStatus> statuses = serviceStatusObj.findAll();
                    for (ServiceStatus sts : statuses) {
                        out.println("<option value='" + sts.getIdServiceStatus() + "' "
                                + ((editedServiceID == sts.getIdServiceStatus()) ? " selected>" : ">")
                                + sts.getName() + "</option>");
                    }
                    out.println("</select></td></tr><tr><td colspan='4'>&nbsp;</td><tr>");
                }
            }
            out.println("</table><hr></form>");
            out.println("<b class='text-warning'>TOTAL BILLED: " + totalBilled
                    + ", TOTAL PAID: " + totalPaid
                    + ", BALANCE (REST) = " + (totalBilled - totalPaid) + "</b><br>");
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

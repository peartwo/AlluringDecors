/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zuzanahruskova
 */
@Entity
@Table(name = "service_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceRequest.findAll", query = "SELECT s FROM ServiceRequest s"),
    @NamedQuery(name = "ServiceRequest.findByIdServiceRequest", query = "SELECT s FROM ServiceRequest s WHERE s.idServiceRequest = :idServiceRequest"),
    @NamedQuery(name = "ServiceRequest.findByDateCreated", query = "SELECT s FROM ServiceRequest s WHERE s.dateCreated = :dateCreated"),
    @NamedQuery(name = "ServiceRequest.findByInvoiceNumber", query = "SELECT s FROM ServiceRequest s WHERE s.invoiceNumber = :invoiceNumber")})
public class ServiceRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_service_request")
    private Integer idServiceRequest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Size(max = 20)
    @Column(name = "invoice_number")
    private String invoiceNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceRequest")
    private Collection<Service> serviceCollection;
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    @ManyToOne(optional = false)
    private Client idClient;

    public ServiceRequest() {
    }

    public ServiceRequest(Integer idServiceRequest) {
        this.idServiceRequest = idServiceRequest;
    }

    public ServiceRequest(Integer idServiceRequest, Date dateCreated) {
        this.idServiceRequest = idServiceRequest;
        this.dateCreated = dateCreated;
    }

    public Integer getIdServiceRequest() {
        return idServiceRequest;
    }

    public void setIdServiceRequest(Integer idServiceRequest) {
        this.idServiceRequest = idServiceRequest;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiceRequest != null ? idServiceRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceRequest)) {
            return false;
        }
        ServiceRequest other = (ServiceRequest) object;
        if ((this.idServiceRequest == null && other.idServiceRequest != null) || (this.idServiceRequest != null && !this.idServiceRequest.equals(other.idServiceRequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ServiceRequest[ idServiceRequest=" + idServiceRequest + " ]";
    }
    
}

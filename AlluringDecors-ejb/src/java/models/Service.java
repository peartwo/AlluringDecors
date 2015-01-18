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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zuzanahruskova
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByIdService", query = "SELECT s FROM Service s WHERE s.idService = :idService"),
    @NamedQuery(name = "Service.findByBilledAmount", query = "SELECT s FROM Service s WHERE s.billedAmount = :billedAmount"),
    @NamedQuery(name = "Service.findByDatePaid", query = "SELECT s FROM Service s WHERE s.datePaid = :datePaid")})
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_service")
    private Integer idService;
    @Lob
    @Size(max = 65535)
    private String address;
    @Lob
    @Size(max = 65535)
    private String content;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "billed_amount")
    private Float billedAmount;
    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idService")
    private Collection<Project> projectCollection;
    @JoinColumn(name = "id_service_type", referencedColumnName = "id_service_type")
    @ManyToOne(optional = false)
    private ServiceType idServiceType;
    @JoinColumn(name = "id_service_status", referencedColumnName = "id_service_status")
    @ManyToOne(optional = false)
    private ServiceStatus idServiceStatus;
    @JoinColumn(name = "id_service_request", referencedColumnName = "id_service_request")
    @ManyToOne(optional = false)
    private ServiceRequest idServiceRequest;
    @JoinColumn(name = "id_service_domain", referencedColumnName = "id_service_domain")
    @ManyToOne(optional = false)
    private ServiceDomain idServiceDomain;

    public Service() {
    }

    public Service(Integer idService) {
        this.idService = idService;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(Float billedAmount) {
        this.billedAmount = billedAmount;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public ServiceType getIdServiceType() {
        return idServiceType;
    }

    public void setIdServiceType(ServiceType idServiceType) {
        this.idServiceType = idServiceType;
    }

    public ServiceStatus getIdServiceStatus() {
        return idServiceStatus;
    }

    public void setIdServiceStatus(ServiceStatus idServiceStatus) {
        this.idServiceStatus = idServiceStatus;
    }

    public ServiceRequest getIdServiceRequest() {
        return idServiceRequest;
    }

    public void setIdServiceRequest(ServiceRequest idServiceRequest) {
        this.idServiceRequest = idServiceRequest;
    }

    public ServiceDomain getIdServiceDomain() {
        return idServiceDomain;
    }

    public void setIdServiceDomain(ServiceDomain idServiceDomain) {
        this.idServiceDomain = idServiceDomain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idService != null ? idService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.idService == null && other.idService != null) || (this.idService != null && !this.idService.equals(other.idService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Service[ idService=" + idService + " ]";
    }
    
}

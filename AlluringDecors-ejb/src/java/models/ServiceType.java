/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zuzanahruskova
 */
@Entity
@Table(name = "service_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s"),
    @NamedQuery(name = "ServiceType.findByIdServiceType", query = "SELECT s FROM ServiceType s WHERE s.idServiceType = :idServiceType"),
    @NamedQuery(name = "ServiceType.findByName", query = "SELECT s FROM ServiceType s WHERE s.name = :name")})
public class ServiceType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_service_type")
    private Integer idServiceType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    @ManyToMany(mappedBy = "serviceTypeCollection")
    private Collection<ServiceDomain> serviceDomainCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceType")
    private Collection<Service> serviceCollection;

    public ServiceType() {
    }

    public ServiceType(Integer idServiceType) {
        this.idServiceType = idServiceType;
    }

    public ServiceType(Integer idServiceType, String name) {
        this.idServiceType = idServiceType;
        this.name = name;
    }

    public Integer getIdServiceType() {
        return idServiceType;
    }

    public void setIdServiceType(Integer idServiceType) {
        this.idServiceType = idServiceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<ServiceDomain> getServiceDomainCollection() {
        return serviceDomainCollection;
    }

    public void setServiceDomainCollection(Collection<ServiceDomain> serviceDomainCollection) {
        this.serviceDomainCollection = serviceDomainCollection;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServiceType != null ? idServiceType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.idServiceType == null && other.idServiceType != null) || (this.idServiceType != null && !this.idServiceType.equals(other.idServiceType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ServiceType[ idServiceType=" + idServiceType + " ]";
    }
    
}

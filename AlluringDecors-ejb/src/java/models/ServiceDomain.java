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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "service_domain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceDomain.findAll", query = "SELECT s FROM ServiceDomain s"),
    @NamedQuery(name = "ServiceDomain.findByIdServiceDomain", query = "SELECT s FROM ServiceDomain s WHERE s.idServiceDomain = :idServiceDomain"),
    @NamedQuery(name = "ServiceDomain.findByName", query = "SELECT s FROM ServiceDomain s WHERE s.name = :name")})
public class ServiceDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_service_domain")
    private Integer idServiceDomain;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @JoinTable(name = "r_service_domain_service_type", joinColumns = {
        @JoinColumn(name = "id_service_domain", referencedColumnName = "id_service_domain")}, inverseJoinColumns = {
        @JoinColumn(name = "id_service_type", referencedColumnName = "id_service_type")})
    @ManyToMany
    private Collection<ServiceType> serviceTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceDomain")
    private Collection<Service> serviceCollection;

    public ServiceDomain() {
    }

    public ServiceDomain(Integer idServiceDomain) {
        this.idServiceDomain = idServiceDomain;
    }

    public ServiceDomain(Integer idServiceDomain, String name) {
        this.idServiceDomain = idServiceDomain;
        this.name = name;
    }

    public Integer getIdServiceDomain() {
        return idServiceDomain;
    }

    public void setIdServiceDomain(Integer idServiceDomain) {
        this.idServiceDomain = idServiceDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<ServiceType> getServiceTypeCollection() {
        return serviceTypeCollection;
    }

    public void setServiceTypeCollection(Collection<ServiceType> serviceTypeCollection) {
        this.serviceTypeCollection = serviceTypeCollection;
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
        hash += (idServiceDomain != null ? idServiceDomain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceDomain)) {
            return false;
        }
        ServiceDomain other = (ServiceDomain) object;
        if ((this.idServiceDomain == null && other.idServiceDomain != null) || (this.idServiceDomain != null && !this.idServiceDomain.equals(other.idServiceDomain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ServiceDomain[ idServiceDomain=" + idServiceDomain + " ]";
    }
    
}

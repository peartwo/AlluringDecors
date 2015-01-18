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
@Table(name = "service_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceStatus.findAll", query = "SELECT s FROM ServiceStatus s"),
    @NamedQuery(name = "ServiceStatus.findByIdServiceStatus", query = "SELECT s FROM ServiceStatus s WHERE s.idServiceStatus = :idServiceStatus"),
    @NamedQuery(name = "ServiceStatus.findByName", query = "SELECT s FROM ServiceStatus s WHERE s.name = :name")})
public class ServiceStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_service_status")
    private Integer idServiceStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServiceStatus")
    private Collection<Service> serviceCollection;

    public ServiceStatus() {
    }

    public ServiceStatus(Integer idServiceStatus) {
        this.idServiceStatus = idServiceStatus;
    }

    public ServiceStatus(Integer idServiceStatus, String name) {
        this.idServiceStatus = idServiceStatus;
        this.name = name;
    }

    public Integer getIdServiceStatus() {
        return idServiceStatus;
    }

    public void setIdServiceStatus(Integer idServiceStatus) {
        this.idServiceStatus = idServiceStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idServiceStatus != null ? idServiceStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceStatus)) {
            return false;
        }
        ServiceStatus other = (ServiceStatus) object;
        if ((this.idServiceStatus == null && other.idServiceStatus != null) || (this.idServiceStatus != null && !this.idServiceStatus.equals(other.idServiceStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ServiceStatus[ idServiceStatus=" + idServiceStatus + " ]";
    }
    
}

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByIdClient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient"),
    // added query to find client by user id
    @NamedQuery(name = "Client.findByIdUser", query = "SELECT c FROM Client c WHERE c.idUser = :idUser"),
    @NamedQuery(name = "Client.findByPhoneNumber", query = "SELECT c FROM Client c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Client.findByAdressStreet", query = "SELECT c FROM Client c WHERE c.adressStreet = :adressStreet"),
    @NamedQuery(name = "Client.findByAddressNumber", query = "SELECT c FROM Client c WHERE c.addressNumber = :addressNumber"),
    @NamedQuery(name = "Client.findByAdressCity", query = "SELECT c FROM Client c WHERE c.adressCity = :adressCity"),
    @NamedQuery(name = "Client.findByAddressZip", query = "SELECT c FROM Client c WHERE c.addressZip = :addressZip")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_client")
    private Integer idClient;
    @Size(max = 50)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 100)
    @Column(name = "adress_street")
    private String adressStreet;
    @Size(max = 10)
    @Column(name = "address_number")
    private String addressNumber;
    @Size(max = 50)
    @Column(name = "adress_city")
    private String adressCity;
    @Size(max = 5)
    @Column(name = "address_zip")
    private String addressZip;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private Collection<ServiceRequest> serviceRequestCollection;

    public Client() {
    }

    public Client(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdressStreet() {
        return adressStreet;
    }

    public void setAdressStreet(String adressStreet) {
        this.adressStreet = adressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAdressCity() {
        return adressCity;
    }

    public void setAdressCity(String adressCity) {
        this.adressCity = adressCity;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<ServiceRequest> getServiceRequestCollection() {
        return serviceRequestCollection;
    }

    public void setServiceRequestCollection(Collection<ServiceRequest> serviceRequestCollection) {
        this.serviceRequestCollection = serviceRequestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Client[ idClient=" + idClient + " ]";
    }
    
}

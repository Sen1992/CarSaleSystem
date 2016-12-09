/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author 王森
 */
@Entity
@Table(name = "sale")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSale;
    
    @ManyToOne
    @JoinColumn(name = "customer")
    private User customer;
    
    @Column(name = "date")
    private String date;
    
    @ManyToOne()
    @JoinColumn(name = "car")
    private Car car;
    
    @ManyToOne()
    @JoinColumn(name = "salesperson")
    private User salesPerson;

    public Long getId() {
        return idSale;
    }

    public void setId(Long id) {
        this.idSale = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSale != null ? idSale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.idSale == null && other.idSale != null) || (this.idSale != null && !this.idSale.equals(other.idSale))) {
            return false;
        }
        return true;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }


    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(User salesPerson) {
        this.salesPerson = salesPerson;
    }
    
    

    @Override
    public String toString() {
        return "fit5192.entities.Sale[ id=" + idSale + " ]";
    }
    
}

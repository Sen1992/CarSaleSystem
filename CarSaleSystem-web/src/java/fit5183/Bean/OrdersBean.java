/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.Sale;
import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 王森
 */
@Named(value = "ordersBean")
@RequestScoped
public class OrdersBean {
    private Long id;
    private List<Sale> saleList;
    
    private String name;

    public String getName() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User)session.getAttribute("UserLogin");
        return user.getFirstName()+" "+user.getLastName();
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String logout(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index?faces-redirect=true";
    }
    
    @EJB
    private SystemInterface systemInterface;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sale> getSaleList() throws Exception {
        saleList = systemInterface.getSalesByCustomId(id);
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
    /**
     * Creates a new instance of OrdersBean
     */
    public OrdersBean() {
        
        id  = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId"));
    }
    
}

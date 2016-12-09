/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.User;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 王森
 */
@Named(value = "viewCarBean")
@RequestScoped
public class ViewCarBean {

    /**
     * Creates a new instance of ViewCarBean
     */
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
    
    
    
    
    
    public ViewCarBean() {
    }
    
}

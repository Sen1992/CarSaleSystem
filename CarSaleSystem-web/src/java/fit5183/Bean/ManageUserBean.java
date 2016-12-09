/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 王森
 */
@Named(value = "manageUserBean")
@SessionScoped
public class ManageUserBean  implements Serializable{
    private String userId;
    private String lastName;
    private String firstName;
    private String email;
    private List<User> userList;
    
    
    
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
    
    /**
     * Creates a new instance of ManageUserBean
     */
    public ManageUserBean() {
    }
    public  List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    /**
     * get the input form user and search customer from database
     * display the results in datatable,If No vehicle is got,feedback is shown
     * @return 
     */
    public String getUser(){
        String sql = "SELECT u FROM User u WHERE ";
        if (!userId.equals("")) {
            sql = sql+"u.userId = '"+userId+"' AND ";
        }
        if (!lastName.equals("")) {
            sql = sql+"u.lastName = '"+lastName+"' AND ";
        }
        if (!firstName.equals("")) {
            sql = sql+"u.firstName = '"+firstName+"' AND ";
        }
        if (!email.equals("")) {
            sql = sql+"u.email = '"+email+"' AND ";
        }
        sql = sql + "u.type = 'Customer'";
        userList = systemInterface.searchUser(sql);
        if(userList.isEmpty()){
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("searchCar:searchUser",new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No User meeting the requirements","No results be got"));
        }
        return "manageUser?faces-redirect=true";
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

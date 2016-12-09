/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import fit5192.utility.PasswordMD5;
import fit5192.utility.SHA256;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author 王森
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    private String idnum;
    private String lastName;
    private String firstName;
    private String email;
    private String type;
    private String address;
    private String phoneNumber;
    private String userName;
    private String passWord;
    
    @EJB
    private SystemInterface systemInterface;
    public String loginController() throws NoSuchAlgorithmException, ServletException{
        FacesContext fct = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            System.out.println(userName+"         "+passWord);
            request.login(userName, passWord);
            System.out.println("right");
            User u = systemInterface.getUser(userName,SHA256.Encrypt(passWord));
            String sessionName = "UserLogin";  
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute(sessionName,(Object)u);
            if (request.isUserInRole("Customer")) {
                return "/Customer/searchCars?faces-redirect=true";
            } else if (request.isUserInRole("SalesPerson")) {
                return "/SalesPerson/SalesPerson?faces-redirect=true";
            }else{
                System.out.println("No role is matched");
                return "";
            }
        } catch (Exception e) {
            fct.addMessage("loginItem:login",new FacesMessage(FacesMessage.SEVERITY_WARN,"Wrong username or password",
                    "Please enter right username and password"));
            System.out.println("password is wrong");
            return "";
        }
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public SystemInterface getSystemInterface() {
        return systemInterface;
    }

    public void setSystemInterface(SystemInterface systemInterface) {
        this.systemInterface = systemInterface;
    }
    
    
}

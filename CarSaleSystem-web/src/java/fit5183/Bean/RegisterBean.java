/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.User;
import fit5192.entities.groups;
import fit5192.operation.SystemInterface;
import fit5192.utility.SHA256;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author 王森
 */
@Named(value = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable{
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
    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }
    
    public String register() throws Exception{
        FacesContext fct = FacesContext.getCurrentInstance();
        User result = systemInterface.checkUser(userName);
        if(result !=null){
            System.out.println("hahahahah");
            fct.addMessage("userForm:warn",new FacesMessage(FacesMessage.SEVERITY_INFO,"Username has been existed","Username has been existed,you have to input another one"));
            return "register?faces-redirect=true";
        }
        else{
            User user = new User();
            groups group = new groups();
            group.setUsername(userName);
            group.setGroupname(type);
            user.setAddress(address);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassWord(SHA256.Encrypt(passWord));
            user.setType(type);
            user.setUserName(userName);
            user.setPhoneNo(phoneNumber);
            systemInterface.addUser(user);
            systemInterface.addgroup(group);
            return "index?faces-redirect=true";
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

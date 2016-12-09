/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.Car;
import fit5192.entities.Sale;
import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import fit5192.utility.Time;
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
@Named(value = "detailsBean")
@SessionScoped
public class DetailsBean implements Serializable{
    private String valueString;
    private Long carid;
    private Car car;
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

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }


    public List<User> getUserList() {
        userList = systemInterface.getSalesManList();
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public Car getCar() throws Exception {
        System.out.println(carid);
        car = systemInterface.searchById(carid);
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    
    public Long getCarid() {
        return carid;
    }

    public void setCarid(Long carid) {
        this.carid = carid;
    }
    /**
     * Creates a new instance of DetailsBean
     */
    public DetailsBean() {
        carid = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("theCarId"));
    }
    /**
     * Customer select salesperson and car to generate a sale record
     */
    public void buyACar(){
        FacesContext fct = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User Custmer = (User)session.getAttribute("UserLogin");
        User SalesMan = systemInterface.searchUserById(Long.valueOf(valueString));
        Custmer.setNumOfOrder(Custmer.getNumOfOrder()+1);
        systemInterface.updateUserOrders(Custmer);
        car.setStatus("Out of Stock");
        systemInterface.updateCarStatus(car);
        try {
            Sale sale =new Sale();
            sale.setDate(Time.getDateTime());
            sale.setCar(car);
            sale.setCustomer(Custmer);
            sale.setSalesPerson(SalesMan);
            systemInterface.generateSale(sale);
            String message = Custmer.getFirstName()+" "+Custmer.getLastName()+" have bought "+car.getModelName()+" "+
                    car.getModelNo()+" from "+SalesMan.getFirstName()+" "+SalesMan.getLastName();
            System.out.println(message);
            fct.addMessage("detailCar:pDiv:submit:buyButton",new FacesMessage(FacesMessage.SEVERITY_WARN,
                    message,"Sorry,buy failedly"));
        }
        catch (Exception e) {
            e.toString();
        }
    }
    public String back(){
        return "SalesPerson?faces-redirect=true";
    }
}

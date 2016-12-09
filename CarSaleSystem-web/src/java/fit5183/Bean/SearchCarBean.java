/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.Car;
import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 王森
 */
@Named(value = "searchCarBean")
@SessionScoped
public class SearchCarBean implements Serializable{

    private String modelNo;
    private String modelName;
    private String make;
    private String type;
    private String thumbnail;
    private String name;
    private String status;
    private String price;

    private Car carTerm;

    public Car getCarTerm() {
        return carTerm;
    }

    public void setCarTerm(Car carTerm) {
        this.carTerm = carTerm;
    }

    public String getPrice() {
        
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @EJB
    private SystemInterface systemInterface;
    private List<Car> carList;

//    public String getName() {
//        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        User user = (User)session.getAttribute("UserLogin");
//        String userName = user.getFirstName()+" "+user.getLastName();
//        System.out.println("userName:" + userName);
//        return user.getFirstName()+" "+user.getLastName();
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
    /**
     * Creates a new instance of SearchCarBean
     */
    public SearchCarBean() {
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the input form user and search cars from database display the results
     * in datatable,If No vehicle is got,feedback is shown
     *
     * @return
     */
    public String getCars() {
        String sql = "SELECT c FROM Car c WHERE ";
        if (!modelName.equals("")) {
            sql = sql + "c.modelName = '" + modelName + "' AND ";
        }
        if (!modelNo.equals("")) {
            sql = sql + "c.modelNo = '" + modelNo + "' AND ";
        }
        if (!make.equals("")) {
            sql = sql + "c.make = '" + make + "' AND ";
        }
        if (!type.equals("All")) {
            sql = sql + "c.type = '" + type + "' AND ";
        }
        sql = sql + "c.status= 'In Stock'";
        carList = systemInterface.getCar(sql);
        if (carList.isEmpty()) {
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("searchCar:pDiv:search", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "No vehicles meeting the requirements", "No results be got"));
        }
        return "searchCars?faces-redirect=true";
    }

    public String getAllCars() {
        String sql = "SELECT c FROM Car c WHERE "; 
        if (!modelName.equals("")) {
            sql = sql + "c.modelName = '" + modelName + "' AND ";
        }

        if (!modelNo.equals("")) {
            sql = sql + "c.modelNo = '" + modelNo + "' AND ";
        }
        if (!make.equals("")) {
            sql = sql + "c.make = '" + make + "' AND ";
        }
        if (!type.equals("All")) {
            sql = sql + "c.type = '" + type + "' AND ";
        }
        sql = sql + "1=1";
        System.out.println(sql);
        carList = systemInterface.getCar(sql);
        return "viewCar?faces-redirect=true";
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index?faces-redirect=true";
    }

    /**
     * delete the car selected in datatable, the car would disappear
     *
     * @param car
     * @return
     */
    public String delete(Car car) {
        carList.remove(car);
        System.out.println(car.toString() + car.getMake() + car.getVIN());
        try {
            this.systemInterface.removeSaleByCar(car);
            this.systemInterface.removeCar(car);
        } catch (Exception ex) {
            Logger.getLogger(SearchCarBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("UserLogin");
        name = user.getFirstName() + " " + user.getLastName();
        System.out.println("userName:" + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

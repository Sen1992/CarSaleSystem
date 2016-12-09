/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5183.Bean;

import fit5192.entities.Car;
import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import fit5192.utility.Bing;
import fit5192.utility.SearchMethod;
import fit5192.utility.SearchUtils;
import fit5192.utility.Time;
import java.io.Serializable;
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
@Named(value = "addCar")
@SessionScoped
public class AddCar implements Serializable{

    private String modelNo;
    private String VIN;
    private String modelName;
    private String make;
    private String type;
    private String thumbnai;
    private String description;
    private String previewURL;
    

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
     * Creates a new instance of AddCar
     */
    public AddCar() {
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
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

    public String getThumbnai() {
        return thumbnai;
    }

    public void setThumbnai(String thumbnai) {
        this.thumbnai = thumbnai;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }
    public String search(){
        Bing resultWeb = SearchUtils.search(modelName+" "+modelNo,3, SearchMethod.WEB).get(1);
        Bing resultImage = SearchUtils.search(modelName+" "+modelNo,3, SearchMethod.IMAGE).get(1);
        thumbnai = resultImage.getMediaUrl();
        description = resultWeb.getTitle()+" : "+resultWeb.getDescription();
        previewURL = resultWeb.getDisplayUrl();
        System.out.println("hhhhhhhhh"+resultImage.getDisplayUrl());
        System.out.println(resultImage.getMediaUrl());
        System.out.println(resultWeb.getMediaUrl());
        System.out.println(resultWeb.getDescription());
        System.out.println("URL"+description);
        System.out.println("URL"+previewURL);
        return "addCar2?faces-redirect=true";
    }
    /**
     * get the information from form and insert it into database
     */
    public void submit() {
        String message1 = "You have successfully add "+ modelName+" "+modelNo+" at "+ Time.getDateTime();
        String message2 ="add unsuccessfully,please try again";
        FacesContext fct = FacesContext.getCurrentInstance();
        Car car = new Car();
        car.setModelName(modelName);
        car.setModelNo(modelNo);
        car.setMake(make);
        car.setVIN(VIN);
        car.setType(type);
        car.setThumbnail(thumbnai);
        car.setPreviewURL(previewURL);
        car.setDescription(description);
        try {
            this.systemInterface.addCar(car);
            fct.addMessage("addcarForm:submitAlert", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    message1, "Successfully"));
        } catch (Exception e) {
            fct.addMessage("addcarForm:pDiv:submitAlert", new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    message2,  "Sorry,buy failedly"));
        }   
    }
    
    
}

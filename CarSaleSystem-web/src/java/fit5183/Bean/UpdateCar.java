package fit5183.Bean;

import fit5192.entities.Car;
import fit5192.entities.User;
import fit5192.operation.SystemInterface;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "updateCar")
@RequestScoped

public class UpdateCar{
    private String id;
    private String modelNo;
    private String VIN;
    private String modelName;
    private String make;
    private String type;
    private String thumbnai;
    private String description;
    private String previewURL;
    private String price;
    private String status;
    
        
    @EJB
    private SystemInterface systemInterface;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }
    
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
    
    
    

    /**
     * Get the car by Id and set some attibutes from form,then merge the record.
     * @throws Exception 
     */
    public String update() throws Exception {
         FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("searchCar:searchUser",new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No User meeting the requirements","No results be got"));
        try{
        Car car = systemInterface.searchById(Long.valueOf(id));
        car.setModelName(modelName);
        car.setModelNo(modelNo);
        car.setMake(make);
        car.setDescription(description);
        car.setPrice(price);
        car.setPreviewURL(previewURL);
        car.setVIN(VIN);
        car.setStatus(status);
        car.setType(type);
        systemInterface.updateCarStatus(car);
        fct.addMessage("detailCar:submit:updateButton",new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Update successfully","Update successfully"));
        }
        catch(Exception e){
            fct.addMessage("detailCar:submit:updateButton",new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Update successfully","Update successfully"));
        }
        return "SalesPerson?faces-redirect=true";
    }

    public UpdateCar() throws Exception {
            id  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            modelName  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("modelName");
            modelNo  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("modelNo");
            make  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("make");
            type  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
            thumbnai  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("thumbnai");
            previewURL  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("previewURL");
            price  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("price");
            status  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("status");
            description  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("description");
            VIN  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("VIN");
    }
}

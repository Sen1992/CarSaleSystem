/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.operation;

import fit5192.entities.Car;
import fit5192.entities.Sale;
import fit5192.entities.User;
import fit5192.entities.groups;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author 王森
 */
@Remote
public interface SystemInterface {
    /**
     * Add the property being passed as parameter into the repository
     *
     * @param car - the property to add
     */
    public void addCar(Car car) throws Exception;
    
    /**
     * Sale person add a user to the system
     * @param user
     * @throws Exception 
     */
    public void addCustomer(User user) throws Exception;
    
    /**
     * search car accroding to make
     * @param make
     * @return
     * @throws Exception 
     */
    public Car searchById(Long id) throws Exception;
    
    public void addUser(User user) throws Exception;
    
    public User getUser(String userName,String passWord);
    
    public User checkUser(String userName);
    
    public List<Car> getCar(String sql);
    
    public List<User> searchUser(String sql);
    
    public List<User> getSalesManList();
    
    public void generateSale(Sale sale);
    
    public User searchUserById(Long id);
    
    public void updateCarStatus(Car car);
    
    public void updateUserOrders(User user);
    
    public void removeCar(Car car)throws Exception;
    
    public String removeSaleByCar(Car car) throws Exception;
    
    public List<Sale> getSalesByCustomId(Long id) throws Exception;
    
    public void addgroup(groups group) throws Exception;

}

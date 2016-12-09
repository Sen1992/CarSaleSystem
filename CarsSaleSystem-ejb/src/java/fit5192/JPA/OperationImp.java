/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.JPA;

import fit5192.entities.Car;
import fit5192.entities.Sale;
import fit5192.entities.User;
import fit5192.entities.groups;
import fit5192.operation.SystemInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author 王森
 */
@Stateless
public class OperationImp implements SystemInterface{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void addCar(Car car) throws Exception {
        entityManager.persist(car);
    }

    @Override
    public void addCustomer(User user) throws Exception {
        entityManager.persist(user);
    }
    @Override
    public Car searchById(Long id) throws Exception {
        try {
            TypedQuery<Car> query = this.entityManager.createQuery("SELECT c FROM Car c WHERE c.idCar ="+id,Car.class); 
            return query.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addUser(User user) throws Exception {
        this.entityManager.persist(user);
    }
    @Override
    public User getUser(String userName, String passWord){
        try {
            TypedQuery<User> query = this.entityManager.createQuery("SELECT U FROM User U WHERE U.userName = '"+userName+"' AND U.passWord = '"+passWord+"'",User.class);
            return query.getSingleResult();  
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User checkUser(String userName){
        try{
            TypedQuery<User> query = this.entityManager.createQuery("SELECT U FROM User U WHERE U.userName = '"+userName+"'",User.class);
            return (User) query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Car> getCar(String sql) {
        try {
            TypedQuery<Car> query = this.entityManager.createQuery(sql,Car.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> searchUser(String sql) {
        try {
            TypedQuery<User> query = this.entityManager.createQuery(sql,User.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getSalesManList() {
        try {
            TypedQuery<User> query = this.entityManager.createQuery("SELECT U FROM User U WHERE U.type = 'SalesPerson'",User.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void generateSale(Sale sale) {
        entityManager.persist(sale);
    }

    @Override
    public User searchUserById(Long id) {
        try {
            TypedQuery<User> query = this.entityManager.createQuery("SELECT U FROM User U WHERE U.idUser ="+id,User.class);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updateCarStatus(Car car) {
        entityManager.merge(car);
    }

    @Override
    public void removeCar(Car car) throws Exception{
        entityManager.remove(entityManager.merge(car));
    }

    @Override
    public String removeSaleByCar(Car car) throws Exception {
        try {
            Long id = car.getId();
            TypedQuery<Sale> query = this.entityManager.createQuery("SELECT S FROM Sale S WHERE S.car.idCar ="+id,Sale.class);
            Sale sale =  query.getSingleResult();
            System.out.println(sale.getDate()+sale.getSalesPerson());
            this.entityManager.remove(this.entityManager.merge(sale));
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updateUserOrders(User user) {
       entityManager.merge(user);
    }

    @Override
    public List<Sale> getSalesByCustomId(Long id) throws Exception {
        try {
            TypedQuery<Sale> query = this.entityManager.createQuery("SELECT S FROM Sale S WHERE S.customer.idUser ="+id,Sale.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addgroup(groups group) throws Exception {
        this.entityManager.persist(group);
    }
    
    
    
    
    
    
    
}

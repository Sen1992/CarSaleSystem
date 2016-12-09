/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.gui;

import fit5192.entities.Car;
import fit5192.operation.SystemInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ejb.EJB;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author 王森
 */
public class Main implements ActionListener,ListSelectionListener{

//    private Car car;
    private ApplicationGUI gui;
    private JTable jt;
    @EJB
    private static SystemInterface systeminterface;
    
    public Main() throws Exception {
    }
    public void initView(){
        this.gui = new ApplicationGUI(this, this);
        this.jt = gui.getTable();
        this.jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    int selectedRowIndex = gui.getTable().getSelectedRow();
                    String propertyId = gui.getTable().getValueAt(selectedRowIndex,0).toString();
                    try {
                        System.out.println(propertyId);
                        System.out.println(Long.parseLong(propertyId));
                        Car c = systeminterface.searchById(Long.parseLong(propertyId));
                        new CarDetailsGUI(c);
                    } catch (Exception ex) {
                        Logger.getLogger(ApplicationGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("JJJJJJJJJJJJJHHHHHH");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==gui.getSearchButton()){
            try {
                //            this.searchByMake(this.gui.getmakeText());
                this.search();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource()==gui.getClearButton()){
            this.gui.clearTextField();
            this.gui.clearResultTable();
        }
        else{
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    m.initView();
                }
            });
    }

    public void search() throws Exception{
        String makeString = this.gui.getmakeText();
        String modelNameString = this.gui.getmodelNameText();
        String modelNoString = this.gui.getmodelNoText();
 //       String typeString = this.gui.gettypeText();
        String typeString = this.gui.getTypeComboBox().getSelectedItem().toString();
        List<Car> properties =null;      
        String sql = "SELECT c FROM Car c WHERE ";
        if (!modelNameString.equals("")) {
            sql = sql+"c.modelName = '"+modelNameString+"' AND ";
        }
        if (!modelNoString.equals("")) {
            sql = sql+"c.modelNo = '"+modelNoString+"' AND ";
        }
        if (!makeString.equals("")) {
            sql = sql+"c.make = '"+makeString+"' AND ";
        }
        if(!typeString.equals("All")){
            sql = sql + "c.type = '"+typeString+"' AND ";
        }
        sql = sql +"1=1";
        System.out.println(sql);
        properties = systeminterface.getCar(sql);
        if (properties != null && !properties.isEmpty()) {
                this.gui.dispalyInfoInTable(properties);
        } else {
                this.gui.displayMessageInDialog("No matched properties found"); 
                this.gui.clearResultTable();
        }  
        for (Car car : properties) {
            System.out.println(car.toString());
            
        }
    }   
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.gui;

import fit5192.entities.Car;
import fit5192.operation.SystemInterface;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author 王森
 */
public class ApplicationGUI extends JFrame{
    private static final String[] TABLE_COLUMNS = {"ID","Model Name","Model No","Make","Type","Status"};
    private final JTable resultTable;
    
    private final JLabel modelNoLabel;
    private final JLabel modelNameLabel;
    private final JLabel makeLabel;
    private final JLabel typeLabel;
    
    
    private final JTextField modelNoJTextField;
    private final JTextField modelNameJTextField;
    private final JTextField makeJTextField;
//    private final JTextField typeJTextField;
    private JComboBox typeComboBox;
    
    private final JPanel searchPanel;
    private final JPanel buttonPanel;
    
    private final JButton searchButton;
    private final JButton clearButton;

    private final Container container;
    @EJB
    private static SystemInterface systeminterface;

    public ApplicationGUI(ActionListener actionListener,ListSelectionListener listSelectionListener) {
        super("Car Sale System");
        this.searchButton = new JButton("Search");
        this.clearButton = new JButton("Clear");
        
        this.modelNoLabel = new JLabel("Model No :",JLabel.RIGHT);
        this.modelNoJTextField = new JTextField();
        
        this.modelNameLabel = new JLabel("Model Name :",JLabel.RIGHT);
        this.modelNameJTextField = new JTextField();
        
        this.makeLabel = new JLabel("Make :",JLabel.RIGHT);
        this.makeJTextField = new JTextField();
        
        this.typeLabel = new JLabel("Type :",JLabel.RIGHT);
//        this.typeJTextField = new JTextField();
        this.typeComboBox = new JComboBox(new String[]{"All","Sedan", "4 wheel drive", "Truck"});
       
          
        container = this.getContentPane();
        
        this.resultTable = new JTable(new DefaultTableModel(TABLE_COLUMNS,0));
        this.resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel resulTableColumnModel = this.resultTable.getColumnModel();
        resulTableColumnModel.getColumn(0).setPreferredWidth(100);
        resulTableColumnModel.getColumn(1).setPreferredWidth(140);
        resulTableColumnModel.getColumn(2).setPreferredWidth(100);
        resulTableColumnModel.getColumn(3).setPreferredWidth(140);
        resulTableColumnModel.getColumn(4).setPreferredWidth(100);
        resulTableColumnModel.getColumn(5).setPreferredWidth(100);
        
        this.searchPanel = new JPanel();
        this.buttonPanel = new JPanel();
        
        
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        this.searchPanel.setLayout(new GridLayout(4,2,10,2));
        
        this.searchButton.addActionListener(actionListener);
        this.clearButton.addActionListener(actionListener);
        
        this.searchPanel.add(modelNoLabel);
        this.searchPanel.add(modelNoJTextField);
        this.searchPanel.add(modelNameLabel);
        this.searchPanel.add(modelNameJTextField);
        this.searchPanel.add(makeLabel);
        this.searchPanel.add(makeJTextField);
        this.searchPanel.add(typeLabel);
        this.searchPanel.add(typeComboBox);
        this.searchPanel.setBorder(BorderFactory.createTitledBorder("selection information"));
        

        
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,3));
        buttonPanel.add(searchButton);
        buttonPanel.add(this.clearButton);
        
        container.add(searchPanel);
        container.add(new JScrollPane(this.resultTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        container.add(this.buttonPanel);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(700,700);
        this.setVisible(true);
    }
    
    public void dispalyInfoInTable(List<Car> cars){
        this.clearResultTable();
//        this.clearTextField();
        
        for(Car car : cars){
            ((DefaultTableModel)this.resultTable.getModel()).addRow(new Object[]{car.getId()+"",car.getModelName(),car.getModelNo(),car.getMake(),car.getType(),car.getStatus()});
        }
    }
    
    public void clearResultTable() {     
        int numberOfRow = this.resultTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.resultTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }
        }            
    }
    public void clearTextField(){
        modelNoJTextField.setText("");
        modelNameJTextField.setText("");
        makeJTextField.setText("");
    }
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    public JButton getSearchButton(){
        return searchButton;
    }
    public JButton getClearButton(){
        return clearButton;
    }
    public String getmodelNameText(){
        return modelNameJTextField.getText();
    }
    public String getmodelNoText(){
        return modelNoJTextField.getText();
    }
    
    public String getmakeText(){
        return makeJTextField.getText();
    }
    
    public JTable getTable(){
        return this.resultTable;
    }

    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(JComboBox typeComboBox) {
        this.typeComboBox = typeComboBox;
    }
    
    
    
                
}

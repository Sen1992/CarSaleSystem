/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.gui;

import fit5192.entities.Car;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author 王森
 */
public class CarDetailsGUI extends JFrame{
    private final Container container;
    private final JTable table;
    private final JLabel title;
    private final JTextArea desc;
    private final JPanel descPanel;
    private final JPanel urlPanel;
    private final JLabel imageLabel;
    private final JLabel previewURL;
    private final LinkLabel url;
    

    public CarDetailsGUI(Car car) throws Exception {
        super("The detailed Description");
        container = this.getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        this.setSize(800,800);
        this.setVisible(true);
        previewURL = new JLabel("PreViewURL:");
        url = new LinkLabel(car.getPreviewURL(),car.getPreviewURL());
        desc = new JTextArea(12,75);
        desc.setEditable(false);
        desc.setFont(new Font("Serif",Font.ITALIC,15));
        desc.setText(car.getDescription());
        descPanel = new JPanel();
 //       descPanel.setSize(400,200);
        descPanel.add(desc);
        descPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        title = new JLabel("The Details About The Car");
        title.setFont(new Font("Monospaced",Font.BOLD,30));
        imageLabel = new JLabel(new ImageIcon(car.getThumbnail()));
        imageLabel.setSize(400,400);
        String[] columnNames = {"Attribute","DESC"};
        Object[][] data = {{"Model Name",car.getModelName()},
            {"Model No",car.getModelNo()},
            {"VIN",car.getVIN()},
            {"Make",car.getMake()},
            {"Type",car.getType()},
            {"Status",car.getStatus()}
        };
        urlPanel = new JPanel();
        urlPanel.add(previewURL);
        urlPanel.add(url);
        this.table = new JTable(data,columnNames);
        table.setRowHeight(26);
        table.setFont(new Font("Serif",Font.PLAIN,18));
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        container.add(title);
        container.add(table);
        container.add(descPanel);
        container.add(imageLabel);
        container.add(urlPanel);
    }
    
}

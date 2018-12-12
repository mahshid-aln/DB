/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mahshid
 */
public class priceFrame extends JFrame {

    queryManager qm;

    private JButton insertbutton = new JButton("Insert");
    private JButton updatebutton = new JButton("Update");
    private JButton deletebutton = new JButton("Delete");
    private JButton showbutton = new JButton("Show");
    //   private JButton okbutton = new JButton("Ok");

    private JTextField daddressfield = new JTextField(20);
    private JTextField saddressfield = new JTextField(20);
    private JTextField agencyidfield = new JTextField(20);
    private JTextField pricefield = new JTextField(20);
    // private JTextField queryfield = new JTextField(20);

    private int destination;
    private int source;
    private int agency;
    private int price;
    //  private String query;

   public priceFrame(queryManager qm) throws HeadlessException, ClassNotFoundException, SQLException {
        this.qm = qm;
        this.setSize(500, 350);
        this.setLocation(200, 500);
        this.setTitle("Price");
        actions();
        add(initbutton());
        this.setVisible(true);

    }

    private JPanel initbutton() {
        JPanel panel = new JPanel();
        JLabel daddressLabel = new JLabel("Dst add. code");
        JLabel saddressLabel = new JLabel("Src add. code");
        JLabel agencyIdLabel = new JLabel("Agency ID");
        JLabel priceLabel = new JLabel("Price");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.minimumLayoutSize(panel);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(daddressLabel)
                        .addComponent(daddressfield, 200, 200, 200)
                        .addComponent(saddressLabel)
                        .addComponent(saddressfield, 200, 200, 200)
                        .addComponent(agencyIdLabel)
                        .addComponent(agencyidfield, 200, 200, 200)
                        .addComponent(priceLabel)
                        .addComponent(pricefield, 200, 200, 200)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(insertbutton)
                                .addComponent(updatebutton)
                                .addComponent(deletebutton)
                                .addComponent(showbutton))
                ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(daddressLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(daddressfield))
                .addComponent(saddressLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saddressfield))
                .addComponent(agencyIdLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(agencyidfield))
                .addComponent(priceLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pricefield))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(insertbutton)
                        .addComponent(updatebutton)
                        .addComponent(deletebutton)
                        .addComponent(showbutton))
        );

        return panel;
    }

    void actions() {

        insertbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFieldData();
                    qm.insert_price(destination, source, agency, price);
                } catch (SQLException ex) {
                    Logger.getLogger(addressFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        updatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFieldData();
                    qm.update_price(destination, source, agency, price);
                } catch (SQLException ex) {
                    Logger.getLogger(priceFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getprimarykey();
                    qm.delete_price(destination, source, agency, price);
                } catch (SQLException ex) {
                    Logger.getLogger(priceFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        showbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qm.show_price();
                } catch (SQLException ex) {
                    Logger.getLogger(priceFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        /*    okbutton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

         }
         });
         */
    }

    private void getprimarykey() {
        this.destination = Integer.parseInt(daddressfield.getText());
        this.source = Integer.parseInt(saddressfield.getText());
        this.agency = Integer.parseInt(agencyidfield.getText());
    }

    private void getFieldData() {

        this.destination = Integer.parseInt(daddressfield.getText());
        this.source = Integer.parseInt(saddressfield.getText());
        this.agency = Integer.parseInt(agencyidfield.getText());
        this.price = Integer.parseInt(pricefield.getText());
        //  this.query = queryfield.getText();

    }

}

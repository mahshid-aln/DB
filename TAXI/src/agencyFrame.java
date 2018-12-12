/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.awt.BorderLayout;
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
import javax.swing.SpringLayout;

/**
 *
 * @author Mahshid
 */
public class agencyFrame extends JFrame {

    queryManager qm;

    private JButton insertbutton = new JButton("Insert");
    private JButton updatebutton = new JButton("Update");
    private JButton deletebutton = new JButton("Delete");
    private JButton showbutton = new JButton("Show");
    // private JButton okbutton = new JButton("Ok");

    private JTextField idfield = new JTextField(20);
    private JTextField namefield = new JTextField(20);
    private JTextField addressfield = new JTextField(20);
    private JTextField phonefield = new JTextField(20);
    //  private JTextField queryfield = new JTextField(20);

    private int id;
    private String name;
    private int address;
    private String phone;
    // private String query;

    public agencyFrame(queryManager qm) throws HeadlessException {
        this.qm = qm;
        this.setSize(500, 350);
        this.setLocation(200, 500);
        this.setTitle("Agency");
        add(initbutton());
        actions();
        this.setVisible(true);
    }

    private JPanel initbutton() {
        JPanel panel = new JPanel();
        JLabel agencyIdLabel = new JLabel("Agency ID");
        JLabel agencyNameLabel = new JLabel("Agency Name");
        JLabel AdressCodeLabel = new JLabel("Adress Code");
        JLabel PhoneLabel = new JLabel("Phone No.");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.minimumLayoutSize(panel);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(agencyIdLabel)
                        .addComponent(idfield, 200, 200, 200)
                        .addComponent(agencyNameLabel)
                        .addComponent(namefield, 200, 200, 200)
                        .addComponent(AdressCodeLabel)
                        .addComponent(addressfield, 200, 200, 200)
                        .addComponent(PhoneLabel)
                        .addComponent(phonefield, 200, 200, 200)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(insertbutton)
                                .addComponent(updatebutton)
                                .addComponent(deletebutton)
                                .addComponent(showbutton))
                ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(agencyIdLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(idfield))
                .addComponent(agencyNameLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(namefield))
                .addComponent(AdressCodeLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressfield))
                .addComponent(PhoneLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phonefield))
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
                    qm.insert_agency(id, name, address, phone);
                } catch (SQLException ex) {
                    Logger.getLogger(addressFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        updatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFieldData();
                try {
                    getFieldData();
                    qm.update_agency(id, name, address, phone);
                } catch (SQLException ex) {
                    Logger.getLogger(agencyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getprimarykey();
                    qm.delete_agency(id);
                } catch (SQLException ex) {
                    Logger.getLogger(agencyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        showbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qm.show_agency();
                } catch (SQLException ex) {
                    Logger.getLogger(agencyFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        this.id = Integer.parseInt(idfield.getText());
    }

    private void getFieldData() {

        this.id = Integer.parseInt(idfield.getText());
        this.name = namefield.getText();
        this.address = Integer.parseInt(addressfield.getText());
        this.phone = phonefield.getText();
        //  this.query = queryfield.getText();

    }

}

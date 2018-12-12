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

/**
 *
 * @author Mahshid
 */
public class addressFrame extends JFrame {

    queryManager qm;

    private JButton insertbutton = new JButton("Insert");
    private JButton updatebutton = new JButton("Update");
    private JButton deletebutton = new JButton("Delete");
    private JButton showbutton = new JButton("Show");
    //  private JButton okbutton = new JButton("Ok");

    private JTextField codefield = new JTextField(20);
    private JTextField cityfield = new JTextField(20);
    private JTextField streetfield = new JTextField(20);
    //  private JTextField queryfield = new JTextField(20);

    private int code;
    private String city;
    private String street;
    //  private String query;

    public addressFrame(queryManager qm) throws HeadlessException {

        this.qm = qm;
        this.setSize(500, 300);
        this.setLocation(200, 500);
        this.setTitle("Address");
        add(initbutton());
        actions();
        this.setVisible(true);

    }

    private JPanel initbutton() {
        JPanel panel = new JPanel();
        JLabel adressCodeLabel = new JLabel("Address Code");
        JLabel cityLabel = new JLabel("City");
        JLabel streetLabel = new JLabel("Street");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.minimumLayoutSize(panel);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(adressCodeLabel)
                        .addComponent(codefield, 200, 200, 200)
                        .addComponent(cityLabel)
                        .addComponent(cityfield, 200, 200, 200)
                        .addComponent(streetLabel)
                        .addComponent(streetfield, 200, 200, 200)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(insertbutton)
                                .addComponent(updatebutton)
                                .addComponent(deletebutton)
                                .addComponent(showbutton))
                ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(adressCodeLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(codefield))
                .addComponent(cityLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cityfield))
                .addComponent(streetLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(streetfield))
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
                    if (!codefield.getText().trim().isEmpty() && !cityfield.getText().trim().isEmpty() && !streetfield.getText().trim().isEmpty()) {
                        qm.insert_address(code, city, street);
                    } else {
                    }
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
                    qm.update_address(code, city, street);
                } catch (SQLException ex) {
                    Logger.getLogger(addressFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getprimarykey();
                    qm.delete_address(code);
                } catch (SQLException ex) {
                    Logger.getLogger(addressFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        showbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qm.show_address();
                } catch (SQLException ex) {
                    Logger.getLogger(addressFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        this.code = Integer.parseInt(codefield.getText());
    }

    private void getFieldData() {

        this.code = Integer.parseInt(codefield.getText());
        this.city = cityfield.getText();
        this.street = streetfield.getText();
        //   this.query = queryfield.getText();

    }

}

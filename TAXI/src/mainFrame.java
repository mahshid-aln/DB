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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;
import javax.swing.JTextField;

/**
 *
 * @author Mahshid
 */
public class mainFrame extends JPanel {

    queryManager qm;

    private JButton agencybutton = new JButton("Agency");
    private JButton taxibutton = new JButton("Taxi");
    private JButton driverbutton = new JButton("Driver");
    private JButton customerbutton = new JButton("Costumer");
    private JButton orderbutton = new JButton("Order");
    private JButton addressbutton = new JButton("Address");
    private JButton pricebutton = new JButton("Price");
    private JButton okbutton = new JButton("Ok");
    private JTextField queryfield = new JTextField(20);
    private String query;

    JFrame af;
    JFrame tf;
    JFrame df;
    JFrame cf;
    JFrame of;
    JFrame adf;
    JFrame pf;

    //...lastButton
    //...lastButton
    public mainFrame() throws ClassNotFoundException, SQLException {

        qm = new queryManager();
        setLayout(new BorderLayout(5, 5));
        actions();
        add(initButtons(), BorderLayout.CENTER);

    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        JLabel querylabel = new JLabel("Query");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.minimumLayoutSize(panel);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addressbutton)
                                .addComponent(agencybutton)
                                .addComponent(customerbutton)
                                .addComponent(driverbutton)
                                .addComponent(orderbutton)
                                .addComponent(pricebutton)
                                .addComponent(taxibutton))
                        .addComponent(querylabel)
                        .addComponent(queryfield, 400, 400, 550)
                        .addComponent(okbutton)
                ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressbutton)
                        .addComponent(agencybutton)
                        .addComponent(customerbutton)
                        .addComponent(driverbutton)
                        .addComponent(orderbutton)
                        .addComponent(pricebutton)
                        .addComponent(taxibutton))
                .addGap(20)
                .addComponent(querylabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(queryfield))
                .addComponent(okbutton)
        );

        return panel;
    }

    void actions() {
        addressbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                adf = new addressFrame(qm);
            }
        });

        agencybutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                af = new agencyFrame(qm);
            }
        });

        customerbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cf = new customerFrame(qm);
                } catch (HeadlessException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        driverbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    df = new driverFrame(qm);
                } catch (HeadlessException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        orderbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    of = new orderFrame(qm);
                } catch (HeadlessException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pricebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pf = new priceFrame(qm);
                } catch (HeadlessException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        taxibutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tf = new taxiFrame(qm);
                } catch (HeadlessException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFieldData();
                    // System.out.println(query);
                    try {
                        qm.queryexe(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    private void getFieldData() throws ParseException {
        this.query = queryfield.getText();
    }

}

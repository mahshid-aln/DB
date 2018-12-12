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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class taxiFrame extends JFrame {

    queryManager qm;

    private JButton insertbutton = new JButton("Insert");
    private JButton updatebutton = new JButton("Update");
    private JButton deletebutton = new JButton("Delete");
    private JButton showbutton = new JButton("Show");
    //   private JButton okbutton = new JButton("Ok");

    private JTextField platefield = new JTextField(20);
    private JTextField agencyidfield = new JTextField(20);
    private JTextField modelfield = new JTextField(20);
    private JTextField memberdatefield = new JTextField(20);
    private JTextField enddatefield = new JTextField(20);
    //    private JTextField queryfield = new JTextField(20);

    private String plate;
    private int agency;
    private String model;
    private Date memberdate;
    private Date enddate;
    //  private String query;

    public taxiFrame(queryManager qm) throws HeadlessException, ClassNotFoundException, SQLException {

        this.qm = qm;
        this.setSize(500, 400);
        this.setLocation(200, 500);
        this.setTitle("Taxi");
        actions();
        add(initbutton());
        this.setVisible(true);

    }

    private JPanel initbutton() {

        JPanel panel = new JPanel();
        JLabel plateLabel = new JLabel("Number-plate");
        JLabel idLabel = new JLabel("Agency ID");
        JLabel modelLabel = new JLabel("Model");
        JLabel dateLabel = new JLabel("Date of Membership");
        JLabel endLabel = new JLabel("End of Membership");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.minimumLayoutSize(panel);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(plateLabel)
                        .addComponent(platefield, 200, 200, 200)
                        .addComponent(idLabel)
                        .addComponent(agencyidfield, 200, 200, 200)
                        .addComponent(modelLabel))
                .addComponent(modelfield, 200, 200, 200)
                .addComponent(dateLabel)
                .addComponent(memberdatefield, 200, 200, 200)
                .addComponent(endLabel)
                .addComponent(enddatefield, 200, 200, 200)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(insertbutton)
                        .addComponent(updatebutton)
                        .addComponent(deletebutton)
                        .addComponent(showbutton))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(plateLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(platefield))
                .addComponent(idLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(agencyidfield))
                .addComponent(modelLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(modelfield))
                .addComponent(dateLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(memberdatefield))
                .addComponent(endLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(enddatefield))
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
                    qm.insert_taxi(plate, agency, model, memberdate, enddate);
                } catch (SQLException ex) {
                    Logger.getLogger(addressFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(taxiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        updatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFieldData();
                    qm.update_taxi(plate, agency, model, memberdate, enddate);
                } catch (SQLException ex) {
                    Logger.getLogger(taxiFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(taxiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getprimarykey();
                    qm.delete_taxi(plate);
                } catch (SQLException ex) {
                    Logger.getLogger(taxiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        showbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qm.show_taxi();
                } catch (SQLException ex) {
                    Logger.getLogger(taxiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        /*   okbutton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

         }
         });
         */
    }

    private void getprimarykey() {
        this.plate = platefield.getText();
    }

    private void getFieldData() throws ParseException {

        String memstring;
        String endstring;
        this.plate = platefield.getText();
        this.agency = Integer.parseInt(agencyidfield.getText());
        this.model = modelfield.getText();
        memstring = memberdatefield.getText();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(memstring);
        java.sql.Date sqldate = new java.sql.Date(parsed.getTime());
        this.memberdate = sqldate;

        endstring = enddatefield.getText();
        parsed = format.parse(endstring);
        sqldate = new java.sql.Date(parsed.getTime());
        this.enddate = sqldate;

        System.out.println("date " + memberdate);

        //   this.query = queryfield.getText();
    }

}

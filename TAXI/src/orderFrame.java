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
import java.sql.Time;
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
public class orderFrame extends JFrame {

    queryManager qm;

    private JButton insertbutton = new JButton("Insert");
    private JButton updatebutton = new JButton("Update");
    private JButton deletebutton = new JButton("Delete");
    private JButton showbutton = new JButton("Show");
    private JButton procedurebutton = new JButton("undone orders");
    //  private JButton okbutton = new JButton("Ok");

    private JTextField orderidfield = new JTextField(20);
    private JTextField agencyidfield = new JTextField(20);
    private JTextField saddressfield = new JTextField(20);
    private JTextField daddressfield = new JTextField(20);
    private JTextField datefield = new JTextField(20);
    private JTextField timefield = new JTextField(20);
    private JTextField statusfield = new JTextField(20);
    private JTextField customeridfield = new JTextField(20);
    // private JTextField queryfield = new JTextField(20);

    private int orderid;
    private int agencyid;
    private int sourceadd;
    private int destadd;
    private Date date;
    private Time time;
    private String status;
    private int customer;
    //  private String query;

    public orderFrame(queryManager qm) throws HeadlessException, ClassNotFoundException, SQLException {
        this.qm = qm;
        this.setSize(500, 550);
        this.setLocation(200, 500);
        this.setTitle("Order");
        actions();
        add(initbutton());
        this.setVisible(true);

    }

    private JPanel initbutton() {
        JPanel panel = new JPanel();
        JLabel orderIdLabel = new JLabel("Order ID");
        JLabel agencyIdLabel = new JLabel("Agency ID");
        JLabel srcLabel = new JLabel("Src add. code");
        JLabel dstLabel = new JLabel("Dst add. code");
        JLabel orderdateLabel = new JLabel("Order Date");
        JLabel ordertimeLabel = new JLabel("Order Time");
        JLabel statusLabel = new JLabel("Order Status");
        JLabel customerLabel = new JLabel("Customer ID");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.minimumLayoutSize(panel);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(orderIdLabel)
                        .addComponent(orderidfield, 200, 200, 200)
                        .addComponent(agencyIdLabel)
                        .addComponent(agencyidfield, 200, 200, 200)
                        .addComponent(srcLabel)
                        .addComponent(saddressfield, 200, 200, 200)
                        .addComponent(dstLabel)
                        .addComponent(daddressfield, 200, 200, 200)
                        .addComponent(orderdateLabel)
                        .addComponent(datefield, 200, 200, 200)
                        .addComponent(ordertimeLabel)
                        .addComponent(timefield, 200, 200, 200)
                        .addComponent(statusLabel)
                        .addComponent(statusfield, 200, 200, 200)
                        .addComponent(customerLabel)
                        .addComponent(customeridfield, 200, 200, 200)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(insertbutton)
                                .addComponent(updatebutton)
                                .addComponent(deletebutton)
                                .addComponent(showbutton)
                                .addComponent(procedurebutton)))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(orderIdLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(orderidfield))
                .addComponent(agencyIdLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(agencyidfield))
                .addComponent(srcLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saddressfield))
                .addComponent(dstLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(daddressfield))
                .addComponent(orderdateLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(datefield))
                .addComponent(ordertimeLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(timefield))
                .addComponent(statusLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(statusfield))
                .addComponent(customerLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(customeridfield))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(insertbutton)
                        .addComponent(updatebutton)
                        .addComponent(deletebutton)
                        .addComponent(showbutton)
                        .addComponent(procedurebutton))
        );

        return panel;

    }

    void actions() {

        insertbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFieldData();
                    qm.insert_order(orderid, agencyid, sourceadd, destadd, date, time, status, customer);
                } catch (SQLException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        updatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFieldData();
                    qm.update_order(orderid, agencyid, sourceadd, destadd, date, time, status, customer);
                } catch (SQLException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getprimarykey();
                    qm.delete_order(orderid);
                } catch (SQLException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        showbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qm.show_order();
                } catch (SQLException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        procedurebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    getagencyid();

                    qm.queryexe("call count_undone('" + agencyid + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(orderFrame.class.getName()).log(Level.SEVERE, null, ex);
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

    private void getagencyid() {
        this.agencyid = Integer.parseInt(agencyidfield.getText());
    }

    private void getprimarykey() {
        this.orderid = Integer.parseInt(orderidfield.getText());
    }

    private void getFieldData() throws ParseException {

        String datestring;
        String timestring;
        this.orderid = Integer.parseInt(orderidfield.getText());
        this.agencyid = Integer.parseInt(agencyidfield.getText());
        this.sourceadd = Integer.parseInt(saddressfield.getText());
        this.destadd = Integer.parseInt(daddressfield.getText());

        datestring = datefield.getText();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(datestring);
        java.sql.Date sqldate = new java.sql.Date(parsed.getTime());
        this.date = sqldate;

        this.time = java.sql.Time.valueOf(timefield.getText());
        this.status = statusfield.getText();
        this.customer = Integer.parseInt(customeridfield.getText());
        //   this.query = queryfield.getText();

    }

}

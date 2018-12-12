/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mahshid
 */
public class queryManager {

    Statement stmt;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/projectdb";
    static final String DB_USER = "root";
    static final String DB_PASS = "dardanus";

    public queryManager() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        stmt = con.createStatement();

    }

    void queryexe(String sql) throws SQLException {
        String start = "";
        try {
            start = sql.substring(0, 6);
            if (start.equalsIgnoreCase("select") || sql.startsWith("call count_undone")) {
                ResultSet rs = stmt.executeQuery(sql);
                JTable table = new JTable(buildTableModel(rs));
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } else {
                stmt.executeUpdate(sql);
            }

        } catch (java.lang.StringIndexOutOfBoundsException exp) {
            JOptionPane.showMessageDialog(null, "empty query");
        }

    }

    //insert
    void insert_address(int code, String city, String street) throws SQLException {
        String sql = "insert into address values('" + code + "', '" + city + "', '" + street + "')";
        stmt.executeUpdate(sql);
    }

    void insert_agency(int id, String name, int addcode, String phone) throws SQLException {
        String sql = "insert into agency values('" + id + "', '" + name + "', '" + addcode + "', '" + phone + "')";
        stmt.executeUpdate(sql);
    }

    void insert_customer(int id, String name, int addcode, String phone) throws SQLException {
        String sql = "insert into customer values('" + id + "', '" + name + "', '" + addcode + "', '" + phone + "', 0)";
        stmt.executeUpdate(sql);
    }

    void insert_driver(int id, String plate, String name, String phone) throws SQLException {
        String sql = "insert into driver values('" + id + "', '" + plate + "', '" + name + "', '" + phone + "')";
        stmt.executeUpdate(sql);
    }

    void insert_order(int id, int agency, int source, int dest, Date order_date, Time order_time, String status, int customer) throws SQLException {
        String sql = "insert into orders values('" + id + "', '" + agency + "', '" + source + "', '" + dest + "', '" + order_date + "', '" + order_time + "', '" + status + "', '" + customer + "')";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "not able to add more orders");
        }
    }

    void insert_price(int dest, int source, int agency, int price) throws SQLException {
        String sql = "insert into price values('    " + dest + "', '" + source + "', '" + agency + "', '" + price + "')";
        stmt.executeUpdate(sql);
    }

    void insert_taxi(String plate, int agency, String model, Date membership, Date end) throws SQLException {
        String sql = "insert into taxi values('" + plate + "', '" + agency + "', '" + model + "', '" + membership + "', '" + end + "')";
        stmt.executeUpdate(sql);
    }

    //show
    void show_address() throws SQLException {
        String sql = "select * from address";
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Address List", JOptionPane.INFORMATION_MESSAGE);
        while (rs.next()) {
            int co = rs.getInt("address_code");
            String ci = rs.getString("city");
            String st = rs.getString("street_name");
            // print the results
            System.out.format("%s, %s, %s\n", co, ci, st);
        }
    }

    void show_agency() throws SQLException {
        String sql = "select * from agency";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
        //  JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Agency List", JOptionPane.INFORMATION_MESSAGE);

    }

    void show_customer() throws SQLException {
        String sql = "select * from customer";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Customer List", JOptionPane.INFORMATION_MESSAGE);
    }

    void show_driver() throws SQLException {
        String sql = "select * from driver";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Driver List", JOptionPane.INFORMATION_MESSAGE);
    }

    void show_order() throws SQLException {
        String sql = "select * from orders";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Order List", JOptionPane.INFORMATION_MESSAGE);
    }

    void show_price() throws SQLException {
        String sql = "select * from price";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Price List", JOptionPane.INFORMATION_MESSAGE);
    }

    void show_taxi() throws SQLException {
        String sql = "select * from taxi";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.executeQuery(sql);
        JTable table = new JTable(buildTableModel(rs));
//        JOptionPane.showMessageDialog(null, new JScrollPane(table));
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Taxi List", JOptionPane.INFORMATION_MESSAGE);
    }

    //update
    void update_address(int code, String city, String street) throws SQLException {
        String sql = "update address set city = '" + city + "', street_name = '" + street + "' where address_code='" + code + "'";
        stmt.executeUpdate(sql);
    }

    void update_agency(int id, String name, int addcode, String phone) throws SQLException {
        String sql = "update agency set agency_name = '" + name + "', address_code = '" + addcode + "', phone_no = '" + phone + "' where agency_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void update_customer(int id, String name, int addcode, String phone) throws SQLException {
        String sql = "update customer set name = '" + name + "', address_code = '" + addcode + "', phone_no = '" + phone + "' where customer_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void update_driver(int id, String plate, String name, String phone) throws SQLException {
        String sql = "update driver set number_plate = '" + plate + "', name = '" + name + "', phone_no = '" + phone + "' where driver_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void update_order(int id, int agency, int source, int dest, Date order_date, Time order_time, String status, int customer) throws SQLException {
        String sql = "update orders set agency_id = '" + agency + "', source_address_code = '" + source + "', destination_address_code = '" + dest + "', date_of_order = '" + order_date + "'"
                + ", time_of_order = '" + order_time + "', order_status = '" + status + "', customer_id = '" + customer + "' where order_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void update_price(int dest, int source, int agency, int price) throws SQLException {
        String sql = "update price set price_amount = '" + price + "' where destination_address_code='" + dest + "' and source_address_code='" + source + "' "
                + "and agency_id='" + agency + "'";
        stmt.executeUpdate(sql);
    }

    void update_taxi(String plate, int agency, String model, Date membership, Date end) throws SQLException {
        String sql = "update taxi set agency_id = '" + agency + "', model = '" + model + "', date_of_membership = '" + membership + "', end_of_membership = '" + end + "' where number_plate='" + plate + "'";
        stmt.executeUpdate(sql);
        /*  Date mdate = new SimpleDateFormat("yyyy-MM-dd").parse(memberdate);
         Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

         PreparedStatement p = con.prepareStatement("insert into JsonData (last_crawl_date) values(?))");
         p.setDate(1, sqlDate);*/
    }

    //delete
    void delete_address(int code) throws SQLException {
        String sql = "delete from address where address_code='" + code + "'";
        stmt.executeUpdate(sql);
    }

    void delete_agency(int id) throws SQLException {
        String sql = "delete from agency where agency_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void delete_customer(int id) throws SQLException {
        String sql = "delete from customer where customer_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void delete_driver(int id) throws SQLException {
        String sql = "delete from driver where driver_id ='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void delete_order(int id) throws SQLException {
        String sql = "delete from orders where order_id='" + id + "'";
        stmt.executeUpdate(sql);
    }

    void delete_price(int dest, int source, int agency, int price) throws SQLException {
        String sql = "delete from price where destination_address_code='" + dest + "' and source_address_code='" + source + "' and agency_id='" + agency + "'";
        stmt.executeUpdate(sql);
    }

    void delete_taxi(String plate) throws SQLException {
        String sql = "delete from taxi where number_plate='" + plate + "'";
        stmt.executeUpdate(sql);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}

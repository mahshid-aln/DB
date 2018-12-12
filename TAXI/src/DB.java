/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.awt.FlowLayout;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author Mahshid
 */
public class DB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      JFrame f=new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
      f.getContentPane().add(new mainFrame());
      f.setSize(800, 280);
      f.setLocation(800, 500);
      f.setTitle("Tables");
      f.setVisible(true);
    }
    
}

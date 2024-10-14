/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DataConnect {
    public static Connection connect(){
        Connection conn = null;
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://KIENVIP;databaseName=demodb", "sa", "sa");
        } catch (Exception ex) {
            Logger.getLogger(DataConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}

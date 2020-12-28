/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author winPC
 */
public class DB {
    private static String db = "jdbc:sqlite:Elec_Lib.db";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    
    public static void connect() {
        try {
            connection = DriverManager.getConnection(db);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }
    
    public static ResultSet createStat(String query) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return rs;
    }
    
    public static boolean shift() {
        try {
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    public static String getString(String str) {
        try {
            return rs.getString(str);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static boolean getBoolean(String str) {
        try {
            return rs.getBoolean(str);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
    
    public static void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
}

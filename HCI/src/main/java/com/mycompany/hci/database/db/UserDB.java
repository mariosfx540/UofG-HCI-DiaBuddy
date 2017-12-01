package com.mycompany.hci.database.db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.hci.database.model.User;
import com.mycompany.hci.CSD3139DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDB {

    /**
     * Get user
     *
     * @param userName
     * @return
     * @throws ClassNotFoundException
     */
    public static List<User> getUsers() throws ClassNotFoundException {
        List<User> users = new ArrayList<>();

        try {
            try (Connection con = CSD3139DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM user;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true) {
                    User user = new User();
                    user.setUserID(res.getInt("userID"));
                    user.setFull_name(res.getString("full_name"));
                    user.setEmail(res.getString("email"));
                    user.setPassword(res.getString("password"));
                    user.setGender(res.getString("gender"));
                    user.setGlucose(res.getDouble("glucose"));
                    user.setTo_carb_ratio(res.getDouble("to_carb_ratio"));
                    user.setCorrection_factor(res.getDouble("correction_factor"));
                    user.setNormal_bg(res.getDouble("normal_bg"));
                    users.add(user);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    /**
     * Get user
     *
     * @param email
     * @return
     * @throws ClassNotFoundException
     */
    public static User getUser(String email) throws ClassNotFoundException {
        User user = new User();
        try {
            try (Connection con = CSD3139DB.getConnection();
                Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM user ")
                        .append(" WHERE ")
                        .append(" EMAIL = ").append("'").append(email).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                if (res.next() == true) {
                    user.setUserID(res.getInt("userID"));
                    user.setFull_name(res.getString("full_name"));
                    user.setEmail(res.getString("email"));
                    user.setPassword(res.getString("password"));
                    user.setGender(res.getString("gender"));
                    user.setGlucose(res.getDouble("glucose"));
                    user.setTo_carb_ratio(res.getDouble("to_carb_ratio"));
                    user.setCorrection_factor(res.getDouble("correction_factor"));
                    user.setNormal_bg(res.getDouble("normal_bg"));
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    
    
    /**
     * Establish a database connection and add the member in the database.
     *
     * @param user
     * @throws ClassNotFoundException
     */
    public static void addUser(User user) throws ClassNotFoundException {
        try {
            try (Connection con = CSD3139DB.getConnection();
                Statement stmt = con.createStatement()) {
                
                StringBuilder insQuery = new StringBuilder();

                insQuery.append("INSERT INTO ")
                        .append(" user (FULL_NAME, EMAIL, PASSWORD, GENDER, TO_CARB_RATIO, CORRECTION_FACTOR, NORMAL_BG) ")
                        .append(" VALUES (")
                        .append("'").append(user.getFull_name()).append("',")
                        .append("'").append(user.getEmail()).append("',")
                        .append("'").append(user.getPassword()).append("',")
                        .append("'").append(user.getGender()).append("',")
                        .append("'").append(user.getTo_carb_ratio()).append("',")
                        .append("'").append(user.getCorrection_factor()).append("',")
                        .append("'").append(user.getNormal_bg()).append("');");

                stmt.executeUpdate(insQuery.toString());
                System.out.println("#DB: The member was successfully added in the database.");

                // Close connection
                stmt.close();
                con.close();

            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    public static boolean checkValidEmail(String email) throws ClassNotFoundException {
        boolean valid = true;
        try {
            try (Connection con = CSD3139DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM user ")
                        .append(" WHERE ")
                        .append(" EMAIL = ").append("'").append(email).append("';");

                stmt.execute(insQuery.toString());
                if (stmt.getResultSet().next() == true) {
                    System.out.println("#DB: The member alreadyExists");
                    valid = false;
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valid;
    }
}

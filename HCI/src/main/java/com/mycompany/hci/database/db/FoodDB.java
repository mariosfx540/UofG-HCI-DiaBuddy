/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hci.database.db;

import com.mycompany.hci.database.model.*;
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

/**
 *
 * @author USER
 */
public class FoodDB {
    
    /**
     * Get foods
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Food> getFoods() throws ClassNotFoundException {
        List<Food> foods = new ArrayList<>();

        try {
            try (Connection con = CSD3139DB.getConnection();
                    Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM foods;");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();

                while (res.next() == true) {
                    Food food = new Food();
                    food.setFoodID(res.getInt("foodID"));
                    food.setFood_name(res.getString("food_name"));
                    food.setCalories(res.getDouble("calories"));
                    food.setCarbs(res.getDouble("carbs"));
                    foods.add(food);
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return foods;
    }
    
    
    /**
     * Get food
     *
     * @param food_name
     * @return
     * @throws ClassNotFoundException
     */
    public static Food getFood(String food_name) throws ClassNotFoundException {
        Food food = new Food();
        try {
            try (Connection con = CSD3139DB.getConnection();
                Statement stmt = con.createStatement()) {

                StringBuilder insQuery = new StringBuilder();

                insQuery.append("SELECT * FROM foods ")
                        .append(" WHERE ")
                        .append(" FOOD_NAME = ").append("'").append(food_name).append("';");

                stmt.execute(insQuery.toString());

                ResultSet res = stmt.getResultSet();
                
                System.out.println(food_name + " THORE DAME");

                if (res.next() == true) {
                    System.out.println("matched");
                    food.setFoodID(res.getInt("foodID"));
                    food.setFood_name(res.getString("food_name"));
                    food.setCalories(res.getDouble("calories"));
                    food.setCarbs(res.getDouble("carbs"));
                }else{
                    System.out.println("not matched");
                }

                // Close connection
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return food;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hci.database.model;

/**
 *
 * @author USER
 */
public class Food {
    private int foodID;
    private String food_name;
    private double calories;
    private double carbs;

    public Food() {
        this.food_name = "";
        this.calories = 0;
        this.carbs = 0;
    }

    public Food(String food_name, double calories, double carbs) {
        this.food_name = food_name;
        this.calories = calories;
        this.carbs = carbs;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return "Food{" + "foodID=" + foodID + ", food_name=" + food_name + ", calories=" + calories + ", carbs=" + carbs + '}';
    }
    
    
    
}

package com.mycompany.hci.database.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class User {
    
    private int userID;    // (unique)
    private String full_name;
    private String email;       // (unique)
    private String password;    
    private String gender;
    private double glucose;
    private double to_carb_ratio;
    private double correction_factor;
    private double normal_bg;

    public User() {
        this.full_name = "";
        this.email = "";
        this.password = "";
        this.gender = "";
        this.glucose = 0;
        this.to_carb_ratio =0;
        this.correction_factor = 0;
        this.normal_bg = 0;
    }
    
    public User(String full_name, String email, String password, String gender, double to_carb_ratio, double correction_factor, double normal_bg) {
        //this.userID = userID;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.glucose = 120;
        this.to_carb_ratio = to_carb_ratio;
        this.correction_factor = correction_factor;
        this.normal_bg = normal_bg;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getGlucose() {
        return glucose;
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public double getTo_carb_ratio() {
        return to_carb_ratio;
    }

    public void setTo_carb_ratio(double to_carb_ratio) {
        this.to_carb_ratio = to_carb_ratio;
    }

    public double getCorrection_factor() {
        return correction_factor;
    }

    public void setCorrection_factor(double correction_factor) {
        this.correction_factor = correction_factor;
    }

    public double getNormal_bg() {
        return normal_bg;
    }

    public void setNormal_bg(double normal_bg) {
        this.normal_bg = normal_bg;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", full_name=" + full_name + ", email=" + email + ", password=" + password + ", gender=" + gender + ", glucose=" + glucose + ", to_carb_ratio=" + to_carb_ratio + ", correction_factor=" + correction_factor + ", normal_bg=" + normal_bg + '}';
    }
    
    
}

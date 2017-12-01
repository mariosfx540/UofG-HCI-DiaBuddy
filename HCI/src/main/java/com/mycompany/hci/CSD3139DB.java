package com.mycompany.hci;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CSD3139DB {

    private static final String URL = "jdbc:mysql://eu-cdbr-azure-west-b.cloudapp.net";     //83.212.108.178   - localhost
    private static final String DATABASE = "acsm_93e6d5b6c72ee18";
    private static final int PORT = 3306;
    private static final String UNAME = "b32256cf5b24ac";                     //csd3139   - root
    private static final String PASSWD = "f8df924a";
    
    

    /**
     * Attempts to establish a database connection Using mysql
       *
     * @return a connection to the database
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL + ":" + PORT + "/" + DATABASE
                + "?zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",                 UNAME, PASSWD);
    }

}

package com.mycompany.hci;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.hci.database.model.User;
import com.mycompany.hci.database.model.*;
import com.mycompany.hci.database.db.*;

import java.text.DecimalFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String method = request.getParameter("method");
        switch (method) {
            case "SignUp": {
                signUp(request, response);
            }
            case "LogIn": {
                logIn(request, response);
                break;
            }
            case "logout": {
                HttpSession session = request.getSession();
                if(session != null && session.getAttribute("user") != null)
                    session.invalidate();
                break;
            }
            case "checkSession": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if(user != null) {
                    redirect("/signed_in.jsp", request, response);
                }
                break;
            }
            case "Search": {
                search(request, response);
            }
            default:
                break;
        }
        
    }
    
    
    private void signUp(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        for (User element : UserDB.getUsers()) {
            if( element.getEmail().equals(request.getParameter("email")) == true){
                response.sendError(470);
                return;
            }
        }
        
        User newUser;
        newUser = new User(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"), 
                request.getParameter("gender"), Double.parseDouble(request.getParameter("to_carb_ratio")),
                Double.parseDouble(request.getParameter("correction_factor")), Double.parseDouble(request.getParameter("normal_bg")) );
        
        UserDB.addUser(newUser);
        
        User user = UserDB.getUser(request.getParameter("email"));
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        redirect("/signed_in.jsp", request, response);
        //response.sendRedirect("/signed_in.jsp");
        
    }
    
    
    private void logIn(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        User user = UserDB.getUser(request.getParameter("login_email"));
        if( user.getEmail().equals("") ){
           response.sendError(480);
           return;
        }

        if( user.getPassword().equals(request.getParameter("login_password")) == false  ){
           response.sendError(490);
           return;
        }
        
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        redirect("/signed_in.jsp", request, response);
        
        
    }
    
     private void search(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
         HttpSession session = request.getSession();
         User user = (User) session.getAttribute("user");
         ArrayList<Food> foods = new ArrayList<>();
         
         String lines = request.getParameter("lines");
         String[] parts = lines.split(",");
         
         
        for(String item: parts){
            item = item.substring(1);
            foods.add(FoodDB.getFood(item));
            //System.out.println("!!!!!!!!!!");
            //System.out.println(item);
        }
         
         
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        out.println("<table class=\"table\">");    
        out.println("<thead>\n" +
                    "<tr>\n" +
                    "<th>Food Name</th>\n" +
                    "<th>Calories</th>\n" +
                    "<th>Carbohydrates</th>\n" +
                    "<th>After Meal Inuline</th>\n" +
                    "</tr>\n" +
                    "</thead>");
        out.println("<tbody>");

        for(Food item: foods){
            System.out.println("????????????");
            
            double step1 = item.getCarbs() / user.getTo_carb_ratio();
            double step2 = (user.getGlucose() - user.getNormal_bg()) / user.getCorrection_factor();
            
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            double insuline = Math.round((step1 + step2) * 100.0) / 100.0;
            
            out.println("<tr>\n" +
                        "<td>" + item.getFood_name() + "</td>\n" +
                        "<td>" + item.getCalories() + "</td>\n" +
                        "<td>" + item.getCarbs() + "</td>\n" +
                        "<td>" + insuline + " units" + "</td>\n" +
                        "</tr>");
         }
         
         out.println("</tbody>\n" + "</table>");
         
         }
         
         
         
     }
    
    
    public void redirect(String url, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}

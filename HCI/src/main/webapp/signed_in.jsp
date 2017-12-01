<%-- 
    Document   : signed_in
    Author     : Andreas Kontopoulos
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.hci.database.db.UserDB"%>
<%@page import="com.mycompany.hci.database.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="assets/js/api.js"></script>

    
</head>




<body>
    
    
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header"><a class="navbar-brand" href="index.html"><i class="glyphicon glyphicon-phone"></i>DiaBuddy</a>
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                </div>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li role="presentation"><a href="myProfile.html">My Profile</a></li>
                        <li role="presentation"><a href="#footer">Contact us</a></li>
                        <li role="presentation"><a onclick="logOutPOST();" href="index.html" style="">Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
   
       
    <div id="main">
        
        <% 
            User user = (User) session.getAttribute("user");
            int userID = user.getUserID();
            String fullName = user.getFull_name();
            String email = user.getEmail();
            String password = user.getPassword();
            String gender = user.getGender();
            double glucose = user.getGlucose();
            double to_carb_ratio = user.getTo_carb_ratio();
            double correction_factor = user.getCorrection_factor();
            double normal_bg = user.getNormal_bg();
        %>
        
        
        <div id="sessionUser" style="display: none"><%= email %></div>

        
        <div class="camera">
            
            <div id="myProgress" style="display: none;">
                <div id="myBar"></div>
            </div>
            
            <div id="profile_pic_2" style="margin-bottom: 10px; display: block;"> 
                <img id="cam-icon" src="assets/img/cam.png" alt="Mountain View">
            </div>
            
            <div id="profile_pic" style="margin-bottom: 10px; display: none;"> 
                <video id="video" width="100%" height="80%" autoplay></video>
            </div>
            
            <div class="btn-holder" id="stream" role="group">
                    <button id="right-bt" onclick="
                        document.getElementById('profile_pic').style.display='block';
                        document.getElementById('results').style.display='none';
                        document.getElementById('profile_pic_2').style.display='none';
                        takePhoto();"><h4 style="color: white; font-family: Helvetica, sans-serif">Camera</h4>
                     </button>
                   <button id="left-bt">
                       <h4 style="color: white; font-family: Helvetica, sans-serif" onclick="move()">Capture</h4>
                    </button>
            </div>

        </div>
        
        <div id="results"></div>
        <button id="back" style="display: none;" onclick="
                        document.getElementById('back').style.display='none';
                        document.getElementById('results').style.display='none';
                        document.getElementById('stream').style.display='block';
                        document.getElementById('profile_pic').style.display='block';
                        document.getElementById('myProgress').style.display='none';
                        takePhoto();
                        "><h4 style="color: white; font-family: Helvetica, sans-serif">Keep Scanning</h4>
                     </button>
        
        
            
        
                        
<!--        <div id="myModal" class="modal">
            <div id="set_picture">
                <button id="pic_file" style="margin-left:2%; width:46.5%; height:80px;" onclick="
                                  document.getElementById('photo_form').style.display='block';
                                  document.getElementById('profile_pic').style.display='none';">
                                  <h1>Choose File<h1>
                </button>

                <button style="margin-left: 2%; margin-right :2%; width:46.5%; height:80px;" onclick="
                                 document.getElementById('photo_form').style.display='none';
                                 document.getElementById('profile_pic').style.display='block';
                                 takePhoto();">
                                 <h1>Camera</h1>
                </button>
            </div>
            
            <fieldset id='photo_form' style="display: none; margin-top: 2%; margin-bottom: 1%; margin-left:22%; margin-right:22%; width: 50%;">
                <h3 style="text-align: center;">Upload Photo to Database</h3>
                <input id='photo' style=" margin: 0; width: 100%;" type="file" name="photo" size="50"/><br><br>
                <input type='button' value='Save' onclick='UploadImageAjaxPOST();'><br>
            </fieldset>


            


            <span id="modal_close" class="close" onclick="document.getElementById('myModal').style.display='none';  
                                                          document.getElementById('pic_file').click(); 
                                                          document.getElementById('photo_form').style.display='none'; 
                                                          ">CLOSE
            </span>
        </div>-->
        
        
        
        
    
        <div class="column" id="camera_pic" style="display: none;"> 
            <canvas id="canvas"></canvas>
        </div>
        

        
        
            
        
    </div>
        
    <div class="footer-basic" id="footer">
        <footer>
            <div class="social"><a href="#"><i class="icon ion-social-instagram"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-facebook"></i></a></div>
            <ul class="list-inline">
                <li><a href="#">Home</a></li>
                <li><a href="#features">Services</a></li>
                <li><a href="#">Terms</a></li>
                <li><a href="#">Privacy Policy</a></li>
            </ul>
            <p class="copyright">Company Name © 2017</p>
        </footer>
    </div>
    <section class="testimonials"></section>
        
</body>
</html>

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function SignUpAjaxPOST(){
    if ( document.getElementById("name").checkValidity() === false ){
        alert("Please fill Full Name field correct!\n\nFirst name must be between 3-60 latin characters");
        document.getElementById("name").focus();
        return;
    }
    
    if ( document.getElementById("email").checkValidity() === false ){
        alert("Please fill Email field correct!\n\nEmail must have the following format: characters@characters.domain (Characters followed by one @ and then characters with at least one '.')");
        document.getElementById("email").focus();
        return;
    }
    
    if ( document.getElementById("password").checkValidity() === false ){
        alert("Please fill Password field!");
        document.getElementById("password").focus();
        return;
    } 
    
    if ( document.getElementById("password").value !== document.getElementById("re_password").value ){
        alert("Your password and confirmation password do not match.");
        document.getElementById("re_password").focus();
        return; 
    } 
    
    if ( document.getElementById("to_carb_ratio").checkValidity() === false ){
        alert("Please fill how many grams of carbohydrate needed per unit of insulin");
        document.getElementById("to_carb_ratio").focus();
        return;
    }
    
    if ( document.getElementById("correction_factor").checkValidity() === false ){
        alert("Please fill how much glucose level drops per unit of insulin");
        document.getElementById("correction_factor").focus();
        return;
    }
    
     if ( document.getElementById("normal_bg").checkValidity() === false ){
        alert("Please fill your normal glucose level in mg/dl");
        document.getElementById("normal_bg").focus();
        return;
    }
    
    
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var re_password = document.getElementById("re_password").value;
    var to_carb_ratio = document.getElementById('to_carb_ratio').value;
    var correction_factor = document.getElementById('correction_factor').value;
    var normal_bg = document.getElementById('normal_bg').value;
    
    if (document.getElementById('male').checked) {
        var gender = document.getElementById('male').value;
    }
    else if (document.getElementById('female').checked) {
        var gender = document.getElementById('female').value;
    }
    else{
        var gender = "";
    }
    
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "MainServlet?method=SignUp", true);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Your Account Has Been Created!");
            document.getElementById('ajaxContent').innerHTML = xhr.responseText;
            window.scrollTo(0,0);
        } 
        else {
            if (xhr.status === 470){
                alert("This email already exists. Please try another one.");
                document.getElementById("email").focus();
                return;
            }
        }
    };
    
    
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send('name=' + name + '&email=' + email + '&password=' + password + '&re_password=' + re_password + 
             '&to_carb_ratio=' + to_carb_ratio + '&correction_factor=' + correction_factor + '&normal_bg=' + normal_bg + 
             '&gender=' + gender);
    
    
}

function logInAjaxPOST() {
    /*
    if ( document.getElementById("login_email").checkValidity() === false ){
        alert("Please fill Username field! \n\nUsername must have at least 8 charachers");
        document.getElementById("login_email").focus();
        return;
    }
    
    if ( document.getElementById("login_password").checkValidity() === false ){
        alert("Please fill Password field correct!\n\nPassword must be between 6-10 characters and include at least one letter, one number and one symbol");
        document.getElementById("login_password").focus();
        return;
    } 
    */
    var login_email = document.getElementById("login_email").value;
    var login_password = document.getElementById("login_password").value;
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "MainServlet?method=LogIn", true);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('ajaxContent').innerHTML = xhr.responseText;
        } 
        else {
            if (xhr.status === 480){
                alert("Email is wrong. Please try again.");
                document.getElementById("login_email").focus();
                return;
            }
            if (xhr.status === 490){
                alert("Password is wrong. Please try again.");
                document.getElementById("login_password").focus();
                return;
            }
        }
    };
    
    
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send('login_email=' + login_email + '&login_password=' + login_password);   
}


function logOutPOST() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "MainServlet?method=logout", true);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.assign('index.html');
        } 
    };
    
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}


function checkSessionAjaxPOST() {   
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "MainServlet?method=checkSession", true);            
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if(xhr.responseText !== "") {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText; 
            } 
            else{
               //window.location.assign('index.html');
            }
        } 
    };
    
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();  
}


function takePhoto() {
    
    var capbutton = document.getElementById('left-bt');
    var canvas = document.getElementById('canvas');
    var video = document.getElementById('video');
    
    var constraints = { video: { facingMode: ("user", "environment") } };
    
    
    var gumStream;
    
    navigator.getUserMedia(constraints,
            function(stream) {
                gumStream = stream;
                video.srcObject = gumStream;
            },
            function(error) {
                console.log('getUserMedia() error', error);
        });

        //Take the photo and then close camera and stream.
        capbutton.addEventListener('click', function (ev) {
            takepicture();
            vidOff();
            ev.preventDefault();
        }, false);
//        
//        pic_file.addEventListener('click', function (ev) {
//            vidOff();
//            ev.preventDefault();
//        }, false);
//       
//
        var context;
        function takepicture() {
            context = canvas.getContext('2d');
            var width = 160, height = 185;
            if (width && height) {
                canvas.width = width;
                canvas.height = height;
                context.drawImage(video, 0, 0, width, height);
                
                var dataURL = canvas.toDataURL();
                //var blob = dataURItoBlob(dataURL);
                
                UploadCameraImageAjaxPOST(dataURL);
                
                
                /*
                var span = document.createElement('span');
                span.innerHTML = ['<img id="imge" src="', dataURL ,'">'].join('');
                var li = document.getElementById('canvas');
                li.insertBefore(span, li.childNodes[0]);
                */
               
                document.getElementById("profile_pic").style.display = "none";
                //document.getElementById("camera_pic").style.display = "block";
            }
        }
                
                
                
            
        
        
        function vidOff() {
            //video.pause();
            //video.src = "";
            gumStream.getTracks()[0].stop();
            console.log("Vid off");
        }
         		
}

function dataURItoBlob(dataURI) {
    // convert base64/URLEncoded data component to raw binary data held in a string
    var byteString;
    if (dataURI.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(dataURI.split(',')[1]);
    else
        byteString = unescape(dataURI.split(',')[1]);

    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

    // write the bytes of the string to a typed array
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ia], {type:mimeString});
}


function UploadCameraImageAjaxPOST(dataURL) {
    
    //Prepare form data
    var formData = new FormData();
    formData.append("base64Image", dataURL);
    //formData.append("url", "https://photos-6.dropbox.com/t/2/AAAB91TEb9Gc6B5FL6L2SsORM_-OQb8l_G2FqL90vOuX7Q/12/528285296/png/32x32/3/1511924400/0/2/menu.png/EPDFip0EGAUgAigC/iyWOOFz4yyDctwEDsFc7stwOsRsBHQnFtZMflm7g3Kc?dl=0&size=1600x1200&size_mode=3" ); //"https://i.pinimg.com/originals/88/87/0e/88870eff7fd7b9b2706cc9dd00d99e3b.jpg"
    formData.append("language"   , "eng");
    formData.append("apikey"  , "0a9583ee6988957");
    formData.append("isOverlayRequired", true);
    
    //Send OCR Parsing request asynchronously
    jQuery.ajax({
        url: 'https://api.ocr.space/parse/image',
        data: formData,
        dataType: 'json',
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
        success: function (ocrParsedResult) {
            //Get the parsed results, exit code and error message and details
            var parsedResults = ocrParsedResult["ParsedResults"];
            var ocrExitCode = ocrParsedResult["OCRExitCode"];
            var isErroredOnProcessing = ocrParsedResult["IsErroredOnProcessing"];
            var errorMessage = ocrParsedResult["ErrorMessage"];
            var errorDetails = ocrParsedResult["ErrorDetails"];
            var processingTimeInMilliseconds = ocrParsedResult["ProcessingTimeInMilliseconds"];
            
            //If we have got parsed results, then loop over the results to do something
            if (parsedResults !== null) {
                //Loop through the parsed results
                $.each(parsedResults, function (index, value) {
                    var exitCode = value["FileParseExitCode"];
                    var parsedText = value["ParsedText"];
                    var errorMessage = value["ParsedTextFileName"];
                    var errorDetails = value["ErrorDetails"];

                    var textOverlay = value["TextOverlay"];
                    var pageText = '';
                    switch (+exitCode) {
                        case 1:
                        pageText = parsedText;
                        break;
                        case 0:
                        case -10:
                        case -20:
                        case -30:
                        case -99:
                        default:
                        pageText += "Error: " + errorMessage;
                        break;
                    }
                    
                    var lines = "";
                    $.each(textOverlay["Lines"], function (index, value) {
                        //alert("Grammi");
                        var words = value["Words"];
                        
                        $.each(words, function (index, value) {
                            var res = value["WordText"];
                            //alert(res);
                            
                            lines = lines + " " + res;
                        });
                        
                        lines = lines + ",";
                        
                        
                        //LOOP THROUGH THE LINES AND GET WORDS TO DISPLAY ON TOP OF THE IMAGE AS OVERLAY

                    });
                    
                    alert(lines);
                    searchForFood(lines);

                    //YOUR CODE HERE
                
                });
            }
        }
    });
    
}

function move() {
    var elem = document.getElementById("myBar");
    
    document.getElementById("myProgress").style.display='block';
    
    var width = 1;
    var id = setInterval(frame, 20);
    function frame() {
        if (width >= 100) {
            clearInterval(id);
        } else {
            width++; 
            elem.style.width = width + '%'; 
        }
    }
}


function searchForFood(lines){
    
    alert(lines);
    
    var userEmail = document.getElementById('sessionUser').innerHTML;
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "MainServlet?method=Search", true);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('results').innerHTML = xhr.responseText;
             document.getElementById('stream').style.display='none';
            document.getElementById('profile_pic').style.display='none';
            document.getElementById('back').style.display='block';
            document.getElementById('results').style.display='block';
        } 
        else {
            
        }
    };
    
    
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send('lines=' + lines);
    
}


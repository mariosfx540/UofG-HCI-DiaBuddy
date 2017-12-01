
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    </head>
    <body>
        
        
         <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header"><a class="navbar-brand" href="index.html"><i class="glyphicon glyphicon-phone"></i>DiaBuddy</a>
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                </div>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li role="presentation"><a href="loginForm.html">Scan</a></li>
                        <li role="presentation"><a onclick="logOutPOST();" href="index.html" style="">Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-4 well well-sm">
            
                    <form action="#" method="post" class="form" role="form">
                    <div class="row">
                        <div class="col-xs-6 col-md-6">
                            <input class="form-control" name="firstname" placeholder="First Name" type="text"
                                required autofocus />
                        </div>
                        <div class="col-xs-6 col-md-6">
                            <input class="form-control" name="lastname" placeholder="Last Name" type="text" required />
                        </div>
                    </div>
                    <input class="form-control" name="youremail" placeholder="Your Email" type="email" />
                    <input class="form-control" name="reenteremail" placeholder="Re-enter Email" type="email" />
                    <input class="form-control" name="password" placeholder="New Password" type="password" />
                    
                    <div class="row">
                        <div class="col-xs-4 col-md-4">
                            <select class="form-control">
                                <option value="Month">Month</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-4">
                            <select class="form-control">
                                <option value="Day">Day</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-4">
                            <select class="form-control">
                                <option value="Year">Year</option>
                            </select>
                        </div>
                    </div>
                    <label class="radio-inline">
                        <input type="radio" name="sex" id="inlineCheckbox1" value="male" />
                        Male
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" id="inlineCheckbox2" value="female" />
                        Female
                    </label>
                    <br />
                    <br />
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign up</button>
                    </form>
                </div>
            </div>
        </div>
        
        
    </body>
</html>

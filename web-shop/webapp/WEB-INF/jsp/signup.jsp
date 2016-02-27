<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sign Up | TOY-Shop</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head>
<!--/head-->

<body>

<header id="header"><!--header-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="index.jsp"><img src="images/home/lego-logo.jpg" alt="home" width="139"
                                                 hight="39"/></a>
                    </div>
                    <div class="btn-group pull-right">
                        <div class="btn-group">
                            <u:language/>
                        </div>
                    </div>
                </div>

                <!--LOGIN FORM-->
                <u:login/>
            </div>
            <!--/header-middle-->


            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse"
                                        data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="index.jsp" class="active">Home</a></li>
                                    <li><a href="/shop.do">Products</a></li>
                                    <li><a href="/signupPage.do">Sign Up</a></li>
                                    <li><a href="/cart.do">Cart</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="search_box pull-right">
                                <input type="text" placeholder="Search"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/header-bottom-->
</header>
<!--/header-->

<section id="form"><!--form-->
    <div class="container">
        <div class="row">

            <div class="col-sm-4 col-signup">
                <div class="signup-form"><!--sign up form-->
                    <h2>New User Signup!</h2>

                    <form name="signup" method="POST" action="/signup.do"
                          onsubmit="return validateSignUpForm(this)" enctype="multipart/form-data">
                        <fieldset
                                title="Name must be from 3 to 16 only latin characters, underscores, hyphens, digits. ">
                            <input type="text" name="name" id="signupName"
                                   placeholder="Name" value="${param.name}" <u:valid fieldName="name"/> />
                        </fieldset>

                        <fieldset title="Sure name must be from 2 to 100 only latin characters. ">
                            <input type="text" name="surname" id="signupSurname"
                                   placeholder="Surname" value="${param.surname}" <u:valid fieldName="surname"/> />
                        </fieldset>

                        <fieldset title="Email must be unique. May be this email is already taken.">
                            <input type="email" name="email"
                                   placeholder="Email Address" value="${param.email}" <u:valid fieldName="email"/> />
                        </fieldset>

                        <fieldset
                                title="Password must begin from one latin character and be from 3 to 14 characters, . ">
                            <input type="password" name="pass" id="signupPass"
                                   placeholder="Password" value="${param.pass}" <u:valid fieldName="password"/> />
                        </fieldset>

                        <span>
								<input type="checkbox" class="checkbox" name="toysInfo"/>
								Inform me about new toys
                        </span>
                        <br/>
                        <span>
								<input type="checkbox" class="checkbox" name="discountsInfo"/>
								Inform me about discounts
                        </span>
                        <input type="file" name="avatar" size="60"/>
                        <br/>

                        <input type="text" placeholder="captcha code" name="user_code"
                                <u:valid fieldName="user_code"/>/>
                        <br/>
                        <%--Ectual Capthca --%>
                        <u:captcha/>

                        <button type="submit" class="btn btn-default">Signup</button>
                    </form>
                </div>
                <!--/sign up form-->
            </div>
        </div>
    </div>
</section>
<!--/form-->

<script src="js/validation.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.scrollUp.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.prettyPhoto.js" type="text/javascript"></script>
<script src="js/main.js" type="text/javascript"></script>
<script src="js/jquery.validation.js" type="text/javascript"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<html lang="en">
<u:head title="Sign Up" />
<!--/head-->
<body>
<header id="header"><!--header-->
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home"><img src="images/home/lego-logo.jpg" alt="home" width="139"
                                             hight="39"/></a>
                    </div>
                    <%@include file="../jspf/lang.jspf" %>
                </div>

                <!--LOGIN FORM-->
                <%@include file="../jspf/login.jspf" %>
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
                                    <li><a href="/home" class="active">Home</a></li>
                                    <li><a href="/shop">Products</a></li>
                                    <li><a href="/signup">Sign Up</a></li>
                                    <li><a href="/cart">Cart</a></li>
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

                    <form name="signup" method="POST" action="/signup"
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

                        <!--Actual captcha-->
                        <img src="/captcha"/>

                        <button type="submit" class="btn btn-default">Signup</button>
                    </form>
                </div>
                <!--/sign up form-->
            </div>
        </div>
    </div>
</section>
<!--/form-->
<%@include file="../jspf/scripts.jspf" %>
</body>
</html>

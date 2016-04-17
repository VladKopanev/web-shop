<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | ToyShop</title>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap" />
    <spring:url value="/resources/css/font-awesome.min.css" var="fontAwesome" />
    <spring:url value="/resources/css/price-range.css" var="priceRange" />
    <spring:url value="/resources/css/animate.css" var="animate" />
    <spring:url value="/resources/css/responsive.css" var="responsive" />
    <spring:url value="/resources/css/html5shiv.js" var="html5shiv" />

    <link href="${bootstrap}" rel="stylesheet">
    <link href="${fontAwesome}" rel="stylesheet">
    <link href="${priceRange}" rel="stylesheet">
    <link href="${animate}" rel="stylesheet">
    <link href="${mainCss}" rel="stylesheet">
    <link href="${responsive}" rel="stylesheet">
    <script src="${html5shiv}"></script>
</head>
<!--/head-->

<body>
<header id="header"><!--header-->
    <spring:url value="/resources/images/home/html5shiv.js" var="html5shiv" />

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home"><img src="*" alt="home" width="139"
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

<u:cartItems/>
<spring:url value="/resources/js/jquery.js" var="jquery" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/jquery.scrollUp.min.js" var="scrollUpJs" />
<spring:url value="/resources/js/jquery.prettyPhoto.js" var="prettyJs"/>
<spring:url value="/resources/js/main.js" var="mainJs" />

<script src="${jquery}"></script>
<script src="${bootstrapJs}"></script>
<script src="${scrollUpJs}"></script>
<script src="${prettyJs}"></script>
<script src="${mainJs}"></script>
</body>
</html>
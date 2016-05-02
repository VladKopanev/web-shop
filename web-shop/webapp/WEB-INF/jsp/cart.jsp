<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<u:head title="Cart" />
<!--/head-->
<body>
<header id="header"><!--header-->
    <spring:url value="/resources/images/home/html5shiv.js" var="html5shiv"/>
    <spring:url value="resources/images/book.gif" var="book"/>
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home"><img src="${book}" alt="home" width="139"
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

<%@include file="../jspf/cartItems.jspf" %>

<%@include file="../jspf/scripts.jspf" %>
</body>
</html>
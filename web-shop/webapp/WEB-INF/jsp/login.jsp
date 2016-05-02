<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<u:head title="Login" />
<!--/head-->
<body>
<header id="header"><!--header-->
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home"><img src="<c:url value="resources/images/book.gif" />" alt="home" width="139"
                                             height="39"/></a>
                    </div>
                    <%@include file="../jspf/lang.jspf" %>
                </div>
            </div>
        </div>
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
                            <li><a href="<c:url value="/home"/>" class="active">Home</a></li>
                            <li><a href="<c:url value="/shop"/>">Products</a></li>
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
    <!--/header-bottom-->
</header>
<!--/header-->

<section id="form"><!--form-->
    <div class="container">
        <div class="row">

            <!--LOGIN FORM-->
            <%@include file="../jspf/login.jspf" %>
        </div>
    </div>
</section>
<!--/form-->
<%@include file="../jspf/scripts.jspf" %>
</body>
</html>

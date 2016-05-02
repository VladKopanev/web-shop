<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<html>
<u:head title="Message" />
<body>
<header id="header"><!--header-->
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home"><img src="<c:url value="resources/images/book.gif" />" alt="home" width="139"
                                             hight="39"/></a>
                    </div>
                    <%@include file="../jspf/lang.jspf" %>
                </div>
                <!--LOGIN FORM-->
                <%@include file="../jspf/login.jspf" %>
            </div>

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
</header>
<!--/header-bottom-->
<div class="container">
    <div class="row">
        <h3>${message}</h3>
    </div>
</div>
<%@include file="../jspf/scripts.jspf" %>
</body>
</html>

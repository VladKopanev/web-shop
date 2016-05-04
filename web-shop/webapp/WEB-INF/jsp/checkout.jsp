<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<u:head title="Checkout" />
<body>
<header id="header"><!--header-->
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home"><img src="<spring:url value="resources/images/book.gif" />" alt="home" width="139"
                                             height="39"/></a>
                    </div>
                    <%@include file="../jspf/lang.jspf" %>
                </div>
            </div><!--/header-middle-->

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
            </div><!--/header-bottom-->
        </div>
    </div>
</header><!--/header-->

<section>
    <div class="container">
        <div class="row">
            <c:set var="itemCount" scope="request" value="${userCart.getCountOfItems(item)}"/>
            <c:choose>
                <c:when test="${not empty param.stage and param.stage == 2}">
                    <h2>Summary</h2>
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li>Items count ${userCart.getCount()}</li>
                            <li>Amount $ ${userCart.getSumOfItems()}</li>
                            <li>
                                <form method="POST" action="/order">
                                    <input type="hidden" name="shipType" value="${param.shipType}"/>
                                    <input type="hidden" name="shipAddress" value="${param.shipAddress}"/>
                                    <input type="submit" value="Order"/>
                                </form>
                            </li>
                            <li></li>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav collapse navbar-collapse">
                        <form id="checkoutForm" method="POST" action="/cart/checkout">
                            <h3>Select shipping type and address</h3>
                            <select class="dropdown" name="shipType" form="checkoutForm">
                                <a href="#">Select shipping type<i class="fa fa-angle-down"></i>
                                    <option value="SELF_PICKUP">Self pickup</option>
                                    <option value="MAIL">Mail</option>
                                    <option value="DELIVERY">Delivery</option>
                                </a>
                            </select>
                            <p><input form="checkoutForm" type="text" placeholder="Ship Address" name="shipAddress"/>
                            <p>
                            <p><input form="checkoutForm" type="submit" value="Next step"/></p>
                            <input type="hidden" name="stage" value="2"/>
                        </form>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>
<%@include file="../jspf/scripts.jspf" %>
</body>
</html>
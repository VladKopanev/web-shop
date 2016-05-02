<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<u:head title="Shop"/>
<!--/head-->
<body>
<header id="header"><!--header-->
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/home">
                            <img src="<c:url value="resources/images/book.gif" />" alt="home" height="39" width="139"/>
                        </a>
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
                                    <li><a href="<c:url value="/home"/>" class="active">Home</a></li>
                                    <li><a href="<c:url value="/shop"/>">Products</a></li>
                                    <li><a href="/signup">Sign Up</a></li>
                                    <li><a href="/cart">Cart</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="search_box pull-right">
                                <input type="text" name="itemName" form="filterForm" placeholder="Search"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/header-bottom-->
        </div>
    </div>
</header>
<!--/header-->
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Category</h2>

                    <div class="panel-group category-products" id="accordian"><!--category-productsr-->

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#">Plush</a>
                                    <input class="pull-right" type="checkbox" form="filterForm" name="category"
                                           value="Plush"
                                           <c:if test="${fn:containsIgnoreCase(shopRequest, 'Plush')}">checked</c:if>/>
                                </h4>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#">Constructors</a>
                                    <input class="pull-right" type="checkbox" form="filterForm" name="category"
                                           value="Constructors"
                                           <c:if test="${fn:containsIgnoreCase(shopRequest, 'Constructors')}">checked</c:if>/>
                                </h4>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#">Boardgames</a>
                                    <input class="pull-right" type="checkbox" form="filterForm" name="category"
                                           value="Boardgames"
                                           <c:if test="${fn:containsIgnoreCase(shopRequest, 'Boardgames')}">checked</c:if>/>
                                </h4>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#">Dolls</a>
                                    <input class="pull-right" type="checkbox" form="filterForm" name="category"
                                           value="Dolls"
                                           <c:if test="${fn:containsIgnoreCase(shopRequest, 'Dolls')}">checked</c:if>/>
                                </h4>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="#">Other</a>
                                    <input class="pull-right" type="checkbox" form="filterForm" name="category"
                                           value="Other"
                                           <c:if test="${fn:containsIgnoreCase(shopRequest, 'Other')}">checked</c:if>/>
                                </h4>
                            </div>
                        </div>
                    </div>
                    <!--/category-products-->

                    <div class="brands_products"><!--brands_products-->
                        <h2>Brands</h2>

                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#"> <span class="pull-right">
                                <input type="checkbox" form="filterForm" name="brand" value="lego"
                                       <c:if test="${fn:containsIgnoreCase(shopRequest, 'lego')}">checked</c:if>/>
                                </span>Lego </a></li>

                                <li><a href="#"> <span class="pull-right">
                                <input type="checkbox" form="filterForm" name="brand" value="hasbro"
                                       <c:if test="${fn:containsIgnoreCase(shopRequest, 'hasbro')}">checked</c:if>/>
                                </span>Hasbro </a></li>

                                <li><a href="#"> <span class="pull-right">
                                <input type="checkbox" form="filterForm" name="brand" value="zoch"
                                       <c:if test="${fn:containsIgnoreCase(shopRequest, 'zoch')}">checked</c:if>/>
                                </span>Zoch </a></li>

                                <li><a href="#"> <span class="pull-right">
                                <input type="checkbox" form="filterForm" name="brand" value="button blue"
                                       <c:if test="${fn:containsIgnoreCase(shopRequest, 'button blue')}">checked</c:if>/>
                                </span>Button Blue </a></li>
                            </ul>
                        </div>
                        <div>
                            <form action="/shop" method="POST" id="filterForm">
                                <input type="number" name="recordsPerPage" placeholder="records per page">
                                <input type="submit">
                                <input type="hidden" name="priceRange" id="priceRange" value="${param.priceRange}">
                                <!--/price-range-->
                            </form>
                            <div class="price-range"><!--price-range-->
                                <h2>Price Range</h2>

                                <div class="well text-center">
                                    <input type="text" class="span2" value="" data-slider-min="0"
                                           data-slider-max="600" data-slider-step="5"
                                           data-slider-value="<u:slider-val/>"
                                           id="sl2"
                                           name="priceRange"><br/>
                                    <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--/brands_products-->

                    <div class="brands_products">
                        <h2>Sort</h2>

                        <div class="brands-name">
                            <table>
                                <tr>
                                    <td>
                                    <span>Name
                                <button onclick="setHiddenSortInput('ProductName', 'asc')">▲</button>
                                <button onclick="setHiddenSortInput('ProductName', 'desc')">▼</button>
                                <input type="hidden" id="ProductName" name="sort" form="filterForm"/>
                                </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    <span id="sortPriceSpan">Price
                                <button onclick="setHiddenSortInput('ProductPrice', 'asc')">▲</button>
                                <button onclick="setHiddenSortInput('ProductPrice', 'desc')">▼</button>
                                <input type="hidden" id="ProductPrice" name="sort" form="filterForm"/>
                                </span>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 padding-right">

                <div class="features_items"><!--features_items-->
                    <h2 class="title text-center">Features Items</h2>

                    <!--PAGINATION-->
                    <%@include file="../jspf/pagination.jspf" %>

                    <!--ITEMS-->
                    <%@include file="../jspf/shopItems.jspf" %>

                </div>
                <!--features_items-->
            </div>
        </div>
    </div>
</section>

<%@include file="../jspf/scripts.jspf" %>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | ToyShop</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
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
</head><!--/head-->

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
		</div><!--/header-middle-->

		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
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
		</div><!--/header-bottom-->
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
                              <form method="POST" action="/order.do">
                                <input type="hidden" name="shipType" value="${param.shipType}"/>
                                <input type="hidden" name="shipAddress" value="${param.shipAddress}"/>
                                <input type="submit" value="Order"/>
                              </form>
                              </li>
                              <li> </li>
                          </ul>
                     </div>
                </c:when>
                <c:otherwise>
                <ul class="nav navbar-nav collapse navbar-collapse">
                    <form id="checkoutForm" method="GET" action="/cart/checkoutPage">
                        <h3>Select shipping type and address</h3>
            	        <select class="dropdown" name="shipType" form="checkoutForm">
            	            <a href="#">Select shipping type<i class="fa fa-angle-down"></i>
            	                <option value="SELF_PICKUP">Self pickup</option>
            	                <option value="MAIL">Mail</option>
            	                <option value="DELIVERY">Delivery</option>
            	            </a>
            	        </select>
            	  <p><input form="checkoutForm" type="text" placeholder="Ship Address" name="shipAddress" /><p>
            	  <p><input form="checkoutForm" type="submit" value="Next step"/></p>
            	    <input type="hidden" name="stage" value="2"/>
                    </form>
                </ul>
                </c:otherwise>
            </c:choose>

            </div>
         </div>
   </section>
</body>
</html>
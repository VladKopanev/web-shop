<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>${title} | Book Shop</title>
    <spring:url value="/resources/css/main.css" var="mainCss"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrap"/>
    <spring:url value="/resources/css/font-awesome.min.css" var="fontAwesome"/>
    <spring:url value="/resources/css/price-range.css" var="priceRange"/>
    <spring:url value="/resources/css/animate.css" var="animate"/>
    <spring:url value="/resources/css/responsive.css" var="responsive"/>

    <link href="${bootstrap}" rel="stylesheet">
    <link href="${fontAwesome}" rel="stylesheet">
    <link href="${priceRange}" rel="stylesheet">
    <link href="${animate}" rel="stylesheet">
    <link href="${mainCss}" rel="stylesheet">
    <link href="${responsive}" rel="stylesheet">

</head>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/locales.js"></script>
 <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
	LANG
	<span class="caret"></span>
</button>
<ul class="dropdown-menu">
	<c:forEach items="${supportedLang}" var="current">
		<li><a id="${current}" onclick="langChange(${current})"href="#">${current}</a></li>
	</c:forEach>
</ul>
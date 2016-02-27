<%@ tag body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

[<c:choose>
    <c:when test="${not empty param.priceRange}">${param.priceRange}
    </c:when>
    <c:otherwise>
        250,450
    </c:otherwise>
</c:choose>]
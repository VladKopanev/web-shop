<%@ tag body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
<ul class="pagination">
    <%--<li><a href="">&raquo;</a></li>--%>
    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="active"><a href="">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/shop.do?page=${i}&${shopRequest}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    </div>
</ul>
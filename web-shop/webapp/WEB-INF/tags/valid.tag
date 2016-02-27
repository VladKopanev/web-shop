<%@ attribute name="fieldName" required="true" %>
<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty nonValidFields && nonValidFields.contains(fieldName)}">style="border: 1px solid #ff0000"</c:if>
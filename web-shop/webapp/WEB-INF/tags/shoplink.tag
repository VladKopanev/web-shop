<%@ attribute name="name" required="false" type="java.lang.String" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
/shop.do?${shopRequest}<c:if test="${fn:containsIgnoreCase(shopRequest, name)}">
    ${fn:substringBefore(shopRequest, name)} ${name} = ${fn:substringAfter(shopRequest, name)}
</c:if>
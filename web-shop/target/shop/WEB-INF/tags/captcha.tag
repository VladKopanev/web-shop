<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${applicationScope.savingMode == hidden}">
    <input type="hidden" value="${requestScope.captchaId}" name="captchaId" />
</c:if>
<img src="/captcha.do?captchaId=${requestScope.captchaId}"/>
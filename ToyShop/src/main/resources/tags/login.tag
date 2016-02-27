<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-8">
     <div class="login-form pull-right">
<c:choose>
            <c:when test="${empty user}">
                    <c:if test="${not empty nonValidLoginInfo}">
                         <span>Login or password is incorrect</span>
                     </c:if>
                <form id="loginForm" method="POST" action="/login.do">
                    <ul class="nav navbar-nav">
                        <li><input type="text" placeholder="Email" name="email" id="loginEmail"/></li>
                        <li><input type="password" placeholder="Password" name="pass" id="loginPass"/></li>
                        <li><input type="submit" value="submit"/></li>
                    </ul>
                </form>
            </c:when>
            <c:otherwise>
                <div>
                    <img src="/avatar.do" alt="avatar" height="50" width="50"/>
                    ${user.name}<a href="/logout.do"> logout</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

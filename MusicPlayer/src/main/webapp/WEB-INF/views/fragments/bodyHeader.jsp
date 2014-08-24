<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page session="false" %>
<!DOCTYPE>
<html>
<head>
<meta  charset="UTF-8">
<link  rel="stylesheet" href="<spring:url value="/resources/css/musicplayer.css"/>" />
</head>
<body>
 
<spring:url value="/resources/images/music-header.jpg" var="head" />

 <c:url value="/logout_music_store" var="logoutUrl"/>
       
       <form action="${logoutUrl}" method="post" id="logoutForm">
          <input  type="hidden" name="${_csrf.parameterName}"
                  value="${_csrf.token}" />
       </form>
       
        <script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

<div id="head">
  <div class="image"><img alt="error" src="${head}"></div>
</div> 
<div id="navbar">
    <div class="link">
            <security:authorize access="isAuthenticated()" >
                <security:authentication property="principal.username" var="current_user"/>
                <div class="current_user"><a href='<spring:url  value='/settings_account_user/${current_user}/profile'/>' >${current_user}</a></div>
                <div class="logout"><a href="javascript:formSubmit()" >Logout</a></div>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <div class="login"> <a href='<spring:url value="/login" /> '>Login</a></div>
                <div class="registration"><a href='<spring:url value="/registration" /> '>Registration</a></div>
            </security:authorize>
    </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href='<spring:url value="/resources/css/settingsStyle.css" />' />
</head>
<body>


 <security:authorize access="isAuthenticated()">
 
    <security:authentication property="principal.username" var="current_username" />
    
    <spring:url value="/settings_account_user/${current_username}/profile" var="settings_account_user_profil"/>
    <spring:url value='/settings_account_user/${current_username}/password' var="actionPassword" />
    <spring:url value='/settings_account_user/${current_username}/email' var="actionEmail" />
     <spring:url value='/settings_account_user/${current_username}/userInformation' var="actionUserInformation" />
    
    <div id="settings">
       <ul>
          <li>
                 <a href="${settings_account_user_profil}">Profil</a>
           </li>
          <li>
                <a href="${actionPassword}">Haslo</a>
           </li>
          <li>
             <a href="${actionEmail}">Email</a>
          </li>
       </ul>
       <ul>
          <li>
             <a  href="${actionUserInformation}">UserInformation</a>
          </li>
       </ul>
    </div>
    
 </security:authorize>

</body>
</html>
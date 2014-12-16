<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>
<link rel="stylesheet" href="<spring:url value="/resources/css/formbody.css" />" />
</head>
<body>
<security:authorize access="permitAll">
   <div id="formlogin">
     <div>Authorization</div>
     
     <c:url value="/login_music_store" var="login_music_store" /> 
     
    <form action="${login_music_store}" method="POST">
        <table>
           <tr>
              <td><label for="login">Login:</label></td>
              <td><input type="text" name="login"/></td>
           </tr>
           <tr>
              <td><label for="password">Password</label></td>
              <td><input type="password" name="password"/></td>
           </tr>
           <tr>
              <td><input type="submit" value="submit" /></td>
           </tr>           
        </table>
        
        <input type="hidden" name="${_csrf.parameterName}"
                  value="${_csrf.token}" />
    </form>
   </div>
</security:authorize>
</body>
</html>
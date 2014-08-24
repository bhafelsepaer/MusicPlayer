<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <link rel="stylesheet" href="<spring:url value="/resources/css/settingsFormStyle.css"/>" /> 
  <link rel="stylesheet" href='<spring:url value="/resources/css/errorHandler.css" /> '>
<script src="<spring:url value="/resources/javascript/errorHandler.js" /> " ></script>
</head>
<body>
 
 <jsp:include page="/WEB-INF/views/fragments/bodyHeader.jsp" />
 <jsp:include page="/WEB-INF/views/fragments/settingsFragment.jsp" />
 <spring:url value="/resources/images/warning-icon.png" var="warnign_icon" />
 
   <security:authorize access="isAuthenticated()">
   <security:authentication property="principal.username" var="current_login"/>
    
   <spring:url value='/settings_account_user/${current_login}/profile' var="actionProfile" />
    
      <div id="formBody">
         <form:form action="${actionProfile}" modelAttribute="user" method="POST">
            <table class="settingsTable">
               <tr>
                  <td><form:label path="login" cssClass="Login">${user_setting.login}</form:label></td>
               </tr>
               <tr>
                  <td><form:label path="firstName">FirstName:</form:label></td>
                  <td><form:input path="firstName" value="${user_setting.firstName}"/></td>
                  <td>
                      <div class="wrapper"><img alt="error" src="${warnign_icon}">
                          <form:errors path="firstName" cssClass="error" />
                     </div>                  
               </tr>
               <tr>
                  <td><form:label path="address">Address:</form:label></td>
                  <td><form:input path="address" value="${user_setting.address}"/></td>
                  <td>
                    <div class="wrapper"><img alt="error" src="${warnign_icon}">
                          <form:errors path="address" cssClass="error"/>
                    </div>   
                  </td>
               </tr>
            </table>
           
              <input type="submit" value="submit" name="submit"/>
         
         </form:form>
      </div>
   
   </security:authorize>

</body>
</html>
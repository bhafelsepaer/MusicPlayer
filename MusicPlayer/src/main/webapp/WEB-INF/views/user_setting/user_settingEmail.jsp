<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Email Page</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="<spring:url value="/resources/javascript/errorHandler.js" /> " ></script>
  <link rel="stylesheet" href="<spring:url value="/resources/css/settingsFormStyle.css"/>" /> 
  <link rel="stylesheet" href='<spring:url value="/resources/css/errorHandler.css" /> '>
</head>
<body>

 <jsp:include page="/WEB-INF/views/fragments/bodyHeader.jsp" />
 <jsp:include page="/WEB-INF/views/fragments/settingsFragment.jsp" />
 <spring:url value="/resources/images/warning-icon.png" var="warnign_icon" />
  
   <security:authorize access="isAuthenticated()">
   
   <security:authentication property="principal.username" var="current_login"/>

    <spring:url value='/settings_account_user/${current_login}/email' var="actionEmail" />
    
    <div id="formBody">
       <form:form action="${actionEmail}" modelAttribute="user" method="POST">
            <table class="settingsTable">
               <tr>
                  <td><form:label path="email">Email:</form:label></td>
                  <td><form:input path="email"/></td>
                  <td>
                    <div class="wrapper"><img alt="" src="${warnign_icon}">
                          <form:errors path="email" cssClass="error" />
                    </div>  
                  </td>
               </tr>
               <tr>
                  <td><form:label path="confirmEmail">ConfirmEmail:</form:label></td>
                  <td><form:input path="confirmEmail" /></td>
                  <td>
                     <div class="wrapper"><img alt="" src="${warnign_icon}">
                          <form:errors path="confirmEmail" cssClass="error"/>
                          <form:errors path="" cssClass="error"/>
                     </div>  
                  </td>
               </tr>
               <tr>
                   <td><form:label path="password">Password:</form:label></td>
                   <td><form:password path="password"/></td>
                   <td>
                      <div class="wrapper"><img alt="" src="${warnign_icon}">
                          <form:errors path="password" cssClass="error" />
                          <div class="error">${wrongCurrentPassword}</div>
                      </div>  
                    </td>               
            </table>
              
              <input type="submit" value="submit" name="submit"/>
         
         </form:form>
    </div>  
   </security:authorize>
</body>
</html>
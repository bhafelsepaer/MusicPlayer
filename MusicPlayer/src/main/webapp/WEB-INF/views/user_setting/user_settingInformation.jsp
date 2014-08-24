<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
 
    <spring:url value='/settings_account_user/${current_login}/userInformation' var="actionuserInformation" />
    
    <div id="formBody">
       <form:form action="${actionuserInformation}" modelAttribute="userInformation" method="POST">
            <table class="settingsTable">
                <tr>
                    <td><form:label path="age">AGE:</form:label></td>
                    <td><form:input path="age"/></td>
                </tr>  
                <tr>
                    <td><form:label path="surname">Surname:</form:label></td>
                    <td><form:input path="surname"/></td>
               </tr>
               <tr>
                   <td>
                      <form:select path="interest" multiple="multiple">
                        <form:options items="${interestList}"/>
                     </form:select>
                  </td>
              </table>           
              
              <input type="submit" value="submit" name="submit"/>
         
         </form:form>
    
    </div>  
 </security:authorize>
   
</body>
</html>
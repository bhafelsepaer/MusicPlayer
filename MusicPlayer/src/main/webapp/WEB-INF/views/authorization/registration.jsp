<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>

<%@ page session="true" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <link rel="stylesheet" href='<spring:url value="/resources/css/registration.css"/> '>
  <link rel="stylesheet" href='<spring:url value="/resources/css/errorHandler.css" /> '>
  <script src="<spring:url value="/resources/javascript/errorHandler.js" /> " ></script>
<title>Registration</title>
</head>
<body>
   
   <!-- Warning image to display errors validation -->
   <spring:url value="/resources/images/warning-icon.png" var="warnign_icon" />
   
   <core:url value="/registration" var="registration"/>
   
  <div id="headRegistration">
  
      <div class="titleRegistration">Registration:</div>
           
      <form:form action="${registration}" modelAttribute="user"
                 method="POST">
                 
         <table class="registrationTable"> 
             <tr>
                <td><form:label path="login">Login:</form:label></td>  
                <td><form:input path="login" /></td>
                <td>
                   <div class="wrapper"><img alt="error" src="${warnign_icon}">
                     <form:errors path="login" cssClass="error"/> 
                   </div>
                </td>
             </tr>    
             <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:input path="password"/></td>
                <td>
                  <div class="wrapper"><img alt="error" src="${warnign_icon}">
                     <form:errors path="password" cssClass="error"/> 
                 </div>
                </td>
             </tr>
             <tr>
                 <td><form:label path="confirmPassword">ConfirmPassword</form:label></td>
                 <td><form:input path="confirmPassword"/></td>
                 <td>
                   <div class="wrapper"><img alt="" src="${warnign_icon}">
                     <form:errors path="confirmPassword" cssClass="error"/> 
                      <form:errors path="" cssClass="error"/> 
                   </div>
                </td>
             <tr>
                <td><form:label path="firstName">First Name:</form:label></td>
                <td><form:input path="firstName" /></td>
               <td>
                   <div class="wrapper"><img alt="" src="${warnign_icon}">
                     <form:errors path="firstName" cssClass="error"/> 
                   </div>
                </td>
             </tr>
             <tr>
                <td><form:label path="address">Address:</form:label></td>
                <td><form:input path="address" /></td>
                <td>
                   <div class="wrapper"><img alt="" src="${warnign_icon}">
                     <form:errors path="address" cssClass="error"/> 
                   </div>
                </td>
             </tr>
             <tr>
                <td><form:label path="email">Email:</form:label></td>
                <td><form:input path="email" /></td>  
                <td>
                   <div class="wrapper"><img alt="" src="${warnign_icon}">
                     <form:errors path="email" cssClass="error"/> 
                   </div>
                </td>
             </tr>
             <tr>
                <td><input type="submit" name="submit" value="submit" /></td>
                <td><input type="radio" name="strategyPattern" value="mailActivation" checked="checked">Email Activation<br></td>  
                <td><input type="radio" name="strategyPattern" value="smsActivation">Sms Activation<br></td>
             </tr>
      </table>
      </form:form>

  </div>
 
</body>
</html>
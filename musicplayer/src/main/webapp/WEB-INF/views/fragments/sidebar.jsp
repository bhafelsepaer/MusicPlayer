<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="<spring:url value="/resources/audio_player/pink.flag/jplayer.pink.flag.css"/>"/>
<link rel="stylesheet" href="<spring:url value="/resources/css/design_sidebar.css"/>"/>
  
</head>
<body>

<security:authorize access="permitAll">

<div id="panel">
     <div id="title">
       Panel     
     </div>
     <div id="content">
     <jsp:include page="/WEB-INF/views/AudioPlayer.jsp"/>
   </div>
</div>

</security:authorize>
</body>
</html>
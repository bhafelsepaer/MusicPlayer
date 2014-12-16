<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<spring:url value="/resources/css/desing_showSong.css"/>" />

</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
<jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />

<security:authorize access="permitAll">
<table>
    <tr>
       <td><a href="<spring:url value="/" />" >Main Page</a></td>
    </tr>
    <c:forEach items="${loadedSong}" var="song" varStatus="xd">
     <tr>
        <td><a href="/musicplayer/playSong?song_id=${song.song_id}">${song.name}</a></td>
    </tr>
    </c:forEach>
</table>

</security:authorize>
</body>
</html>
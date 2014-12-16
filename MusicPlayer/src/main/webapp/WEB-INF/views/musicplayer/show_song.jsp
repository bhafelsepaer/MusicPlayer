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

<style>

html, body {
   width: 100%;
}
table {
  border-collapse: collapse;
  margin: 0 auto;
}

td {
  padding: 15px;
  background-color: red;
}

table, tr, td {
  border: 1px solid black;
}

</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
<jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />


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
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<table>
    <c:forEach items="${loadedSongByGenre}" var="song">
     <tr>
        <td>${song.song_id}</td>
        <td><a href="/musicplayer/playSong?song_id=${song.song_id}">${song.name}</a></td>
    </tr>
    </c:forEach>
</table>

	<jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
</body>
</html>
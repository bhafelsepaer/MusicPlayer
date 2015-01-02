<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="core" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Song In Playlist</title>
 <jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />
 <jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
 <jsp:include page="/WEB-INF/views/fragments/settingsFragment.jsp" />
</head>
<body>
<security:authorize access="isAuthenticated()">
<core:forEach items="${SongInPlaylist}" var="song">
  <a href="/musicplayer/playSong?song_id=${song.song_id}">${song.name}</a></br>
</core:forEach>
</security:authorize>
</body>
</html>
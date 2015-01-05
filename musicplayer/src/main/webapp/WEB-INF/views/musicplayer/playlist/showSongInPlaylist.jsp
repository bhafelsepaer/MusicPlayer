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
 <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script>
   $(document).ready(function(){
       $(".deleteSong").on("click", function(){
    	    var song_id = $(this).data("song-id");
    	    
       });
   });
 </script>
</head>
<body>
<security:authorize access="isAuthenticated()">
<core:forEach items="${songInPlaylist}" var="song">
  <a href="/musicplayer/playlist/playSongFromPlaylist?song_id=${song.song_id}">${song.name}</a>
  <button class="deleteSong" type="button" data-song-id=${song.song_id}>Delete</button></br>
</core:forEach>
</security:authorize>
</body>
</html>
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
 <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
 
 <script>
  $(document).ready(function(){
	  
	  $(".addSong").on("click", function(){
		  var position = $(this).position();
		  $(this).parent('.playlistbutton').find(".playlistId")
		                                   .css({right: position.left - 420,
		                                	     top: position.top - 2});
		  
		   $(this).parent('.playlistbutton').find(".playlistId").toggle();
     });
	
	  $(".playlistId").change(function(){
		 var playlistToSaveSong = $(this).find('option:selected').val();
		 var a = $.parseJSON(playlistToSaveSong)
		 
		 $.get("http://localhost:8080/musicplayer/saveSongToPlaylist",
				 {song_id : a.song, playlist_id : a.playlist},
				 function(response){
				 });
		 $(this).toggle();
		 
	  });
	 
  });
 </script>
 
 <style>
 
 .playlistId {
     display: none;
     position: absolute;
     color: red;
 }
 </style>

</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
<jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />

<security:authorize access="permitAll">
<div id="songContent">
<table>
    <tr>
       <td><a href="<spring:url value="/" />" >Main Page</a></td>
    </tr>
    <c:forEach items="${loadedSong}" var="song" varStatus="xd">
     <tr>
        <td><a href="/musicplayer/playSong?song_id=${song.song_id}">${song.name}</a></td>
      <security:authorize access="isAuthenticated()">
        <td>
          <div class="playlistbutton">
             <button type="submit" class="addSong">AddSong</button>
               <div class='playlistId'>
                <select class="choicePlaylist">
                <option selected disabled class="selectedPlaylist">Choose playlist</option>
			       <c:forEach items='${loadedPlaylist}' var='actualPlaylist'>
			         <option value='{"playlist": "${actualPlaylist.playlist_id}", "song":"${song.song_id}"}'>${actualPlaylist.name}</option>
			        </c:forEach>
			     </select>
	          </div>
          </div>
       </td>
     </security:authorize>
    </tr>
    </c:forEach>
</table>
</div>

</security:authorize>
</body>
</html>
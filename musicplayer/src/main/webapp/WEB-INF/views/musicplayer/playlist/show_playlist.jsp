<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <link rel="stylesheet" href="<spring:url value="/resources/css/settingsFormStyle.css"/>" /> 
<style>
.actual_playlist  div {
   vertical-align: top;
   display:inline-block;
} 

.playlist_edit {
  visibility: hidden;
}
</style>
<script>
 $(document).ready(function(){
	var isClicked = false;
	
	$('.activePlaylistEdit').on('click', function(){
		if(!isClicked){
		   var playlist_name = $(this).parent('.actual_playlist').
		                           find('.playlist_name').text();
		   $(this).parent('.actual_playlist').
           find('.playlist_name').hide();
		   $(this).parent().find('.playlist_edit').css("visibility", "visible"); 
		   $(this).hide();
		   isClicked = true;
	   }
	});
	
	$('.playlist_edit').dblclick(function(){
		$(this).css("visibility", "hidden");
		$(this).parent('.actual_playlist').find('.playlist_name').show();
		$(this).parent('.actual_playlist').find('.activePlaylistEdit').show();
		isClicked = false;
	});
	
  $(".submitButton").on("click", function(){
	  $('.playlist_edit').css("visibility", "hidden");
	  $(this).parent('.actual_playlist').find('.playlist_name').show();
	  $(this).parent('.actual_playlist').find('.activePlaylistEdit').show();
	  isClicked = false;
	     
  });
  
   $('#newPlaylistForm').submit(function(e){
	   if(!isClicked){
	     $.get('http://localhost:8080/musicplayer/playlist/${user_id}/save_playlist',
	         $(this).serialize(), function(response) {
               $("#playlist").load("http://localhost:8080/musicplayer/playlist/${user_id}");
	    });
	  }
	   e.preventDefault();
  }); 
   
   $('.updatePlaylistForm').submit(function(e){
	  $.get('http://localhost:8080/musicplayer/playlist/update',
	       $(this).serialize(),function(response){
		  $("#playlist").load("http://localhost:8080/musicplayer/playlist/${user_id}");
	  });
	  e.preventDefault();
   });
  
   $('.playlist_delete a').click(function(e){
	   e.preventDefault();
	   var playlist_id = $(this).data('playlist-id');
	   alert(playlist_id);
	   var playlist_name = $(this).data('playlist-name');
	   $.get('http://localhost:8080/musicplayer/playlist/delete',
			 {playlist_id : playlist_id, playlist_name: playlist_name}, function(response){
		$("#playlist").load("http://localhost:8080/musicplayer/playlist/${user_id}");
	   });
   });
}); 
</script>
</head>
<body>
<security:authorize access="isAuthenticated()"> 
 <div id="playlist">
   <jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />
   <jsp:include page="/WEB-INF/views/fragments/settingsFragment.jsp" />
  
   <core:forEach items="${playlist}" var="actualPlaylist">
     <div class="actual_playlist">
      <div class="playlist_name"><a href="<spring:url value="/playlist/showPlaylistSong/${actualPlaylist.playlist_id}" />" >
                                   ${actualPlaylist.name}</a></div> 
      <button class="activePlaylistEdit">Edit</button>
       <div class="playlist_edit">
          <form  class="updatePlaylistForm">
                <input type="hidden" value="${actualPlaylist.playlist_id}" name="playlist_id">
                <input type="text" value="${actualPlaylist.name}" name="playlist_name">
                <input type="submit" value="update" class="submitButton">
                <div class="playlist_delete">
                  <a href="http://localhost:8080/musicplayer/playlist/delete" 
                     data-playlist-id="${actualPlaylist.playlist_id}" data-playlist-name="${actualPlaylist.name}">
                     delete</a></div>
          </form>
       </div>
    </div>
 </core:forEach>
 
     <form  id="newPlaylistForm">
        New Playlist :<input type="text" name="playlistName">
       <input type="submit" value="submit"> 
    </form>
   </div>
</security:authorize>
</body>
</html>
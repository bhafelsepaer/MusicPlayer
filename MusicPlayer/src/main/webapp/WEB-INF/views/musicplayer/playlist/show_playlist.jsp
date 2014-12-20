<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<style>

.actual_playlist  div {
   vertical-align: top;
   display:inline-block;
} 

.playlist_update {
  visibility: hidden;
}
</style>
<script>
 $(document).ready(function(){
	$('.playlist_name').dblclick(function(){
		var playList_name = $(this).text();
		$(this).hide();
		$(this).parent().find('.playlist_update').css("visibility", "visible"); 
	});
	
  $(".submitButton").on("click", function(){
	  $('.playlist_update').css("visibility", "hidden");
	  $('.playlist_name').show();
	     
  });
  
   $('#newPlaylistForm').submit(function(e){
	$.get('http://localhost:8080/musicplayer/playlist/${user_id}/save_playlist',
	     $(this).serialize(), function(response) {
	    	alert(response);
	    	location.reload();       //Refresh page
                                    //Refresh Playlist without refrash whole page will be added later
	});
	e.preventDefault();
  }); 
   
   $('.updatePlaylistForm').submit(function(e){
	  $.get('http://localhost:8080/musicplayer/playlist/update',
	       $(this).serialize(),function(response){
		  alert(response);
		  location.reload();
	  });
	  e.preventDefault();
   });
  
   $('.playlist_delete a').click(function(e){
	   e.preventDefault();
	   var playlist_id = $(this).data('playlist-id');
	   var playlist_name = $(this).data('playlist-name');
	   alert(playlist_id + " and " + playlist_name);
	   $.get('http://localhost:8080/musicplayer/playlist/delete',
			 {playlist_id : playlist_id, playlist_name: playlist_name}, function(response){
		   alert(response);
		   location.reload();
	   });
   });
}); 
</script>
</head>
<body>

   <core:forEach items="${playlist}" var="actualPlaylist">
     <div class="actual_playlist">
      <div class="playlist_name">${actualPlaylist.name}</div> 
       <div class="playlist_update">
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
</body>
</html>
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

.playlist  div {
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
}); 
</script>
<core:url value="/playlist/${user_id}/save_playlist" var="save_playlist"/>
</head>
<body>

 <core:forEach items="${playlist}" var="actualPlaylist">
     <div class="playlist">
       <div class="playlist_name">${actualPlaylist.name}</div> 
       <div class="playlist_update">
          <form  action="<core:url value="/playlist/${actualPlaylist.playlist_id}/update" />" method="GET">
                <input type="text" value="${actualPlaylist.name}" name="playlist_name">
                <input type="submit" value="update" class="submitButton">
          </form>
       </div>
       <a href="<core:url value="/playlist/${actualPlaylist.playlist_id}/delete" />" >delete</a></br>
    </div>
 </core:forEach>
 
 <form action="${save_playlist}" method="GET">
 New Element :<input type="text" name="playlistName">
 <input type="submit" value="submit"> 
 </form>
 

</body>
</html>
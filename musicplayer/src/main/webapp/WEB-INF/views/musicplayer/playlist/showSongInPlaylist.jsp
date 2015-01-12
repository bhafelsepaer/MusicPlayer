<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="core" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Song In Playlist</title>
 <jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />
 <jsp:include page="/WEB-INF/views/fragments/settingsFragment.jsp" />
 <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 
<script>
   $(document).ready(function(){
	   
	    $(".activeSong a").on("contextmenu",function(e){
		    e.preventDefault();
		    $("#myMenu").appendTo($(this))
		       .css({top: e.pageY + "px", left: e.pageX + "px"}).show();
		    var song_id = $(this).data("song-id");
		    
		   $('.rateSong').unbind('click');
		   $('.rateSong').on("click", function(){
			  var rating = prompt("Please rate this song");
			   $("#myMenu").hide();
			   $.ajax({
				 type: "GET",
				 url: "http://localhost:8080/musicplayer/song/rateSongByUser",
				 data: {user_id : ${user_id}, song_id: song_id, ratingSong: rating},
				 success: function(response) {
					 alert(response);
				 }, error: function(jqXHR, textStatus, errorThrown) {
					 alert(jqXHR + " " + textStatus + " " +  errorThrown);
				 }
			  }); 
		   });
		   
		   $(".showRateUser").unbind('click');
		   $(".showRateUser").on("click", function(){
			   $("#myMenu").hide();
			   $.ajax({
				 type: "GET",
				 url: "http://localhost:8080/musicplayer/song/showRatingSongbyUser",
				 data: {user_id : ${user_id}, song_id: song_id},
				 success: function(response) {
					 alert(response);
				 }, error: function(jqXHR, textStatus, errorThrown) {
					 alert(jqXHR.responseText);
				 }
			   });
		   });
		   
		   $(".showAverageSongRating").unbind('click');
		      $(".showAverageSongRating").on("click", function(){
		    	 $("#myMenu").hide();
		    	 $.ajax({
		    		type: "GET",
		    		url: "http://localhost:8080/musicplayer/song/showAverageRatingSong",
		    		data: {song_id : song_id},
		    		success: function(response) {
		    			alert(response);
		    		}, error: function(jqXHR, textStatus, errorThrown) {
		    			alert(jqXHR.responseText);
		    		}
		    	 });
		      });
	     });
	   });
</script>

<style>
     form, .activeSong {
        display: inline;
     }
     
     #myMenu {
         display:none;
         z-index:1000;
         position: absolute;
         background-color:#C0C0C0;
         border: 1px solid black;
         padding: 2px;
     }
     
     #myMenu ul {
       list-style-type: none;
       padding: 0;
       margin: 0;
     }
     
</style>
</head>
<body>
<security:authorize access="isAuthenticated()">
 <jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
  
 <div id="myMenu">
    <ul>
      <li class="rateSong"><a href="#">Rating Song</a></li>
      <li class="showRateUser"><a href="#">Show Rating User</a></li>
      <li class="showAverageSongRating"><a href="#">Show Average Song</a></li>
   </ul>
</div>		

<core:forEach items="${songInPlaylist}" var="song">
    <div class="activeSong">
       <a href="/musicplayer/playlist/playSongFromPlaylist/${playlist_id}/${song.song_id}" 
       data-song-id = "${song.song_id}" >${song.name}</a>
    </div>
    <form action="<spring:url value="/playlist/deleteSongFromPlaylist/${playlist_id}/${song.song_id}"/> " method="get">
        <input type="submit" value="delete">
    </form></br>
</core:forEach>
</security:authorize>
</body>
</html>
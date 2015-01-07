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
 <link rel="stylesheet" href='<spring:url value="/resources/css/contextMenu/jquery.contextMenu.css"/>' />
 <script src='<spring:url value="/resources/javascript/contextMenu/jquery.contextMenu.js"/>'></script>
 
 
<script>
   $(document).ready(function(){
	   
	    $(".actualSong a").on("contextmenu",function(e){
		    e.preventDefault();
		    $("#myMenu").appendTo($(this))
		       .css({top: e.pageY + "px", left: e.pageX + "px"}).show();
		    var song_id = $(this).data("song-id");
		    
		   $(this).unbind().on("click", function(){
			   $("#myMenu").hide();
			   var rating = prompt("Please rate this song");
			   $.ajax({
				 type: "GET",
				 url: "http://localhost:8080/musicplayer/song/rateSongByUser",
				 data: {user_id : ${user_id}, song_id: song_id, ratingSong: rating},
				 success: function(response) {
					 alert(response);
				 }, error: function(jqXHR, textStatus, errorThrown) {
					 alert(textStatus);
				 }
			  }); 
		   });
	     });
	   });
</script>

<style>
     form, .actualSong {
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
     
     #inputRating {
        display: none;
        position:absolute;
        width:300px;
        z-index:15;
        top:50%;
        left:50%;
        margin:-100px 0 0 -150px;
        background-color: #C0C0C0;
        border: 1px solid black;
        padding 2px;
     }
     
</style>
</head>
<body>
<security:authorize access="isAuthenticated()">
 <jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
 
 <div id="myMenu">
    <ul>
			<li class="rateSong"><a href="#">Edit</a></li>
   </ul>
</div>		

<core:forEach items="${songInPlaylist}" var="song">
    <div class="actualSong">
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
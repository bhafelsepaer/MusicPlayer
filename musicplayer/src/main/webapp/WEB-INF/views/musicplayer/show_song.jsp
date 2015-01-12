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
		 var a = $.parseJSON(playlistToSaveSong);
		 
		 $.ajax({
			type: "GET",
			url: "http://localhost:8080/musicplayer/playlist/saveSongToPlaylist",
			data:  {song_id : a.song, playlist_id : a.playlist},
			success: function(response){
				alert(response);
			},
			error: function(xhr, ajaxOptions, thrownError){
				alert(xhr.responseText);
			}
		 });
		 $(this).find(".choicePlaylist").prop('selectedIndex', 0);
		 $(this).toggle();
	  });
	  
	  $(".activeSong a").on("contextmenu",function(e){
		    e.preventDefault();
		    $("#myMenu").appendTo($(this))
		       .css({top: e.pageY + "px", left: e.pageX + "px"}).show();
		    var song_id = $(this).data("song-id");
		 
	<security:authorize access="isAuthenticated()">
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
				 success: function(response){
					 alert(response);
				 }, error: function(jqXHR, textStatus, errorThrown) {
					 alert(jqXHR.responseText);
				 }
			   });
		   });
    </security:authorize>
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
     
 .playlistId {
     display: none;
     position: absolute;
     color: red;
 }
 </style>
 
</head>
<body>
<div id="sidebar">
   <jsp:include page="/WEB-INF/views/fragments/sidebar.jsp" />
</div>
<jsp:include page="/WEB-INF/views/fragments/mainPage.jsp" />

 <div id="myMenu">
    <ul>
    <security:authorize access="isAuthenticated()"> <li class="rateSong"><a href="#">Rating Song</a></li>
      <li class="showRateUser"><a href="#">Show Rating User</a></li>
      </security:authorize>
      <security:authorize access="permitAll">
              <li class="showAverageSongRating"><a href="#">Show Average Song</a></li>
      </security:authorize>
   </ul>
</div>		

<security:authorize access="permitAll">
<div id="songContent">
<table>
    <tr>
       <td><a href="<spring:url value="/" />" >Main Page</a></td>
    </tr>
    <c:forEach items="${loadedSong}" var="song" varStatus="xd">
     <tr>
        <td><div class="activeSong">
           <a href="/musicplayer/playSong?song-id=${song.song_id}"
           data-song-id=${song.song_id}>${song.name}</a>
       </div></td>
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
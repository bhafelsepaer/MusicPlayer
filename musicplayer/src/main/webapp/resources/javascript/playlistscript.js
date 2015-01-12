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
	   var playlist_name = $(this).data('playlist-name');
	   $.get('http://localhost:8080/musicplayer/playlist/delete',
			 {playlist_id : playlist_id, playlist_name: playlist_name}, function(response){
		$("#playlist").load("http://localhost:8080/musicplayer/playlist/${user_id}");
	   });
   });
}); 
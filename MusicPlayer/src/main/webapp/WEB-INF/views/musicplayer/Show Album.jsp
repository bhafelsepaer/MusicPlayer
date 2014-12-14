<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.linkMusic {
   width: 200px;
   height: 300px;
   margin: 10px;
   position: relative;
   display: inline-block;
}

.textCover {
  position: absolute;
  bottom: 0;
  left: 0;
  font-family: Helvetica Neue;
  color: white;
}

#headCoverAlbum_Elza_New_World {
    background-image: url(<c:url value='/resources/images/album_picture/byAlbum/Elza-NewWorld.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverAlbum_The_Fighters-Never_Surrender {
    background-image: url(<c:url value='/resources/images/album_picture/byAlbum/The_Fighters-Never_Surrender.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverAlbum_Pikachu-I_AM_FREE {
    background-image: url(<c:url value='/resources/images/album_picture/byAlbum/Pikachu-I_AM_FREE.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverAlbum_Stupid_Cow-Jingle_Jingle {
    background-image: url(<c:url value='/resources/images/album_picture/byAlbum/Stupid_Cow-Jingle_Jingle.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverAlbum_Baka-Triple_Baka {
    background-image: url(<c:url value='/resources/images/album_picture/byAlbum/Baka-Triple_Baka.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}
</style>

</head>
<body>

<div class="linkMusic">
   <a href="<spring:url value="/album/nightcore_album?album_name=Elza-New_World" />" rel="album">
      <div id="headCoverAlbum_Elza_New_World">
         <div class="textCover">
            <h5>New Album DJ ELZA from year 2014</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/album/nightcore_album?album_name=The_Fighters-Never_Surrender" />" rel="album">
      <div id="headCoverAlbum_The_Fighters-Never_Surrender">
         <div class="textCover">
            <h5>The Fighters Sing About A Fight</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/album/nightcore_album?album_name=Pikachu-I_AM_FREE" />" rel="album">
      <div id="headCoverAlbum_Pikachu-I_AM_FREE">
         <div class="textCover">
            <h5>Pikachu pika pika</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/album/nightcore_album?album_name=Stupid_Cow-Jingle_Jingle" />" rel="album">
      <div id="headCoverAlbum_Stupid_Cow-Jingle_Jingle">
         <div class="textCover">
            <h5>Inteligent Cow Song about drugs,sex and peace</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/album/nightcore_album?album_name=Baka-Triple_Baka" />" rel="album">
      <div id="headCoverAlbum_Baka-Triple_Baka">
         <div class="textCover">
            <h5>Stupid Baka Głupek</h5>
         </div>
     </div>
   </a>
</div>

</body>
</html>
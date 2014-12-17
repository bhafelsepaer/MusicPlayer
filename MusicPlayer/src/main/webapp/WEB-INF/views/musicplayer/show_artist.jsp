<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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

#headCoverArtist_Elza {
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/Dj_Elza.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverArtist_Stupid_Cow {
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/Stupid_Cow.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverArtist_Baka {
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/Baka.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverArtist_The_Fighters {
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/The_Fighters.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverArtist_Tritiana {
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/Tritiana.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverArtist_Pikachu {
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/Pikachu.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

</style>

</head>
<body>

<security:authorize access="permitAll">
<div class="linkMusic">
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=ELZA" />" rel="artist">
      <div id="headCoverArtist_Elza">
         <div class="textCover">
            <h5>DJ Elza create Song with a snow</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=Stupid_Cow" />" rel="artist">
      <div id="headCoverArtist_Stupid_Cow">
         <div class="textCover">
            <h5>Stupid_Cow create inteligent song</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=Baka" />" rel="artist">
      <div id="headCoverArtist_Baka">
         <div class="textCover">
            <h5>Artist Baka from Wysp Dziewiczych Stanów Zjednoczonych</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=The_Fighters" />" rel="artist">
      <div id="headCoverArtist_The_Fighters">
         <div class="textCover">
            <h5>The_Fighters Song About fights</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=Tritiana" />" rel="artist">
      <div id="headCoverArtist_Tritiana">
         <div class="textCover">
            <h5>Tritiana is new vocal artist who win the show Mam Talent</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=Pikachu" />" rel="artist">
      <div id="headCoverArtist_Pikachu">
         <div class="textCover">
            <h5>Pikachu who escaped from Ash Startet to filfil him dream about sing</h5>
         </div>
     </div>
   </a>
</div>
</security:authorize>

</body>
</html>
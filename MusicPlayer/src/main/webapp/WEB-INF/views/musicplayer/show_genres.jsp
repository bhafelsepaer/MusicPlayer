<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#headCover {
    background-image: url(<c:url value='/resources/images/album_picture/byGenre/nightcore-pop.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverMetal {
    background-image: url(<c:url value='/resources/images/album_picture/byGenre/nightcore-Metal.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px; 
    position: relative;
}

#headCoverHipHop {
    background-image: url(<c:url value='/resources/images/album_picture/byGenre/nightcore-HipHop.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px; 
    position: relative;
}

#headCoverRock {
    background-image: url(<c:url value='/resources/images/album_picture/byGenre/nightcore-Rock.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px; 
    position: relative;
}

#headCoverTrance {
    background-image: url(<c:url value='/resources/images/album_picture/byGenre/Trance.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px; 
    position: relative;
}


#headCoverEpicMusic {
    background-image: url(<c:url value='/resources/images/album_picture/byGenre/EpicMusic.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px; 
    position: relative;
}

.textCover {
  position: absolute;
  bottom: 0;
  left: 0;
  font-family: Helvetica Neue;
  color: white;
}

.linkMusic {
   width: 200px;
   height: 300px;
   margin: 10px;
   position: relative;
   display: inline-block;
}

</style>
</head>
<body>

<security:authorize access="permitAll">
<div class="linkMusic">
   <a href="<spring:url value="/genre/nightcore_genre?genre_name=nightcore-pop" />" rel="genre">
      <div id="headCover">
         <div class="textCover">
            <h5>Best Nightcore Pop Music! Bring your friends and let's have Fun</h5>
         </div>
     </div>
   </a>
</div>

<div class="linkMusic">
      <a href="<spring:url value="/genre/nightcore_genre?genre_name=nightcore-metal" />" rel="genre">
          <div id="headCoverMetal">
             <div class="textCover">
              <h5>Best Nightcore Metal Music! Feel Black Power</h5>
           </div>
         </div>
     </a>
</div>

<div class="linkMusic">
    <a href="<spring:url value="/genre/nightcore_genre?genre_name=nightcore-HipHop" />" rel="genre">
        <div id="headCoverHipHop">
             <div class="textCover">
                 <h5>Best Nightcore Hip Hop Music! The Best Rap Here</h5>
             </div>
        </div>
    </a>
</div>

<div class="linkMusic">
     <a href="<spring:url value="/genre/nightcore_genre?genre_name=nightcore-Rock" />" rel="genre">
        <div id="headCoverRock">
           <div class="textCover">
              <h5>Best Nightcore Rock Music! Rock will Eat You</h5>
           </div>
        </div>
    </a>
</div>

<div class="linkMusic">
     <a href="<spring:url value="/genre/nightcore_genre?genre_name=Trance" />" rel="genre">
        <div id="headCoverTrance">
           <div class="textCover">
              <h5>Trance Music will Created by independent Artist</h5>
           </div>
        </div>
    </a>
</div>

<div class="linkMusic">
     <a href="<spring:url value="/genre/nightcore_genre?genre_name=Epic Music" />" rel="genre">
        <div id="headCoverEpicMusic">
           <div class="textCover">
              <h5>Epci Music From Game Movie itp.</h5>
           </div>
        </div>
    </a>
</div>
</security:authorize>
</body>
</html>
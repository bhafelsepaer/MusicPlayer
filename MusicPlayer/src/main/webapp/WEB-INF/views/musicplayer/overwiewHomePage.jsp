<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#headCover {
    background-image: url(<c:url value='/resources/images/album_picture/nightcore-pop.jpg' />);
    background-size: cover;
    border: 1px solid blue;
    width: 200px;
    height: 300px;
    position: relative;
}

#headCoverMetal {
    background-image: url(<c:url value='/resources/images/album_picture/nightcore-metal.jpg' />);
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
   position: relative;
   display: inline-block;
}

</style>
</head>
<body>

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
      <a href="<spring:url value="/genre/nightcore_genre?genre_name=nightcore-metal" />" rel="genre" id="linkMusic">
          <div id="headCoverMetal">
             <div class="textCover">
              <h5>Best Nightcore Metal Music! Feel Black Power</h5>
           </div>
         </div>
     </a>
</div>

</body>
</html>
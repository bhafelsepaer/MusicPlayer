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

#headCoverArtist_Elza{
    background-image: url(<c:url value='/resources/images/album_picture/byArtist/Dj_Elza.jpg' />);
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
   <a href="<spring:url value="/artist/nightcore_artist?artist_name=Elza" />" rel="artist">
      <div id="headCoverArtist_Elza">
         <div class="textCover">
            <h5>DJ Elza create Song with a snow</h5>
         </div>
     </div>
   </a>
</div>


</body>
</html>